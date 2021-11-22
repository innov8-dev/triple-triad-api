package dev.innov8.triple_triad.common.dtos;

import dev.innov8.triple_triad.common.models.card.Card;

public class SingleCardResponse {

    private Card cardData;

    public SingleCardResponse() {
        super();
    }

    public SingleCardResponse(Card card) {
        this.cardData = card;
    }

    public Card getCardData() {
        return cardData;
    }

    @Override
    public String toString() {
        return "SingleCardResponse{" +
                "cardData=" + cardData +
                '}';
    }
}
