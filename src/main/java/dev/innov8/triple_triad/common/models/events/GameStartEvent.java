package dev.innov8.triple_triad.common.models.events;

import dev.innov8.triple_triad.common.models.game.Game;
import dev.innov8.triple_triad.common.models.user.AppUser;

import javax.persistence.*;

// TODO: Review fields

@Entity
@Table(name = "game_start_events")
public class GameStartEvent extends Event {

    @OneToOne
    @JoinColumn(name = "game_id", nullable = false, unique = true)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "player_1", nullable = false)
    private AppUser player1;

    @ManyToOne
    @JoinColumn(name = "player_2", nullable = false)
    private AppUser player2;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public AppUser getPlayer1() {
        return player1;
    }

    public void setPlayer1(AppUser player1) {
        this.player1 = player1;
    }

    public AppUser getPlayer2() {
        return player2;
    }

    public void setPlayer2(AppUser player2) {
        this.player2 = player2;
    }

}
