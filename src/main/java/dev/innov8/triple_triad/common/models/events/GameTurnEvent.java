package dev.innov8.triple_triad.common.models.events;

import dev.innov8.triple_triad.common.models.game.Game;
import dev.innov8.triple_triad.common.models.user.AppUser;

import javax.persistence.*;

// TODO: Review fields

@Entity
@Table(name = "game_turn_events")
public class GameTurnEvent extends Event {

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "turn_player", nullable = false)
    private AppUser turnPlayer;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public AppUser getTurnPlayer() {
        return turnPlayer;
    }

    public void setTurnPlayer(AppUser turnPlayer) {
        this.turnPlayer = turnPlayer;
    }

}
