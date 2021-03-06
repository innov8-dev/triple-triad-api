package dev.innov8.triple_triad.common.web.dtos.requests;

import dev.innov8.triple_triad.models.card.Creature;
import dev.innov8.triple_triad.models.card.Element;
import dev.innov8.triple_triad.common.util.Url;
import dev.innov8.triple_triad.common.web.dtos.requests.ResourceRequest;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class NewCreatureRequest implements ResourceRequest<Creature> {

    @NotBlank
    private String name;

    @Min(1) @Max(10)
    private int topRank;

    @Min(1) @Max(10)
    private int rightRank;

    @Min(1) @Max(10)
    private int bottomRank;

    @Min(1) @Max(10)
    private int leftRank;

    @Url
    private String imageUrl;

    @Min(1) @Max(10)
    private int level;

    private Element element;

    public NewCreatureRequest() {
        super();
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String obverseImageUrl) {
        this.imageUrl = obverseImageUrl;
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
        return "NewCreatureRequest{" +
                "name='" + name + '\'' +
                ", topRank=" + topRank +
                ", rightRank=" + rightRank +
                ", bottomRank=" + bottomRank +
                ", leftRank=" + leftRank +
                ", imageUrl='" + imageUrl + '\'' +
                ", level=" + level +
                ", element='" + element + '\'' +
                '}';
    }

    @Override
    public Creature extract() {
        return new Creature()
                        .setName(name)
                        .setTopRank(topRank)
                        .setRightRank(rightRank)
                        .setBottomRank(bottomRank)
                        .setLeftRank(leftRank)
                        .setImageUrl(imageUrl)
                        .setLevel(level)
                        .setElement(element);
    }
}
