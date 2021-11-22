package dev.innov8.triple_triad.card;

import dev.innov8.triple_triad.common.dtos.CardListResponse;
import dev.innov8.triple_triad.common.dtos.SingleCardResponse;
import dev.innov8.triple_triad.common.exceptions.InvalidRequestException;
import dev.innov8.triple_triad.common.exceptions.ResourceNotFoundException;
import dev.innov8.triple_triad.common.models.card.Card;
import dev.innov8.triple_triad.common.models.card.Creature;
import dev.innov8.triple_triad.common.models.card.Element;
import dev.innov8.triple_triad.common.models.card.Type;
import dev.innov8.triple_triad.common.util.EntitySearcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static dev.innov8.triple_triad.common.util.Validator.*;

@Service
public class CardService {

    private final CardRepository cardRepo;
    private final EntitySearcher entitySearcher;

    @Autowired
    public CardService(CardRepository cardRepo, EntitySearcher entitySearcher) {
        this.cardRepo = cardRepo;
        this.entitySearcher = entitySearcher;
    }

    public CardListResponse searchCards(Map<String, String> requestParamMap) {

        if (requestParamMap.isEmpty()) return getAllCards();

        Set<Card> matchingUsers = entitySearcher.searchForEntity(requestParamMap, Card.class);

        if (matchingUsers.isEmpty()) throw new ResourceNotFoundException();

        return new CardListResponse(matchingUsers);

    }

    public CardListResponse getAllCards() {
        return new CardListResponse(cardRepo.findAll());
    }

    public CardListResponse getCardsByCreatureName(String name) {
        if (isNullOrEmpty(name)) throw new InvalidRequestException("Invalid name value provided.");
        return new CardListResponse(cardRepo.findCardsByCreatureName(name));
    }

    public CardListResponse getCardsByCreatureLevel(int level) {
        if (level < 1 || level > 10) throw new InvalidRequestException("Invalid level value provided.");
        return new CardListResponse(cardRepo.findCardsByCreatureLevel(level));
    }

    public CardListResponse getCardsByCreatureType(Type type) {
        if (isNull(type)) throw new InvalidRequestException("Invalid type value provided.");
        return new CardListResponse(cardRepo.findCardsByCreatureType(type));
    }

    public CardListResponse getCardsByCreatureElement(Element element) {
        if (isNull(element)) throw new InvalidRequestException("Invalid element value provided.");
        return new CardListResponse(cardRepo.findCardsByCreatureElement(element));
    }

    public CardListResponse getCardsByCreatureTopRank(int rank) {
        if (rank < 1 || rank > 10) throw new InvalidRequestException("Invalid rank value provided.");
        List<Card> cards = cardRepo.findCardsByCreatureTopRank(rank);
        if (cards.isEmpty()) throw new ResourceNotFoundException();
        return new CardListResponse(cards);
    }

    public CardListResponse getCardsByCreatureRightRank(int rank) {
        if (rank < 1 || rank > 10) throw new InvalidRequestException("Invalid rank value provided.");
        List<Card> cards = cardRepo.findCardsByCreatureRightRank(rank);
        if (cards.isEmpty()) throw new ResourceNotFoundException();
        return new CardListResponse(cards);
    }

    public CardListResponse getCardsByCreatureBottomRank(int rank) {
        if (rank < 1 || rank > 10) throw new InvalidRequestException("Invalid rank value provided.");
        List<Card> cards = cardRepo.findCardsByCreatureBottomRank(rank);
        if (cards.isEmpty()) throw new ResourceNotFoundException();
        return new CardListResponse(cards);
    }

    public CardListResponse getCardsByCreatureLeftRank(int rank) {
        if (rank < 1 || rank > 10) throw new InvalidRequestException("Invalid rank value provided.");
        List<Card> cards = cardRepo.findCardsByCreatureLeftRank(rank);
        if (cards.isEmpty()) throw new ResourceNotFoundException();
        return new CardListResponse(cards);
    }

    public CardListResponse getCardsWithCreatureTopRankAtLeast(int rank) {
        if (rank < 1 || rank > 10) throw new InvalidRequestException("Invalid rank value provided.");
        List<Card> cards = cardRepo.findCardsByCreatureTopRankGreaterThanEqual(rank);
        if (cards.isEmpty()) throw new ResourceNotFoundException();
        return new CardListResponse(cards);
    }

