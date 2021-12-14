package dev.innov8.triple_triad.common.web.dtos.requests;

import dev.innov8.triple_triad.models.card.Card;

public class NewCardRequest implements ResourceRequest<Card> {
    @Override
    public Card extract() {
        return null;
    }
}
