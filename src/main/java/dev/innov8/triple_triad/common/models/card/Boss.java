package dev.innov8.triple_triad.common.models.card;

import javax.persistence.*;

@Entity
@DiscriminatorValue("BOSS")
public class Boss extends AbstractCreature {

    protected Boss() {
        super();
    }

    protected Boss(String id, String name, int topRank, int rightRank, int bottomRank, int leftRank, String imageUrl, int level, Element element) {
        super(id, name, topRank, rightRank, bottomRank, leftRank, imageUrl, level, Type.BOSS, element);
    }

}
