package dev.innov8.triple_triad.common.dtos;

import dev.innov8.triple_triad.common.models.card.Card;

import java.util.ArrayList;
import java.util.Collection;

public class CardListResponse {

    private final Collection<Card> cards;

    public CardListResponse() {
        super();
        this.cards = new ArrayList<>();
    }

    public CardListResponse(Collection<Card> cards) {
        this.cards = cards;
    }

    public Collection<Card> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        return "CardListResponse{" +
                "cards=" + cards +
                '}';
    }

}
