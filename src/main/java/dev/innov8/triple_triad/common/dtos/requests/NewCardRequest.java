package dev.innov8.triple_triad.common.dtos.requests;

import dev.innov8.triple_triad.common.models.card.Card;
import dev.innov8.triple_triad.common.web.ResourceRequest;

public class NewCardRequest extends ResourceRequest<Card> {
    @Override
    public Card extract() {
        return null;
    }
}
