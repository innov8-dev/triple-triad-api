package dev.innov8.triple_triad.card.dtos.requests;

import dev.innov8.triple_triad.common.models.card.Card;
import dev.innov8.triple_triad.common.web.ResourceRequest;

public class NewCardRequest implements ResourceRequest<Card> {
    @Override
    public Card extract() {
        return null;
    }
}
