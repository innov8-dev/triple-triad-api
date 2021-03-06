package dev.innov8.triple_triad.common.web.dtos.responses;

import dev.innov8.triple_triad.models.card.Creature;
import dev.innov8.triple_triad.models.Resource;
import dev.innov8.triple_triad.models.card.Card;
import dev.innov8.triple_triad.models.user.AppUser;

public class ResponseFactory {

    private ResponseFactory() {
        super();
    }

    public static ResourceResponse buildResponse(String type, Resource resource) {
        switch (type) {
            case "AppUser":
                return new UserResponse((AppUser) resource);
            case "Card":
                return new CardResponse((Card) resource);
            case "Creature":
                return new CreatureResponse((Creature) resource);
            default:
                return new ErrorResponse(401, "Invalid resource type specified.");
        }
    }

}
