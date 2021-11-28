package dev.innov8.triple_triad.common.web;

import dev.innov8.triple_triad.common.dtos.responses.CardResponse;
import dev.innov8.triple_triad.common.dtos.responses.CreatureResponse;
import dev.innov8.triple_triad.common.dtos.responses.ErrorResponse;
import dev.innov8.triple_triad.common.dtos.responses.UserResponse;
import dev.innov8.triple_triad.common.models.Resource;
import dev.innov8.triple_triad.common.models.card.AbstractCreature;
import dev.innov8.triple_triad.common.models.card.Card;
import dev.innov8.triple_triad.common.models.card.Creature;
import dev.innov8.triple_triad.common.models.user.AppUser;

public class ResponseFactory {

    private ResponseFactory() {
        super();
    }

    public static ResourceResponse buildResponse(String type, Resource resource) {
        System.out.println("RESOURCE: " + resource);
        System.out.println("TYPE: " + type);
        switch (type) {
            case "AppUser":
                return new UserResponse((AppUser) resource);
            case "Card":
                return new CardResponse((Card) resource);
            case "Monster":
            case "Boss":
            case "GuardianForce":
            case "Player":
                return new CreatureResponse((AbstractCreature) resource);
            default:
                return new ErrorResponse(401, "Invalid resource type specified.");
        }
    }

}
