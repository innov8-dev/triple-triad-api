package dev.innov8.triple_triad.card;

import dev.innov8.triple_triad.common.dtos.CardListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public CardListResponse searchCards(@RequestParam Map<String, String> requestParams) {
        return cardService.searchCards(requestParams);
    }

}
