package dev.innov8.triple_triad.creature.dtos.requests;

import dev.innov8.triple_triad.common.models.card.Creature;
import dev.innov8.triple_triad.common.models.card.Element;
import dev.innov8.triple_triad.common.web.ResourceRequest;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class UpdateCreatureRequest implements ResourceRequest<Creature> {

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

    private String requestingUserId;

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

    public String getRequestingUserId() {
        return requestingUserId;
    }

    public void setRequestingUserId(String requestingUserId) {
        this.requestingUserId = requestingUserId;
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
                ", requestingUserId='" + requestingUserId + '\'' +
                '}';
    }

    @Override
    public Creature extract() {
        Creature creature = new Creature();
        creature.setId(id);
        creature.setName(name)
                .setTopRank(topRank)
                .setRightRank(rightRank)
                .setBottomRank(bottomRank)
                .setLeftRank(leftRank)
                .setImageUrl(obverseImageUrl)
                .setLevel(level)
                .setElement(element);
        return creature;
    }
}
