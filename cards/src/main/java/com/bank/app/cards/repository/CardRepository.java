package com.bank.app.cards.repository;

import com.bank.app.cards.constant.CardType;
import com.bank.app.cards.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByPersonalIdAndCardType(String personalId, CardType cardType);

    Optional<List<Card>> findByPersonalId(String personalId);
}
