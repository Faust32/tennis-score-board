package model.score;

import static model.score.Game.player1;
import static model.score.Game.player2;

public class SetScore implements StateUpdater {

    @Override
    public void updateState(Game game) {
        int player1Score = game.setPoints.get(player1);
        int player2Score = game.setPoints.get(player2);
        if (Game.state.equals(State.FIRST_PLAYER_WON_GAME) || Game.state.equals(State.SECOND_PLAYER_WON_GAME)) {
            if (Game.state.equals(State.FIRST_PLAYER_WON_GAME)) {
                player1Score++;
                game.setPoints.put(player1, player1Score);
            } else {
                player2Score++;
                game.setPoints.put(player2, player2Score);
            }
            Game.state = State.NOTHING;
            if (player1Score >= 6 && player1Score > player2Score && player1Score - player2Score >= 2) {
                Game.state = State.FIRST_PLAYER_WON_SET;
                resetScore(game);
            }
            if (player2Score >= 6 && player2Score > player1Score && player2Score - player1Score >= 2) {
                Game.state = State.SECOND_PLAYER_WON_SET;
                resetScore(game);
            }
            if (player1Score == 6 && player2Score == 6) {
                Game.state = State.TIEBREAK;
            }
        }
    }

    private void resetScore(Game game) {
        game.setPoints.replace(1, 0);
        game.setPoints.replace(2, 0);
    }
}
