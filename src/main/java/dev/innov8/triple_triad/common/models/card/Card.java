package dev.innov8.triple_triad.common.models.card;

import javax.persistence.*;
import java.util.*;

import static dev.innov8.triple_triad.common.util.Validator.isNull;
import static dev.innov8.triple_triad.common.util.Validator.isNullOrEmpty;

@Entity
@Table(name = "cards")
public class Card {

    @Id
    @Column(
        name = "card_id",
        nullable = false,
        unique = true,
        columnDefinition = "VARCHAR CHECK (card_id <> '' AND CHAR_LENGTH(card_id) = 36)"
    )
    private String id;

    @Column(
        name = "name",
        nullable = false,
        columnDefinition = "VARCHAR CHECK name <> ''"
    )
    private String name;

    @Column(
        name = "top_rank",
        nullable = false,
        unique = true,
        columnDefinition = "INTEGER CHECK (top_rank > 0 AND top_rank < 11)"
    )
    private int topRank;

    @Column(
        name = "right_rank",
        nullable = false,
        columnDefinition = "INTEGER CHECK (right_rank > 0 AND right_rank < 11)"
    )
    private int rightRank;

    @Column(
        name = "bottom_rank",
        nullable = false,
        columnDefinition = "INTEGER CHECK (bottom_rank > 0 AND bottom_rank < 11)"
    )
    private int bottomRank;

    @Column(
        name = "left_rank",
        nullable = false,
        columnDefinition = "INTEGER CHECK (left_rank > 0 AND left_rank < 11)"
    )
    private int leftRank;

    @Column(
        name = "obverse_image_url",
        nullable = false,
        columnDefinition = "VARCHAR CHECK (SUBSTRING(obverse_image_url, 1, 8) = 'https://')"
    )
    private String obverseImageUrl;

    @Column(
        name = "reverse_image_url",
        nullable = false,
            columnDefinition = "VARCHAR CHECK (SUBSTRING(reverse_image_url, 1, 8) = 'https://')"
    )
    private String reverseImageUrl;

    @Column(
        name = "level",
        nullable = false,
        columnDefinition = "INTEGER CHECK (level > 0 AND level <= 10)"
    )
    private int level;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Enumerated(EnumType.STRING)
    private Element element;

    @ManyToOne
    @JoinColumn(name = "deck_id")
    private Deck deck;

    protected Card() {
        super();
    }

    private Card(String id, String name, int topRank, int rightRank, int bottomRank, int leftRank, String obverseImageUrl, String reverseImageUrl, int level, Type type, Element element, Deck deck) {
        this.id = id;
        this.name = name;
        this.topRank = topRank;
        this.rightRank = rightRank;
        this.bottomRank = bottomRank;
        this.leftRank = leftRank;
        this.obverseImageUrl = obverseImageUrl;
        this.reverseImageUrl = reverseImageUrl;
        this.level = level;
        this.type = type;
        this.element = element;
        this.deck = deck;
    }

    public String getId() {
        return id;
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

    public String getReverseImageUrl() {
        return reverseImageUrl;
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

    public Deck getDeck() {
        return deck;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return topRank == card.topRank &&
               rightRank == card.rightRank &&
               bottomRank == card.bottomRank &&
               leftRank == card.leftRank &&
               level == card.level &&
               Objects.equals(id, card.id) &&
               Objects.equals(name, card.name) &&
               Objects.equals(obverseImageUrl, card.obverseImageUrl) &&
               Objects.equals(reverseImageUrl, card.reverseImageUrl) &&
               type == card.type && element == card.element &&
               Objects.equals(deck, card.deck);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, topRank, rightRank, bottomRank, leftRank, obverseImageUrl, reverseImageUrl, level, type, element, deck);
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
                ", obverseImageUrl='" + obverseImageUrl + '\'' +
                ", reverseImageUrl='" + reverseImageUrl + '\'' +
                ", level=" + level +
                ", type=" + type +
                ", element=" + element +
                ", deckId=" + deck.getId() +
                '}';
    }

    public static class CardBuilder {
        private String id;
        private String name;
        private int topRank;
        private int rightRank;
        private int bottomRank;
        private int leftRank;
        private String obverseImageUrl;
        private String reverseImageUrl;
        private int level;
        private Type type;
        private Element element;
        private Deck deck;

        public CardBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public CardBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public CardBuilder setTopRank(int topRank) {
            this.topRank = topRank;
            return this;
        }

        public CardBuilder setRightRank(int rightRank) {
            this.rightRank = rightRank;
            return this;
        }

        public CardBuilder setBottomRank(int bottomRank) {
            this.bottomRank = bottomRank;
            return this;
        }

        public CardBuilder setLeftRank(int leftRank) {
            this.leftRank = leftRank;
            return this;
        }

        public CardBuilder setObverseImageUrl(String obverseImageUrl) {
            this.obverseImageUrl = obverseImageUrl;
            return this;
        }

        public CardBuilder setReverseImageUrl(String reverseImageUrl) {
            this.reverseImageUrl = reverseImageUrl;
            return this;
        }

        public CardBuilder setLevel(int level) {
            this.level = level;
            return this;
        }

        public CardBuilder setType(Type type) {
            this.type = type;
            return this;
        }

        public CardBuilder setElement(Element element) {
            this.element = element;
            return this;
        }

        public CardBuilder setDeck(Deck deck) {
            this.deck = deck;
            return this;
        }

        public Card build() {
            if (isNullOrEmpty(id)) id = UUID.randomUUID().toString();
            if (isNull(element)) element = Element.NONE;
            return new Card(id, this.name, topRank, rightRank, bottomRank, leftRank, obverseImageUrl, reverseImageUrl, level, type, element, deck);
        }

    }

}
