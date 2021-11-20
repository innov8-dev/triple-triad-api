package dev.innov8.triple_triad.common.models.card;

import dev.innov8.triple_triad.common.models.user.AppUser;

import javax.persistence.*;
import java.util.*;

// TODO: Review fields

@Entity
public class Deck {

    @Id
    @Column(name = "deck_id", nullable = false, unique = true)
    private String id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private AppUser owner;

    @OneToMany
    @JoinTable(
            name = "deck_cards",
            joinColumns = @JoinColumn(name = "deck_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "card_id", nullable = false)
    )
    private List<Card> cards;

    public Deck() {
        this.id = UUID.randomUUID().toString();
        this.owner = null;
        this.cards = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null) throw new IllegalStateException("Deck.id cannot be null");
        this.id = id;
    }

    public AppUser getOwner() {
        return owner;
    }

    public void setOwner(AppUser owner) {
        if (owner == null) throw new IllegalStateException("Deck.owner cannot be null");
        this.owner = owner;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        if (cards == null) throw new IllegalStateException("Deck.cards cannot be null");
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
        return Objects.equals(id, deck.id) && Objects.equals(owner, deck.owner) && Objects.equals(cards, deck.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, owner, cards);
    }

    @Override
    public String toString() {
        return "Deck{" +
                "id='" + id + '\'' +
                ", owner=" + owner +
                ", cards=" + cards +
                '}';
    }

}
