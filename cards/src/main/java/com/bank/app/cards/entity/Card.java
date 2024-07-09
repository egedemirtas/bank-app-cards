package com.bank.app.cards.entity;

import com.bank.app.cards.constant.CardType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Card extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String personalId;

    @Column(nullable = false, unique = true, updatable = false)
    private String cardNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @Column(nullable = false)
    private BigDecimal totalLimit;

    @Column(nullable = false)
    private BigDecimal amountUsed;

    @Column(nullable = false)
    private BigDecimal availableAmount;
}
