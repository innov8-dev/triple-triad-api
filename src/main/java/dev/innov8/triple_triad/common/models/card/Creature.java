package dev.innov8.triple_triad.common.models.card;

import dev.innov8.triple_triad.common.models.Resource;

import javax.persistence.*;
import java.util.UUID;


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

    @Column(name = "obverse_image_url", nullable = false)
    private String obverseImageUrl;

    @Column(name = "level", nullable = false)
    private int level;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Enumerated(EnumType.STRING)
    private Element element;

    protected Creature() {
        super();
    }

    private Creature(String id, String name, int topRank, int rightRank, int bottomRank, int leftRank, String obverseImageUrl, int level, Type type, Element element) {
        this.id = id;
        this.name = name;
        this.topRank = topRank;
        this.rightRank = rightRank;
        this.bottomRank = bottomRank;
        this.leftRank = leftRank;
        this.obverseImageUrl = obverseImageUrl;
        this.level = level;
        this.type = type;
        this.element = element;
    }

    public String getName() {
        return name;
    }

    public int getTopRank() {
        return topRank;
    }

    public int getRightRank() {
        return rightRank;
    }

    public int getBottomRank() {
        return bottomRank;
    }

    public int getLeftRank() {
        return leftRank;
    }

    public String getObverseImageUrl() {
        return obverseImageUrl;
    }

    public int getLevel() {
        return level;
    }

    public Type getType() {
        return type;
    }

    public Element getElement() {
        return element;
    }

    public static class CreatureBuilder {

        private String id;
        private String name;
        private int topRank;
        private int rightRank;
        private int bottomRank;
        private int leftRank;
        private String obverseImageUrl;
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

        public CreatureBuilder setObverseImageUrl(String obverseImageUrl) {
            this.obverseImageUrl = obverseImageUrl;
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
            return new Creature(id, this.name, topRank, rightRank, bottomRank, leftRank, obverseImageUrl, level, type, element);
        }

    }

}
