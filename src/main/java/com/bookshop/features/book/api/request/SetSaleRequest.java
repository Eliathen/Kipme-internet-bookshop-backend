package com.bookshop.features.book.api.request;


import com.bookshop.features.book.data.entity.SaleUnit;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SetSaleRequest(
        @NotBlank(message = "Provide sale's value")
        @JsonProperty(value = "value", required = true)
        BigDecimal value,
        @NotBlank(message = "Provide sale's unit")
        @JsonProperty(value = "unit", required = true)
        SaleUnit unit,
        @NotBlank(message = "Provide start date")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @JsonProperty(value = "start_at", required = true)
        LocalDateTime startAt,
        @NotBlank(message = "Provide end date")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @JsonProperty(value = "end_at", required = true)
        LocalDateTime endAt
) {

}
