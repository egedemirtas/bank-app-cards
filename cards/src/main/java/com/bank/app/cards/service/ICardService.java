package com.bank.app.cards.service;

import com.bank.app.cards.constant.CardType;
import com.bank.app.cards.dto.CardDto;
import com.bank.app.cards.dto.CardRequestDto;

import java.util.List;

public interface ICardService {
    String createCard(CardRequestDto cardDto);

    List<CardDto> getCards(String personalId);

    void updateCard(CardDto cardDto);

    void deleteCard(String personalId, CardType cardType);
}
