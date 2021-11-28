package dev.innov8.triple_triad.creature;

import dev.innov8.triple_triad.common.models.card.Monster;
import dev.innov8.triple_triad.common.services.ResourceService;
import dev.innov8.triple_triad.common.data.EntitySearcher;
import org.springframework.stereotype.Service;

@Service
public class CreatureService extends ResourceService<Monster> {

    private final CreatureRepository creatureRepo;

    public CreatureService(CreatureRepository creatureRepo, EntitySearcher entitySearcher) {
        super(creatureRepo, entitySearcher);
        this.creatureRepo = creatureRepo;
    }

}
