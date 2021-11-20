package dev.innov8.triple_triad.common.models.events;

import dev.innov8.triple_triad.common.models.game.Game;
import dev.innov8.triple_triad.common.models.game.GameResult;

import javax.persistence.*;

// TODO: Review fields

@Entity
@Table(name = "game_end_events")
public class GameEndEvent extends Event {

    @OneToOne
    @JoinColumn(name = "game_id", nullable = false, unique = true)
    private Game game;

    @Enumerated(EnumType.STRING)
    private GameResult gameResult;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public GameResult getGameResult() {
        return gameResult;
    }

    public void setGameResult(GameResult gameResult) {
        this.gameResult = gameResult;
    }

}
