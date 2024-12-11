package model.score;

import static model.score.Game.player1;
import static model.score.Game.player2;

public class TiebreakScore implements StateUpdater {

    @Override
    public void updateState(Game game) {
        if (Game.state.equals(State.TIEBREAK)) {
            int firstPlayerScore = game.gamePoints.get(player1);
            int secondPlayerScore = game.gamePoints.get(player2);
            if (firstPlayerScore >= 7 && firstPlayerScore - secondPlayerScore >= 2) {
                Game.state = State.FIRST_PLAYER_WON_SET;
            }
            if (secondPlayerScore >= 7 && secondPlayerScore - firstPlayerScore >= 2) {
                Game.state = State.SECOND_PLAYER_WON_SET;
            }
        }
    }
}
