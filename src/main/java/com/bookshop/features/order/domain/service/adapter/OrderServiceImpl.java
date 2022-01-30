package com.bookshop.features.order.domain.service.adapter;

import com.bookshop.core.config.CacheConfig;
import com.bookshop.features.basket.api.BasketService;
import com.bookshop.features.basket.data.entity.BasketEntity;
import com.bookshop.features.basket.data.entity.BasketItemEntity;
import com.bookshop.features.book.domain.service.port.BookService;
import com.bookshop.features.order.api.request.OrderRequest;
import com.bookshop.features.order.data.entity.*;
import com.bookshop.features.order.domain.repository.DeliveryMethodRepository;
import com.bookshop.features.order.domain.repository.OrderRepository;
import com.bookshop.features.order.domain.service.port.OrderService;
import com.bookshop.features.order.exception.BasketIsEmpty;
import com.bookshop.features.order.exception.NotEnoughBook;
import com.bookshop.features.payment.api.PaymentService;
import com.bookshop.features.payment.data.entity.PaymentEntity;
import com.bookshop.features.payment.data.entity.PaymentMethodEntity;
import com.bookshop.features.payment.data.entity.PaymentStatus;
import com.bookshop.features.user.api.AddressService;
import com.bookshop.features.user.api.UserService;
import com.bookshop.features.user.data.entity.AddressEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final DeliveryMethodRepository deliveryMethodRepository;
    private final BasketService basketService;
    private final UserService userService;
    private final PaymentService paymentService;
    private final BookService bookService;
    private final AddressService addressService;
    private final CacheConfig cacheConfig;


    @Override
    public List<DeliveryMethodEntity> getDeliveryMethods() {
        return deliveryMethodRepository.getDeliveryMethods();
    }

    @Override
    public OrderEntity makeOrder(OrderRequest request) {
        var basket = basketService.getUserBasket();
        var paymentMethod = paymentService.getPaymentMethodById(request.getPaymentMethodId());
        var deliveryMethod = getDeliveryMethodById(request.getDeliveryMethodId());
        var addressDelivery = addressService.getAddressById(request.getDeliveryAddressId());
        var currentUser = userService.getCurrentUser();

        validateBasket(basket);

        var delivery = buildDelivery(deliveryMethod, addressDelivery);

        var payment = buildPayment(basket, paymentMethod);

        var order = OrderEntity.builder()
                .orderDate(LocalDateTime.now())
                .orderStatus(OrderStatus.IN_PROGRESS)
                .orderPositions(convertBasketItemsToOrderPositions(basket))
                .user(currentUser)
                .payment(payment)
                .fullPrice(payment.getAmount().add(delivery.getDeliveryPrice()))
                .deliveryEntity(delivery).build();

        OrderEntity result = orderRepository.createOrder(order);
        basket.clearBasket();
        return result;
    }

    private DeliveryEntity buildDelivery(DeliveryMethodEntity deliveryMethod, AddressEntity addressDelivery) {
        return DeliveryEntity.builder()
                .deliveryMethod(deliveryMethod)
                .address(addressDelivery)
                .deliveryPrice(deliveryMethod.getPrice())
                .build();
    }

    private PaymentEntity buildPayment(BasketEntity basket, PaymentMethodEntity paymentMethod) {
        return PaymentEntity.builder()
                .amount(basket.getBasketFullPrice())
                .paymentStatus(PaymentStatus.UN_PAID)
                .createdAt(LocalDateTime.now())
                .paymentMethodEntity(paymentMethod)
                .build();
    }

    private List<OrderPositionEntity> convertBasketItemsToOrderPositions(BasketEntity basket) {
        return basket.getItems().stream().map(this::convertItemToOrderPosition).collect(Collectors.toList());
    }

    private OrderPositionEntity convertItemToOrderPosition(BasketItemEntity item) {
        return OrderPositionEntity.builder()
                .orderedBook(item.getBook())
                .price(item.getBook().getCurrentPrice())
                .build();
    }

    private void validateBasket(BasketEntity basket) {
        cacheConfig.setAvailable(false);
        if (basket.getItems() == null) throw new BasketIsEmpty();
        try {
            basket.getItems().forEach(book -> {
                        var magazineQuantity = bookService.getBookById(book.getId()).getQuantity();
                        if (book.getQuantity() > magazineQuantity) {
                            throw new NotEnoughBook(book.getId());
                        }
                    }
            );
        } finally {
            cacheConfig.setAvailable(true);
        }

    }

    @Override
    public List<OrderEntity> getUserOrders() {
        var user = userService.getCurrentUser();
        return user.getOrders();
    }

    private DeliveryMethodEntity getDeliveryMethodById(Integer deliveryMethodId) {
        return deliveryMethodRepository.getDeliveryById(deliveryMethodId).orElseThrow(DeliveryMethodNotFound::new);
    }
}
