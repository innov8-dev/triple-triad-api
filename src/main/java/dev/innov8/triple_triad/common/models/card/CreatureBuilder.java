package dev.innov8.triple_triad.common.models.card;

import java.util.UUID;

public class CreatureBuilder {

    private String id;
    private String name;
    private int topRank;
    private int rightRank;
    private int bottomRank;
    private int leftRank;
    private String imageUrl;
    private int level;
    private Type type;
    private Element element;

    public CreatureBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public CreatureBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CreatureBuilder setTopRank(int topRank) {
        this.topRank = topRank;
        return this;
    }

    public CreatureBuilder setRightRank(int rightRank) {
        this.rightRank = rightRank;
        return this;
    }

    public CreatureBuilder setBottomRank(int bottomRank) {
        this.bottomRank = bottomRank;
        return this;
    }

    public CreatureBuilder setLeftRank(int leftRank) {
        this.leftRank = leftRank;
        return this;
    }

    public CreatureBuilder setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public CreatureBuilder setLevel(int level) {
        this.level = level;
        return this;
    }

    public CreatureBuilder setType(Type type) {
        this.type = type;
        return this;
    }

    public CreatureBuilder setElement(Element element) {
        this.element = element;
        return this;
    }

    public Creature build() {
        if (id == null || id.isEmpty()) id = UUID.randomUUID().toString();
        if (element == null) element = Element.NONE;

        switch (type) {
            case MONSTER:
                return new Monster(id, this.name, topRank, rightRank, bottomRank, leftRank, imageUrl, level, element);
            case BOSS:
                return new Boss(id, this.name, topRank, rightRank, bottomRank, leftRank, imageUrl, level, element);
            case GF:
                return new GuardianForce(id, this.name, topRank, rightRank, bottomRank, leftRank, imageUrl, level, element);
            case PLAYER:
                return new Player(id, this.name, topRank, rightRank, bottomRank, leftRank, imageUrl, level, element);
            default:
                return null;
        }

    }

}
