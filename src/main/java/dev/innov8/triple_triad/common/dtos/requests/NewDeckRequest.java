package dev.innov8.triple_triad.common.dtos.requests;

import dev.innov8.triple_triad.common.models.card.Deck;
import dev.innov8.triple_triad.common.web.ResourceRequest;

public class NewDeckRequest extends ResourceRequest<Deck> {

    @Override
    public Deck extract() {
        return null;
    }

}
