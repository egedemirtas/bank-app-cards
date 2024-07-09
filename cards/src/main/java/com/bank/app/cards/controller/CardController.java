package com.bank.app.cards.controller;

import com.bank.app.cards.constant.CardType;
import com.bank.app.cards.dto.CardDto;
import com.bank.app.cards.dto.CardRequestDto;
import com.bank.app.cards.dto.ResponseDto;
import com.bank.app.cards.service.ICardService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/cards", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CardController {

    private final ICardService cardService;

    @Autowired
    public CardController(ICardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createCard(@RequestBody @Valid CardRequestDto cardRequestDto) {
        String cardNumber = cardService.createCard(cardRequestDto);
        return new ResponseEntity<>(new ResponseDto("Card successfully created: %s".formatted(cardNumber)),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CardDto>> fetchCards(
            @RequestParam @Pattern(regexp = "(^$|[0-9]{15})", message = "Personal ID must be 15 digits")
            String personalId) {
        List<CardDto> cardList = cardService.getCards(personalId);
        return new ResponseEntity<>(cardList, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseDto> updateCard(@RequestBody @Valid CardDto cardDto) {
        cardService.updateCard(cardDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto> deleteCard(
            @RequestParam @Pattern(regexp = "(^$|[0-9]{15})", message = "Personal ID must be 15 digits")
            String personalId, @RequestParam CardType cardType) {
        cardService.deleteCard(personalId, cardType);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
