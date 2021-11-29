package dev.innov8.triple_triad.deck.dtos.requests;

import dev.innov8.triple_triad.common.models.card.Deck;
import dev.innov8.triple_triad.common.web.ResourceRequest;

public class NewDeckRequest implements ResourceRequest<Deck> {

    @Override
    public Deck extract() {
        return null;
    }

}
