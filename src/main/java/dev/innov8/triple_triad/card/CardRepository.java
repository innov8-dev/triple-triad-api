package dev.innov8.triple_triad.card;

import dev.innov8.triple_triad.common.models.card.Card;
import dev.innov8.triple_triad.common.models.card.Element;
import dev.innov8.triple_triad.common.models.card.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Set;

public interface CardRepository /*extends JpaRepository<Card, String>*/ {

    Set<Card> findCardsByCreatureName(String name);
    Set<Card> findCardsByCreatureLevel(int level);
    Set<Card> findCardsByCreatureType(Type type);
    Set<Card> findCardsByCreatureElement(Element element);
    Set<Card> findCardsByCreatureTopRank(int rankValue);
    Set<Card> findCardsByCreatureRightRank(int rankValue);
    Set<Card> findCardsByCreatureBottomRank(int rankValue);
    Set<Card> findCardsByCreatureLeftRank(int rankValue);
    Set<Card> findCardsByCreatureTopRankGreaterThanEqual(int rankValue);
    Set<Card> findCardsByCreatureRightRankGreaterThanEqual(int rankValue);
    Set<Card> findCardsByCreatureBottomRankGreaterThanEqual(int rankValue);
    Set<Card> findCardsByCreatureLeftRankGreaterThanEqual(int rankValue);
    Set<Card> findCardsByOwnerId(String ownerId);

}
