package dev.innov8.triple_triad.card;

import dev.innov8.triple_triad.common.models.card.Card;
import dev.innov8.triple_triad.common.web.ResourceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cards")
public class CardController extends ResourceController<Card> {

    @Autowired
    public CardController(CardService cardService) {
        super(cardService);
    }

}
