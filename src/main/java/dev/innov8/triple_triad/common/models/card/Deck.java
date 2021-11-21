package dev.innov8.triple_triad.common.models.card;

import dev.innov8.triple_triad.common.models.user.AppUser;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(
    name = "decks",
    uniqueConstraints = { @UniqueConstraint(columnNames = { "name", "owner_id" }) }
)
public class Deck {

    @Id
    @Column(
        name = "deck_id",
        nullable = false,
        unique = true,
        columnDefinition = "VARCHAR CHECK deck_id <> '' AND CHAR_LENGTH(deck_id) = 36"
    )
    private String id;

    @Column(
        name = "name",
        nullable = false,
        columnDefinition = "VARCHAR CHECK name <> ''"
    )
    private String name;

    @ManyToOne
    @JoinColumn(
        name = "owner_id",
        nullable = false,
        columnDefinition = "VARCHAR CHECK owner_id <> '' AND CHAR_LENGTH(owner_id) = 36"
    )
    private AppUser owner;

    @OneToMany(
        fetch = FetchType.EAGER,
        mappedBy = "deck"
    )
    private List<Card> cards;

    public Deck() {
        this.id = UUID.randomUUID().toString();
        this.owner = null;
        this.cards = new ArrayList<>();
    }

    public Deck(String name) {
        this();
        this.name = name;
    }

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

    public AppUser getOwner() {
        return owner;
    }

    public void setOwner(AppUser owner) {
        this.owner = owner;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public void addCards(Card... cards) {
        this.cards.addAll(Arrays.asList(cards));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deck deck = (Deck) o;
        return Objects.equals(id, deck.id) && Objects.equals(name, deck.name) && Objects.equals(owner, deck.owner) && Objects.equals(cards, deck.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, owner, cards);
    }

    @Override
    public String toString() {
        return "Deck{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                ", cards=" + cards +
                '}';
    }
}
