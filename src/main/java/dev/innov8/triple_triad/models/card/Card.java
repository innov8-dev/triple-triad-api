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
@Table(name = "cards")
public class Card extends Resource {

    @ManyToOne
    @JoinColumn(name = "creature_id")
    private Creature creature;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private AppUser owner;

    @Column(
        name = "reverse_image_url",
        nullable = false,
        columnDefinition = "VARCHAR DEFAULT 'https://triple-triad-card-images.s3.amazonaws.com/card-reversed.png'"
    )
    private String reverseImageUrl;

    public Card() {
        super();
        this.reverseImageUrl = "https://triple-triad-card-images.s3.amazonaws.com/card-reversed.png";
    }

    public Card(String id, Creature creature, AppUser owner) {
        this.id = id;
        this.creature = creature;
        this.owner = owner;
        this.reverseImageUrl = "https://triple-triad-card-images.s3.amazonaws.com/card-reversed.png";
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

    public String getReverseImageUrl() {
        return reverseImageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(creature, card.creature) && Objects.equals(owner, card.owner) && Objects.equals(reverseImageUrl, card.reverseImageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creature, owner, reverseImageUrl);
    }

    @Override
    public String toString() {
        return "Card{" +
                "creature=" + creature +
                ", owner=" + owner +
                ", reverseImageUrl='" + reverseImageUrl + '\'' +
                '}';
    }

}
