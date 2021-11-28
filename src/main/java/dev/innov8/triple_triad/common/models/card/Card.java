package dev.innov8.triple_triad.common.models.card;

import dev.innov8.triple_triad.common.models.Resource;
import dev.innov8.triple_triad.common.models.user.AppUser;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "cards")
public class Card extends Resource {

    @ManyToOne
    @JoinColumn(name = "creature_id")
    private Creature creature;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private AppUser owner;

    public Card() {
        super();
    }

    public Card(String id, Creature creature, AppUser owner) {
        this.id = id;
        this.creature = creature;
        this.owner = owner;
    }

    public Creature getCreature() {
        return creature;
    }

    public void setCreature(Creature creature) {
        this.creature = creature;
    }

    public AppUser getOwner() {
        return owner;
    }

    public void setOwner(AppUser owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(id, card.id) && Objects.equals(creature, card.creature) && Objects.equals(owner, card.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creature, owner);
    }

    @Override
    public String toString() {
        return "Card{" +
                "id='" + id + '\'' +
                ", creature=" + creature +
                ", owner=" + owner +
                '}';
    }

}
