package dev.innov8.triple_triad.common.models.card;

import dev.innov8.triple_triad.common.models.Resource;

import javax.persistence.*;
import java.util.*;

@Entity(name = "creatures")
public class Creature extends Resource {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "top_rank", nullable = false)
    private int topRank;

    @Column(name = "right_rank", nullable = false)
    private int rightRank;

    @Column(name = "bottom_rank", nullable = false)
    private int bottomRank;

    @Column(name = "left_rank", nullable = false)
    private int leftRank;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "level", nullable = false)
    private int level;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Enumerated(EnumType.STRING)
    private Element element;

    public Creature() {
        super();
        id = UUID.randomUUID().toString();
        element = Element.NONE;
    }

    private Creature(String id, String name, int topRank, int rightRank, int bottomRank, int leftRank, String imageUrl, int level, Type type, Element element) {
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

    public String getName() {
        return name;
    }

    public Creature setName(String name) {
        this.name = name;
        return this;
    }

    public int getTopRank() {
        return topRank;
    }

    public Creature setTopRank(int topRank) {
        this.topRank = topRank;
        return this;
    }

    public int getRightRank() {
        return rightRank;
    }

    public Creature setRightRank(int rightRank) {
        this.rightRank = rightRank;
        return this;
    }

    public int getBottomRank() {
        return bottomRank;
    }

    public Creature setBottomRank(int bottomRank) {
        this.bottomRank = bottomRank;
        return this;
    }

    public int getLeftRank() {
        return leftRank;
    }

    public Creature setLeftRank(int leftRank) {
        this.leftRank = leftRank;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Creature setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public int getLevel() {
        return level;
    }

    public Creature setLevel(int level) {
        switch (level) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                type = Type.MONSTER;
                break;
            case 6:
            case 7:
                type = Type.BOSS;
                break;
            case 8:
            case 9:
                type = Type.GF;
                break;
            case 10:
                type = Type.PLAYER;
        }
        this.level = level;
        return this;
    }

    public Type getType() {
        return type;
    }

    public Creature setType(Type type) {
        this.type = type;
        return this;
    }

    public Element getElement() {
        return element;
    }

    public Creature setElement(Element element) {
        this.element = element;
        return this;
    }

    @Override
    public String toString() {
        return "Creature{" +
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
