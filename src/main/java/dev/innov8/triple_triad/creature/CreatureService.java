package dev.innov8.triple_triad.creature;

import dev.innov8.triple_triad.common.dtos.requests.NewCreatureRequest;
import dev.innov8.triple_triad.common.models.card.Creature;
import dev.innov8.triple_triad.common.services.ResourceService;
import dev.innov8.triple_triad.common.util.EntitySearcher;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class CreatureService extends ResourceService<Creature> {

    private final CreatureRepository creatureRepo;

    public CreatureService(CreatureRepository creatureRepo, EntitySearcher entitySearcher) {
        super(creatureRepo, entitySearcher);
        this.creatureRepo = creatureRepo;
    }

}
