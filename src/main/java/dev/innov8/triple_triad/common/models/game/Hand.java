package dev.innov8.triple_triad.common.models.game;

import dev.innov8.triple_triad.common.models.card.Card;

import javax.persistence.*;
import java.util.List;

// TODO: Review fields

@Entity
@Table(name = "game_hand")
public class Hand {

    @Id
    @Column(name = "game_hand_id")
    public String id;

    @ManyToMany
    @JoinTable(
            name = "game_hand_cards",
            joinColumns = @JoinColumn(name = "game_hand_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    public List<Card> cards;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

}
