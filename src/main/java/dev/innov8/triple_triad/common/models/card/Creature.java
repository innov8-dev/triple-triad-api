package dev.innov8.triple_triad.common.models.card;

import dev.innov8.triple_triad.common.models.Resource;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;


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

    protected Creature() {
        super();
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

    public String getImageUrl() {
        return imageUrl;
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

            List<Map<String, Boolean>> illegalStates = new ArrayList<>();

            if (topRank < 1 || topRank > 10) {
                Map<String, Boolean> illegalTopRank = new HashMap<>();
                illegalTopRank.put("topRank", true);
                illegalStates.add(illegalTopRank);
            }

            if (rightRank < 1 || rightRank > 10) {
                Map<String, Boolean> illegalRightRank = new HashMap<>();
                illegalRightRank.put("rightRank", true);
                illegalStates.add(illegalRightRank);
            }

            if (bottomRank < 1 || bottomRank > 10) {
                Map<String, Boolean> illegalBottomRank = new HashMap<>();
                illegalBottomRank.put("bottomRank", true);
                illegalStates.add(illegalBottomRank);
            }

            if (leftRank < 1 || leftRank > 10) {
                Map<String, Boolean> illegalLeftRank = new HashMap<>();
                illegalLeftRank.put("leftRank", true);
                illegalStates.add(illegalLeftRank);
            }

            if (level < 1 || level > 10) {
                Map<String, Boolean> illegalLevel = new HashMap<>();
                illegalLevel.put("level", true);
                illegalStates.add(illegalLevel);
            }

            if (imageUrl == null || imageUrl.trim().isEmpty()) {
                Map<String, Boolean> illegalLevel = new HashMap<>();
                illegalLevel.put("imageUrl", true);
                illegalStates.add(illegalLevel);
            }

            if (!illegalStates.isEmpty()) {

                List<String> states = illegalStates.stream()
                                                   .flatMap(stateMap -> stateMap.entrySet().stream())
                                                   .map(Map.Entry::getKey)
                                                   .collect(Collectors.toList());

                throw new IllegalStateException("Illegal instantiation arguments found for states: " + states);

            }


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
            return new Creature(id, this.name, topRank, rightRank, bottomRank, leftRank, imageUrl, level, type, element);
        }

    }

}
