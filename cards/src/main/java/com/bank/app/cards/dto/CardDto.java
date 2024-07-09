package com.bank.app.cards.dto;

import com.bank.app.cards.constant.CardType;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardDto {

    @NotEmpty(message = "Personal id can not be empty")
    @Pattern(regexp = "(^$|[0-9]{15})", message = "Personal id should be 15 digits")
    private String personalId;

    @NotEmpty(message = "Card Number can not be a null or empty")
    @Pattern(regexp = "(^$|[0-9]{12})", message = "CardNumber must be 12 digits")
    private String cardNumber;

    @NotNull
    private CardType cardType;

    @Positive(message = "Total card limit should be greater than zero")
    private BigDecimal totalLimit;

    @PositiveOrZero(message = "Total amount used should be equal or greater than zero")
    private BigDecimal amountUsed;

    @PositiveOrZero(message = "Total available amount should be equal or greater than zero")

    private BigDecimal availableAmount;
}
