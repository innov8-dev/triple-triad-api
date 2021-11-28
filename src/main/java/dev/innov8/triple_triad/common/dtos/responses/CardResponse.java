package dev.innov8.triple_triad.common.dtos.responses;

import dev.innov8.triple_triad.common.models.card.Card;

public class CardResponse implements ResourceResponse {

    private String cardId;
    private CreatureResponse creature;
    private UserResponse owner;

    public CardResponse(Card card) {
        this.cardId = card.getId();
        this.creature = new CreatureResponse(card.getCreature());
        this.owner = new UserResponse(card.getOwner());
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public CreatureResponse getCreature() {
        return creature;
    }

    public void setCreature(CreatureResponse creature) {
        this.creature = creature;
    }

    public UserResponse getOwner() {
        return owner;
    }

    public void setOwner(UserResponse owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "CardResponse{" +
                "cardId='" + cardId + '\'' +
                ", creature=" + creature +
                ", owner=" + owner +
                '}';
    }

}
