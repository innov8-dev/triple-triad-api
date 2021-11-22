package dev.innov8.triple_triad.card;

import dev.innov8.triple_triad.common.models.card.Card;
import dev.innov8.triple_triad.common.models.card.Element;
import dev.innov8.triple_triad.common.models.card.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, String> {

    List<Card> findCardsByCreatureName(String name);
    List<Card> findCardsByCreatureLevel(int level);
    List<Card> findCardsByCreatureType(Type type);
    List<Card> findCardsByCreatureElement(Element element);
    List<Card> findCardsByCreatureTopRank(int rankValue);
    List<Card> findCardsByCreatureRightRank(int rankValue);
    List<Card> findCardsByCreatureBottomRank(int rankValue);
    List<Card> findCardsByCreatureLeftRank(int rankValue);
    List<Card> findCardsByCreatureTopRankGreaterThanEqual(int rankValue);
    List<Card> findCardsByCreatureRightRankGreaterThanEqual(int rankValue);
    List<Card> findCardsByCreatureBottomRankGreaterThanEqual(int rankValue);
    List<Card> findCardsByCreatureLeftRankGreaterThanEqual(int rankValue);
    List<Card> findCardsByOwnerId(String ownerId);

}
