package com.interview.deckgame.deck.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.interview.deckgame.deck.DeckService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DeckServiceImpl implements DeckService {

    private final DeckRepository deckRepository;
    private final DeckCardRepository cardRepository;

    @Transactional
    public DeckEntity newDeck(String name) {
        final var newDeck = new DeckEntity();
        newDeck.setName(name);
        final var savedDeck = deckRepository.save(newDeck);

        final var cards = initializeCards(savedDeck);
        cardRepository.saveAll(cards);

        savedDeck.setCards(cards);
        return savedDeck;
    }

    @Override
    public Optional<DeckEntity> getDeckById(Long deckId) {
        return deckRepository.findById(deckId);
    }

    private List<CardEntity> initializeCards(DeckEntity deck) {
        final var cards = new ArrayList<CardEntity>();
        for (var suit : CardEntity.Suit.values()) {
            for (var value : CardEntity.Value.values()) {
                CardEntity card = new CardEntity();
                card.setDeck(deck);
                card.setSuit(suit);
                card.setValue(value);
                card.setRankValue(rankFor(value));
                cards.add(card);
            }
        }
        return cards;
    }

    private int rankFor(CardEntity.Value v) {
        return switch (v) {
            case A -> 1;
            case JACK -> 11;
            case QUEEN -> 12;
            case KING -> 13;
            default -> v.ordinal() + 1;
        };
    }
}
