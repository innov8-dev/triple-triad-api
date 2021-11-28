package dev.innov8.triple_triad.common.models.card;

import javax.persistence.*;

@Entity
@DiscriminatorValue("GF")
public class GuardianForce extends AbstractCreature {

    protected GuardianForce() {
        super();
    }

    protected GuardianForce(String id, String name, int topRank, int rightRank, int bottomRank, int leftRank, String imageUrl, int level, Element element) {
        super(id, name, topRank, rightRank, bottomRank, leftRank, imageUrl, level, Type.GF, element);
    }
}
