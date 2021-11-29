package dev.innov8.triple_triad.creature.dtos.requests;

import dev.innov8.triple_triad.common.models.card.Element;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class UpdateCreatureRequest {

    @NotBlank
    private String id;

    private String name;

    @Min(0) @Max(10)
    private int topRank;

    @Min(0) @Max(10)
    private int rightRank;

    @Min(0) @Max(10)
    private int bottomRank;

    @Min(0) @Max(10)
    private int leftRank;

    private String obverseImageUrl;

    @Min(0) @Max(10)
    private int level;

    private Element element;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    @Override
    public String toString() {
        return "UpdateCreatureRequest{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", topRank=" + topRank +
                ", rightRank=" + rightRank +
                ", bottomRank=" + bottomRank +
                ", leftRank=" + leftRank +
                ", obverseImageUrl='" + obverseImageUrl + '\'' +
                ", level=" + level +
                ", element=" + element +
                '}';
    }

}
