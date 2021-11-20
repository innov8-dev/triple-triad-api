package dev.innov8.triple_triad.common.models.game;

import javax.persistence.*;

// TODO: Review fields

@Entity
@Table(name = "games")
public class Game {

    @Id
    @Column(name = "game_id", nullable = false, unique = true)
    private String id;

    @OneToOne
    @JoinColumn(name = "player_one_hand_id", nullable = false, unique = true)
    private Hand playerOneHand;

    @OneToOne
    @JoinColumn(name = "player_two_hand_id", nullable = false, unique = true)
    private Hand playerTwoHand;

    @Enumerated(EnumType.STRING)
    private GameState gameState;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Hand getPlayerOneHand() {
        return playerOneHand;
    }

    public void setPlayerOneHand(Hand playerOneHand) {
        this.playerOneHand = playerOneHand;
    }

    public Hand getPlayerTwoHand() {
        return playerTwoHand;
    }

    public void setPlayerTwoHand(Hand playerTwoHand) {
        this.playerTwoHand = playerTwoHand;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

}
