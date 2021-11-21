package dev.innov8.triple_triad.card;

import dev.innov8.triple_triad.common.models.card.Card;
import dev.innov8.triple_triad.common.models.card.Type;

import static dev.innov8.triple_triad.common.util.Validator.*;

public class CardService {

    public boolean isCardValid(Card card) {
        if (isNull(card)) return false;
        if (isNullOrEmpty(card.getName())) return false;
        if (card.getTopRank() < 1 || card.getTopRank() > 10) return false;
        if (card.getRightRank() < 1 || card.getRightRank() > 10) return false;
        if (card.getBottomRank() < 1 || card.getBottomRank() > 10) return false;
        if (card.getLeftRank() < 1 || card.getLeftRank() > 10) return false;
        if (isNullOrEmpty(card.getObverseImageUrl()) || !isValidUrl(card.getObverseImageUrl())) return false;
        if (isNullOrEmpty(card.getReverseImageUrl()) || !isValidUrl(card.getReverseImageUrl())) return false;
        if (card.getLevel() <= 0 || card.getLevel() > 10) return false;
        if (isNull(card.getType())) return false;
        return cardLevelAndTypeMatches(card);
    }

    private boolean cardLevelAndTypeMatches(Card card) {
        int level = card.getLevel();
        Type type = card.getType();
        if (isNull(type)) return false;
        if (level <= 0 || level > 10) return false;
        if (level <= 5 && !type.equals(Type.MONSTER)) return false;
        if ((level == 6 || level == 7) && !type.equals(Type.BOSS)) return false;
        if ((level == 8 || level == 9) && !type.equals(Type.GF)) return false;
        return level != 10 || type.equals(Type.PLAYER);
    }

}
