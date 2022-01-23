package com.bookshop.features.book.api.request;


import com.bookshop.features.book.data.entity.SALE_UNIT;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class SetSaleRequest {

    @NotBlank(message = "Provide sale's value")
    private BigDecimal value;

    @NotBlank(message = "Provide sale's unit")
    private SALE_UNIT unit;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotBlank(message = "Provide start date")
    private LocalDateTime startAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotBlank(message = "Provide end date")
    private LocalDateTime endAt;

    @JsonCreator
    public SetSaleRequest(
            @JsonProperty(value = "value", required = true) BigDecimal value,
            @JsonProperty(value = "unit", required = true) SALE_UNIT unit,
            @JsonProperty(value = "start_at", required = true) LocalDateTime startAt,
            @JsonProperty(value = "end_at", required = true) LocalDateTime endAt) {
        this.value = value;
        this.unit = unit;
        this.startAt = startAt;
        this.endAt = endAt;
    }
}
