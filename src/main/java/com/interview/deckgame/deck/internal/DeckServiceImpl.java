package com.interview.deckgame.deck.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.interview.deckgame.deck.DeckService;
import com.interview.deckgame.shared.CardRank;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DeckServiceImpl implements DeckService {

    private final DeckRepository deckRepository;
    private final CardRepository cardRepository;

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
                card.setRankValue(CardRank.rankFor(value));
                cards.add(card);
            }
        }
        return cards;
    }
}
