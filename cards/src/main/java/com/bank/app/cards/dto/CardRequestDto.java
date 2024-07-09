package com.bank.app.cards.dto;

import com.bank.app.cards.constant.CardType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardRequestDto {
    @NotEmpty(message = "Personal ID can not be empty")
    @Pattern(regexp="(^$|[0-9]{15})", message = "Personal ID must be 15 digits")
    private String personalId;

    @NotNull
    private CardType cardType;

    @Positive(message = "Limit of card must be positive")
    private BigDecimal totalLimit;
}
