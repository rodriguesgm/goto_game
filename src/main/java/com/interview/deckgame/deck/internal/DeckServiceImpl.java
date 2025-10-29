package com.interview.deckgame.deck.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public DeckEntity newDeck() {
        final var newDeck = new DeckEntity();
        final var cards = initializeCards();
        newDeck.setCards(cards);
        return deckRepository.save(newDeck);
    }

    @Override
    public Optional<DeckEntity> getDeckById(UUID deckId) {
        return deckRepository.findById(deckId);
    }

    private List<DeckCardEntity> initializeCards() {
        final var cards = new ArrayList<DeckCardEntity>();
        for (final var value : Card.CardValue.values()) {
            for (final var type : Card.CardType.values()) {
                final var card = new DeckCardEntity();
                card.setCard(new Card(value, type));
                cards.add(card);
            }
        }
        return cards;
    }
}
