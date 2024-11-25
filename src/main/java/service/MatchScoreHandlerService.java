package service;

import lombok.RequiredArgsConstructor;
import model.score.Game;
import model.score.GameScore;
import model.score.State;


@RequiredArgsConstructor
public class MatchScoreHandlerService {
    public Long updateScore(Long playerId) {
        Game game = new GameScore();
        State state = game.getState();
        game.addPoint(playerId.intValue());
        if (state.equals(State.FIRST_PLAYER_WON_MATCH) || state.equals(State.SECOND_PLAYER_WON_MATCH)) {
            if (state.equals(State.FIRST_PLAYER_WON_MATCH)) {
                return playerId;
            }
            return playerId;
        }
        return 0L;
    }
}
