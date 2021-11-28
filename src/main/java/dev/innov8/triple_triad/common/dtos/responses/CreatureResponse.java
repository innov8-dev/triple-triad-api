package dev.innov8.triple_triad.common.dtos.responses;

import dev.innov8.triple_triad.common.models.card.Creature;

public class CreatureResponse implements ResourceResponse {

    private String creatureId;
    private String name;
    private int topRank;
    private int rightRank;
    private int bottomRank;
    private int leftRank;
    private String obverseImageUrl;
    private String reverseImageUrl;
    private int level;
    private String type;
    private String element;

    public CreatureResponse(Creature creature) {
        this.creatureId = creature.getId();
        this.name = creature.getName();
        this.topRank = creature.getTopRank();
        this.rightRank = creature.getRightRank();
        this.bottomRank = creature.getBottomRank();
        this.leftRank = creature.getLeftRank();
        this.obverseImageUrl = creature.getObverseImageUrl();
        this.reverseImageUrl = creature.getReverseImageUrl();
        this.level = creature.getLevel();
        this.type = creature.getType().toString();
        this.element = creature.getElement().toString();
    }

    public String getCreatureId() {
        return creatureId;
    }

    public void setCreatureId(String creatureId) {
        this.creatureId = creatureId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTopRank() {
        return topRank;
    }

    public void setTopRank(int topRank) {
        this.topRank = topRank;
    }

    public int getRightRank() {
        return rightRank;
    }

    public void setRightRank(int rightRank) {
        this.rightRank = rightRank;
    }

    public int getBottomRank() {
        return bottomRank;
    }

    public void setBottomRank(int bottomRank) {
        this.bottomRank = bottomRank;
    }

    public int getLeftRank() {
        return leftRank;
    }

    public void setLeftRank(int leftRank) {
        this.leftRank = leftRank;
    }

    public String getObverseImageUrl() {
        return obverseImageUrl;
    }

    public void setObverseImageUrl(String obverseImageUrl) {
        this.obverseImageUrl = obverseImageUrl;
    }

    public String getReverseImageUrl() {
        return reverseImageUrl;
    }

    public void setReverseImageUrl(String reverseImageUrl) {
        this.reverseImageUrl = reverseImageUrl;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    @Override
    public String toString() {
        return "CreatureResponse{" +
                "creatureId='" + creatureId + '\'' +
                ", name='" + name + '\'' +
                ", topRank=" + topRank +
                ", rightRank=" + rightRank +
                ", bottomRank=" + bottomRank +
                ", leftRank=" + leftRank +
                ", obverseImageUrl='" + obverseImageUrl + '\'' +
                ", reverseImageUrl='" + reverseImageUrl + '\'' +
                ", level=" + level +
                ", type='" + type + '\'' +
                ", element='" + element + '\'' +
                '}';
    }

}
