package com.bank.app.cards.service;

import com.bank.app.cards.constant.CardType;
import com.bank.app.cards.dto.CardDto;
import com.bank.app.cards.dto.CardRequestDto;
import com.bank.app.cards.entity.Card;
import com.bank.app.cards.exception.CardAlreadyExistsException;
import com.bank.app.cards.exception.CardNotFoundException;
import com.bank.app.cards.mapper.CardMapper;
import com.bank.app.cards.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service
public class CardService implements ICardService {

    private static final String CARD_ALREADY_EXISTS_ERROR =
            "Card already exists with the same personal Id and type";
    private static final String CARD_NOT_FOUND_PID_ERROR = "Cards not found with personal ID: %s";
    private static final String CARD_NOT_FOUND_PID_TYPE_ERROR =
            "Cards could not be found for personalID: %s, type: %s";

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    public CardService(CardRepository cardRepository, CardMapper cardMapper) {
        this.cardRepository = cardRepository;
        this.cardMapper = cardMapper;
    }

    @Override
    public String createCard(CardRequestDto cardRequestDto) {
        cardRepository.findByPersonalIdAndCardType(cardRequestDto.getPersonalId(),
                cardRequestDto.getCardType()).ifPresent(x -> {
            throw new CardAlreadyExistsException(CARD_ALREADY_EXISTS_ERROR);
        });
        Card card = cardRepository.save(createNewCard(cardRequestDto));
        return card.getCardNumber();
    }

    @Override
    public List<CardDto> getCards(String personalId) {
        List<Card> cardList = cardRepository.findByPersonalId(personalId)
                .orElseThrow(() -> new CardNotFoundException(CARD_NOT_FOUND_PID_ERROR.formatted(personalId)));
        return cardMapper.cardList2CardDtoList(cardList);
    }

    @Override
    public void updateCard(CardDto cardDto) {
        Card card = cardRepository.findByPersonalIdAndCardType(cardDto.getPersonalId(), cardDto.getCardType())
                .orElseThrow(() -> new CardNotFoundException(
                        CARD_NOT_FOUND_PID_TYPE_ERROR.formatted(cardDto.getPersonalId(),
                                cardDto.getCardType())));
        Card updatedCard = cardMapper.cardDto2Card(cardDto);
        updatedCard.setId(card.getId());
        cardRepository.save(updatedCard);
    }

    @Override
    public void deleteCard(String personalId, CardType cardType) {
        Card card = cardRepository.findByPersonalIdAndCardType(personalId, cardType).orElseThrow(
                () -> new CardNotFoundException(
                        CARD_NOT_FOUND_PID_TYPE_ERROR.formatted(personalId, cardType)));
        cardRepository.delete(card);
    }

    private Card createNewCard(CardRequestDto cardRequestDto) {
        Card newCard = new Card();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setPersonalId(cardRequestDto.getPersonalId());
        newCard.setCardType(cardRequestDto.getCardType());
        newCard.setTotalLimit(cardRequestDto.getTotalLimit());
        newCard.setAmountUsed(BigDecimal.ZERO);
        newCard.setAvailableAmount(cardRequestDto.getTotalLimit());
        return newCard;
    }
}
