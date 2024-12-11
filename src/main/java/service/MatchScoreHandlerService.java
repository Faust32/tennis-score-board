package service;

import lombok.Setter;
import model.score.Game;
import model.score.Score;
import model.score.State;

@Setter
public class MatchScoreHandlerService {

    private Game game;

    public boolean updateScore(Long playerId) {
        game.addPoint(playerId.intValue());
        State state = Game.getState();
        return state.equals(State.FIRST_PLAYER_WON_MATCH) || state.equals(State.SECOND_PLAYER_WON_MATCH);
    }

    public Score getScore() {
        return game.getScore();
    }
}
