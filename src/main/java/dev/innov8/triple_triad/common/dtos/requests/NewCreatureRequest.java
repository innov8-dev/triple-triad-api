package dev.innov8.triple_triad.common.dtos.requests;

import dev.innov8.triple_triad.common.models.card.*;
import dev.innov8.triple_triad.common.util.ValidCreatureLevelType;
import dev.innov8.triple_triad.common.util.Url;
import dev.innov8.triple_triad.common.web.ResourceRequest;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ValidCreatureLevelType
public class NewCreatureRequest extends ResourceRequest<Creature> {

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
    private String obverseImageUrl;

    @Min(1) @Max(10)
    private int level;

    @NotNull
    private Type type;

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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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
                ", obverseImageUrl='" + obverseImageUrl + '\'' +
                ", level=" + level +
                ", type='" + type + '\'' +
                ", element='" + element + '\'' +
                '}';
    }

    @Override
    public Creature extract() {
        return new CreatureBuilder()
                            .setName(name)
                            .setTopRank(topRank)
                            .setRightRank(rightRank)
                            .setBottomRank(bottomRank)
                            .setLeftRank(leftRank)
                            .setImageUrl(obverseImageUrl)
                            .setType(type)
                            .setElement(element)
                            .build();
    }
}
