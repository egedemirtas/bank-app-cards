package com.bank.app.cards.mapper;

import com.bank.app.cards.dto.CardDto;
import com.bank.app.cards.entity.Card;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CardMapper {
    Card cardDto2Card(CardDto cardDto);

    CardDto card2CardDto(Card card);

    List<CardDto> cardList2CardDtoList(List<Card> cardList);
}
