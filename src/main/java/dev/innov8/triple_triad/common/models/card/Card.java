package dev.innov8.triple_triad.common.models.card;

import javax.persistence.*;
import java.util.Objects;

// TODO: Review fields

@Entity
public class Card {

    @Id
    @Column(name = "column_id")
    private String id;

    private String name;

    @Column(name = "top_rank")
    private int topRank;

    @Column(name = "right_rank")
    private int rightRank;

    @Column(name = "bottom_rank")
    private int bottomRank;

    @Column(name = "left_rank")
    private int leftRank;

    @Column(name = "obverse_image")
    private String obverseImage;

    @Column(name = "reverse_image")
    private String reverseImage;

    @Embedded
    private Level level;

    @Enumerated(EnumType.STRING)
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

    public String getObverseImage() {
        return obverseImage;
    }

    public void setObverseImage(String obverseImage) {
        this.obverseImage = obverseImage;
    }

    public String getReverseImage() {
        return reverseImage;
    }

    public void setReverseImage(String reverseImage) {
        this.reverseImage = reverseImage;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return topRank == card.topRank && rightRank == card.rightRank && bottomRank == card.bottomRank && leftRank == card.leftRank && Objects.equals(id, card.id) && Objects.equals(name, card.name) && Objects.equals(obverseImage, card.obverseImage) && Objects.equals(reverseImage, card.reverseImage) && Objects.equals(level, card.level) && element == card.element;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, topRank, rightRank, bottomRank, leftRank, obverseImage, reverseImage, level, element);
    }

    @Override
    public String toString() {
        return "Card{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", topRank=" + topRank +
                ", rightRank=" + rightRank +
                ", bottomRank=" + bottomRank +
                ", leftRank=" + leftRank +
                ", obverseImage='" + obverseImage + '\'' +
                ", reverseImage='" + reverseImage + '\'' +
                ", level=" + level +
                ", element=" + element +
                '}';
    }

}
