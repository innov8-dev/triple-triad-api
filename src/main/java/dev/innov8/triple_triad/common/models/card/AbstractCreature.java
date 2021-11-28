package dev.innov8.triple_triad.common.models.card;

import dev.innov8.triple_triad.common.models.Resource;

import javax.persistence.*;

@Entity
@Table(name = "creatures")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public class AbstractCreature extends Resource implements Creature {

    @Column(name = "name", nullable = false)
    protected String name;

    @Column(name = "top_rank", nullable = false)
    protected int topRank;

    @Column(name = "right_rank", nullable = false)
    protected int rightRank;

    @Column(name = "bottom_rank", nullable = false)
    protected int bottomRank;

    @Column(name = "left_rank", nullable = false)
    protected int leftRank;

    @Column(name = "image_url", nullable = false)
    protected String imageUrl;

    @Column(name = "level", nullable = false)
    protected int level;

    @Column(name = "type", nullable = false, insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    protected Type type;

    @Enumerated(EnumType.STRING)
    protected Element element;

    protected AbstractCreature() {
        super();
    }

    protected AbstractCreature(String id, String name, int topRank, int rightRank, int bottomRank, int leftRank, String imageUrl, int level, Type type, Element element) {
        this.id = id;
        this.name = name;
        this.topRank = topRank;
        this.rightRank = rightRank;
        this.bottomRank = bottomRank;
        this.leftRank = leftRank;
        this.imageUrl = imageUrl;
        this.level = level;
        this.type = type;
        this.element = element;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getTopRank() {
        return topRank;
    }

    @Override
    public int getRightRank() {
        return rightRank;
    }

    @Override
    public int getBottomRank() {
        return bottomRank;
    }

    @Override
    public int getLeftRank() {
        return leftRank;
    }

    @Override
    public Element getElement() {
        return element;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String toString() {
        return "AbstractCreature{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", topRank=" + topRank +
                ", rightRank=" + rightRank +
                ", bottomRank=" + bottomRank +
                ", leftRank=" + leftRank +
                ", imageUrl='" + imageUrl + '\'' +
                ", level=" + level +
                ", type=" + type +
                ", element=" + element +
                '}';
    }
}
