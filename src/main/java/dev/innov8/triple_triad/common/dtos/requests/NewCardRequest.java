package dev.innov8.triple_triad.common.dtos.requests;

import dev.innov8.triple_triad.common.models.card.Card;
import dev.innov8.triple_triad.common.web.AppRequest;

public class NewCardRequest extends AppRequest<Card> {
    @Override
    public Card extract() {
        return null;
    }
}
