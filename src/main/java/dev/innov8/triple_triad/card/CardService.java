package dev.innov8.triple_triad.card;

import dev.innov8.triple_triad.card.dtos.responses.CardResponse;
import dev.innov8.triple_triad.common.exceptions.InvalidRequestException;
import dev.innov8.triple_triad.common.exceptions.ResourceNotFoundException;
import dev.innov8.triple_triad.common.models.card.Card;
import dev.innov8.triple_triad.common.models.card.Element;
import dev.innov8.triple_triad.common.models.card.Type;
import dev.innov8.triple_triad.common.services.ResourceService;
import dev.innov8.triple_triad.common.datasource.EntitySearcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CardService extends ResourceService<Card> {

    private final CardRepository cardRepo;

    @Autowired
    public CardService(CardRepository cardRepo, EntitySearcher entitySearcher) {
        super(cardRepo, entitySearcher);
        this.cardRepo = cardRepo;
    }

    public List<CardResponse> getCardsByCreatureName(String name) {
        if (name == null || name.isEmpty()) throw new InvalidRequestException("Invalid name value provided.");
        Set<Card> matchingCards = cardRepo.findCardsByCreatureName(name);
        if (matchingCards.isEmpty()) throw new ResourceNotFoundException();
        return matchingCards.stream().map(CardResponse::new).collect(Collectors.toList());
    }

    public List<CardResponse> getCardsByCreatureLevel(int level) {
        if (level < 1 || level > 10) throw new InvalidRequestException("Invalid level value provided.");
        Set<Card> matchingCards = cardRepo.findCardsByCreatureLevel(level);
        if (matchingCards.isEmpty()) throw new ResourceNotFoundException();
        return matchingCards.stream().map(CardResponse::new).collect(Collectors.toList());
    }

    public List<CardResponse> getCardsByCreatureType(Type type) {
        if (type == null) throw new InvalidRequestException("Invalid type value provided.");
        Set<Card> matchingCards = cardRepo.findCardsByCreatureType(type);
        if (matchingCards.isEmpty()) throw new ResourceNotFoundException();
        return matchingCards.stream().map(CardResponse::new).collect(Collectors.toList());
    }

    public List<CardResponse> getCardsByCreatureElement(Element element) {
        if (element == null) throw new InvalidRequestException("Invalid element value provided.");
        Set<Card> matchingCards = cardRepo.findCardsByCreatureElement(element);
        if (matchingCards.isEmpty()) throw new ResourceNotFoundException();
        return matchingCards.stream().map(CardResponse::new).collect(Collectors.toList());
    }

    public List<CardResponse> getCardsByCreatureTopRank(int rank) {
        if (rank < 1 || rank > 10) throw new InvalidRequestException("Invalid rank value provided.");
        Set<Card> matchingCards = cardRepo.findCardsByCreatureTopRank(rank);
        if (matchingCards.isEmpty()) throw new ResourceNotFoundException();
        return matchingCards.stream().map(CardResponse::new).collect(Collectors.toList());
    }

    public List<CardResponse> getCardsByCreatureRightRank(int rank) {
        if (rank < 1 || rank > 10) throw new InvalidRequestException("Invalid rank value provided.");
        Set<Card> matchingCards = cardRepo.findCardsByCreatureRightRank(rank);
        if (matchingCards.isEmpty()) throw new ResourceNotFoundException();
        return matchingCards.stream().map(CardResponse::new).collect(Collectors.toList());
    }

    public List<CardResponse> getCardsByCreatureBottomRank(int rank) {
        if (rank < 1 || rank > 10) throw new InvalidRequestException("Invalid rank value provided.");
        Set<Card> matchingCards = cardRepo.findCardsByCreatureBottomRank(rank);
        if (matchingCards.isEmpty()) throw new ResourceNotFoundException();
        return matchingCards.stream().map(CardResponse::new).collect(Collectors.toList());
    }

    public List<CardResponse> getCardsByCreatureLeftRank(int rank) {
        if (rank < 1 || rank > 10) throw new InvalidRequestException("Invalid rank value provided.");
        Set<Card> matchingCards = cardRepo.findCardsByCreatureLeftRank(rank);
        if (matchingCards.isEmpty()) throw new ResourceNotFoundException();
        return matchingCards.stream().map(CardResponse::new).collect(Collectors.toList());
    }

    public List<CardResponse> getCardsWithCreatureTopRankAtLeast(int rank) {
        if (rank < 1 || rank > 10) throw new InvalidRequestException("Invalid rank value provided.");
        Set<Card> matchingCards = cardRepo.findCardsByCreatureTopRankGreaterThanEqual(rank);
        if (matchingCards.isEmpty()) throw new ResourceNotFoundException();
        return matchingCards.stream().map(CardResponse::new).collect(Collectors.toList());
    }

    public List<CardResponse> getCardsWithCreatureRightRankAtLeast(int rank) {
        if (rank < 1 || rank > 10) throw new InvalidRequestException("Invalid rank value provided.");
        Set<Card> matchingCards = cardRepo.findCardsByCreatureRightRankGreaterThanEqual(rank);
        if (matchingCards.isEmpty()) throw new ResourceNotFoundException();
        return matchingCards.stream().map(CardResponse::new).collect(Collectors.toList());
    }

    public List<CardResponse> getCardsWithCreatureBottomRankAtLeast(int rank) {
        if (rank < 1 || rank > 10) throw new InvalidRequestException("Invalid rank value provided.");
        Set<Card> matchingCards = cardRepo.findCardsByCreatureBottomRankGreaterThanEqual(rank);
        if (matchingCards.isEmpty()) throw new ResourceNotFoundException();
        return matchingCards.stream().map(CardResponse::new).collect(Collectors.toList());
    }

    public List<CardResponse> getCardsWithCreatureLeftRankAtLeast(int rank) {
        if (rank < 1 || rank > 10) throw new InvalidRequestException("Invalid rank value provided.");
        Set<Card> matchingCards = cardRepo.findCardsByCreatureLeftRankGreaterThanEqual(rank);
        if (matchingCards.isEmpty()) throw new ResourceNotFoundException();
        return matchingCards.stream().map(CardResponse::new).collect(Collectors.toList());
    }

    public List<CardResponse> getCardsByOwnerId(String ownerId) {
        if (ownerId == null || ownerId.length() != 36) throw new InvalidRequestException("Invalid owner id value provided.");
        Set<Card> matchingCards = cardRepo.findCardsByOwnerId(ownerId);
        if (matchingCards.isEmpty()) throw new ResourceNotFoundException();
        return matchingCards.stream().map(CardResponse::new).collect(Collectors.toList());
    }

    public CardResponse getCardById(String cardId) {
        if (cardId == null || cardId.length() != 36) throw new InvalidRequestException("Invalid card id value provided.");
        Card card = cardRepo.findById(cardId).orElseThrow(ResourceNotFoundException::new);
        return new CardResponse(card);
    }

}
