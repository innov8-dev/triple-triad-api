package dev.innov8.triple_triad.models.card;

import dev.innov8.triple_triad.models.Resource;
import dev.innov8.triple_triad.models.user.AppUser;
import dev.innov8.triple_triad.common.util.RestResource;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.*;

@Entity
@Component
@RestResource
@Table(
    name = "decks",
    uniqueConstraints = { @UniqueConstraint(columnNames = { "name", "owner_id" }) }
)
public class Deck extends Resource {

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private AppUser owner;

    @OneToMany
    @JoinTable(
        name = "deck_cards",
        joinColumns = @JoinColumn(name = "deck_id"),
        inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    private Set<Card> cards;

    public Deck() {
        this.id = UUID.randomUUID().toString();
        this.owner = null;
        this.cards = new HashSet<>();
    }

    public Deck(String name) {
        this();
        this.name = name;
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

    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
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
