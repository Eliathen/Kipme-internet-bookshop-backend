package com.bookshop.features.book.domain.service.adapter;

import com.bookshop.features.book.api.request.SetSaleRequest;
import com.bookshop.features.book.data.entity.BookEntity;
import com.bookshop.features.book.data.entity.SALE_UNIT;
import com.bookshop.features.book.data.entity.SaleEntity;
import com.bookshop.features.book.domain.repository.SaleRepository;
import com.bookshop.features.book.domain.service.port.BookService;
import com.bookshop.features.book.domain.service.port.SaleService;
import com.bookshop.features.book.exception.InvalidCategorySaleUnitException;
import com.bookshop.features.book.exception.InvalidSaleValue;
import com.bookshop.features.book.mapper.SaleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final BookService bookService;
    private final SaleRepository saleRepository;

    @Override
    public void setSaleForBook(SetSaleRequest request, Long bookId) {
        SaleEntity sale = SaleMapper.mapToSaleEntity(request);
        BookEntity book = bookService.getBookById(bookId);
        validateSale(sale, book);
        sale.addBook(book);
        saleRepository.saveSale(sale);
    }

    @Override
    public void setSaleForCategory(SetSaleRequest request, Integer categoryId) {
        SaleEntity sale = SaleMapper.mapToSaleEntity(request);
        if (sale.getSaleUnit() == SALE_UNIT.VALUE) throw new InvalidCategorySaleUnitException();
        for (BookEntity bookEntity : bookService.getBooksByCategoryId(categoryId)) {
            validateSale(sale, bookEntity);
            sale.addBook(bookEntity);
        }
        saleRepository.saveSale(sale);
    }

    private void validateSale(SaleEntity sale, BookEntity book) {
        if (sale.getSaleUnit() == SALE_UNIT.PERCENT) {
            validatePercentSale(sale);
            return;
        }
        validateValueSale(sale, book);
    }

    private void validatePercentSale(SaleEntity sale) {
        if (sale.getValue().compareTo(BigDecimal.ZERO) < 0 || sale.getValue().compareTo(BigDecimal.valueOf(100)) > 0)
            throw new InvalidSaleValue();
    }

    private void validateValueSale(SaleEntity saleEntity, BookEntity book) {
        if (saleEntity.getValue().compareTo(BigDecimal.ZERO) < 0 || saleEntity.getValue().compareTo(book.getPrice()) > 0)
            throw new InvalidSaleValue();
    }
}
