package dev.innov8.triple_triad.creature;

import dev.innov8.triple_triad.common.models.card.Monster;
import dev.innov8.triple_triad.common.web.ResourceController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/creatures")
public class CreatureController extends ResourceController<Monster> {

    private final CreatureService creatureService;

    public CreatureController(CreatureService creatureService) {
        super(creatureService);
        this.creatureService = creatureService;
    }

}
