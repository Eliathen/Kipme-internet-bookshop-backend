package com.bookshop.core.schedules;


import com.bookshop.features.book.domain.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class ScheduleTasks {

    private final SaleRepository saleRepository;
    private final Logger logger = LoggerFactory.getLogger(ScheduleTasks.class);


    @Scheduled(initialDelay = 1000 * 60, cron = "0 0 0 * * *", zone = "Poland") // run every day at midnight
    public void startEndSales() {
        var sales = saleRepository.getAllSales();
        logger.info("Scheduler invoked");
        sales.forEach(sale -> {
            if (!sale.isActive() && LocalDateTime.now().isAfter(sale.getStartAt()) && LocalDateTime.now().isBefore(sale.getEndAt())) {
                sale.setActive(true);
                saleRepository.saveSale(sale);
            } else if (sale.isActive() && LocalDateTime.now().isAfter(sale.getEndAt())) {
                sale.setActive(false);
                saleRepository.saveSale(sale);
            }
        });
    }

}
