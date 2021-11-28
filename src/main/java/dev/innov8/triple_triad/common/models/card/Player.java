package dev.innov8.triple_triad.common.models.card;

import javax.persistence.*;

@Entity
@DiscriminatorValue("PLAYER")
public class Player extends AbstractCreature {

    protected Player() {
        super();
    }

    protected Player(String id, String name, int topRank, int rightRank, int bottomRank, int leftRank, String imageUrl, int level, Element element) {
        super(id, name, topRank, rightRank, bottomRank, leftRank, imageUrl, level, Type.PLAYER, element);
    }

}