    public CardListResponse getCardsWithCreatureRightRankAtLeast(int rank) {
        if (rank < 1 || rank > 10) throw new InvalidRequestException("Invalid rank value provided.");
        List<Card> cards = cardRepo.findCardsByCreatureRightRankGreaterThanEqual(rank);
        if (cards.isEmpty()) throw new ResourceNotFoundException();
        return new CardListResponse(cards);
    }

    public CardListResponse getCardsWithCreatureBottomRankAtLeast(int rank) {
        if (rank < 1 || rank > 10) throw new InvalidRequestException("Invalid rank value provided.");
        List<Card> cards = cardRepo.findCardsByCreatureBottomRankGreaterThanEqual(rank);
        if (cards.isEmpty()) throw new ResourceNotFoundException();
        return new CardListResponse(cards);
    }

    public CardListResponse getCardsWithCreatureLeftRankAtLeast(int rank) {
        if (rank < 1 || rank > 10) throw new InvalidRequestException("Invalid rank value provided.");
        List<Card> cards = cardRepo.findCardsByCreatureLeftRankGreaterThanEqual(rank);
        if (cards.isEmpty()) throw new ResourceNotFoundException();
        return new CardListResponse(cards);
    }

    public CardListResponse getCardsByOwnerId(String ownerId) {
        if (!isValidId(ownerId)) throw new InvalidRequestException("Invalid owner id value provided.");
        List<Card> cards = cardRepo.findCardsByOwnerId(ownerId);
        if (cards.isEmpty()) throw new ResourceNotFoundException();
        return new CardListResponse(cards);
    }

    public SingleCardResponse getCardById(String cardId) {
        if (!isValidId(cardId)) throw new InvalidRequestException("Invalid card id value provided.");
        Card card = cardRepo.findById(cardId).orElseThrow(ResourceNotFoundException::new);
        return new SingleCardResponse(card);
    }

    public String saveNewCard(Card newCard) {
        if (!isCardValid(newCard)) throw new InvalidRequestException("Invalid card object provided.");
        cardRepo.save(newCard);
        return newCard.getId();
    }

    public boolean updateCard(Card updatedCard) {
        if (!isCardValid(updatedCard)) throw new InvalidRequestException("Invalid card object provided.");
        cardRepo.save(updatedCard);
        return true;
    }

    public boolean isCardValid(Card card) {
        if (isNull(card)) return false;
        if (isNull(card.getCreature())) return false;

        Creature cardCreature = card.getCreature();
        if (isNullOrEmpty(cardCreature.getName())) return false;
        if (cardCreature.getTopRank() < 1 || cardCreature.getTopRank() > 10) return false;
        if (cardCreature.getRightRank() < 1 || cardCreature.getRightRank() > 10) return false;
        if (cardCreature.getBottomRank() < 1 || cardCreature.getBottomRank() > 10) return false;
        if (cardCreature.getLeftRank() < 1 || cardCreature.getLeftRank() > 10) return false;
        if (isNullOrEmpty(cardCreature.getObverseImageUrl()) || !isValidUrl(cardCreature.getObverseImageUrl())) return false;
        if (isNullOrEmpty(cardCreature.getReverseImageUrl()) || !isValidUrl(cardCreature.getReverseImageUrl())) return false;
        if (cardCreature.getLevel() <= 0 || cardCreature.getLevel() > 10) return false;
        if (isNull(cardCreature.getType())) return false;
        return cardLevelAndTypeMatches(cardCreature.getLevel(), cardCreature.getType());
    }

    private boolean cardLevelAndTypeMatches(int level, Type type) {
        if (isNull(type)) return false;
        if (level <= 0 || level > 10) return false;
        if (level <= 5 && !type.equals(Type.MONSTER)) return false;
        if ((level == 6 || level == 7) && !type.equals(Type.BOSS)) return false;
        if ((level == 8 || level == 9) && !type.equals(Type.GF)) return false;
        return level != 10 || type.equals(Type.PLAYER);
    }

}
