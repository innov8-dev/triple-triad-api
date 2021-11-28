package dev.innov8.triple_triad.common.models.card;

import javax.persistence.*;

@Entity
@DiscriminatorValue("MONSTER")
public class Monster extends AbstractCreature {

    protected Monster() {
        super();
    }

    protected Monster(String id, String name, int topRank, int rightRank, int bottomRank, int leftRank, String imageUrl, int level, Element element) {
        super(id, name, topRank, rightRank, bottomRank, leftRank, imageUrl, level, Type.MONSTER, element);
    }

}
