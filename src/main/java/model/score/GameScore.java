package model.score;

import static model.score.Game.player1;
import static model.score.Game.player2;

public class GameScore implements StateUpdater {

    @Override
    public void updateState(Game game) {
        int player1Score = game.gamePoints.get(player1);
        int player2Score = game.gamePoints.get(player2);
        if (Game.state.equals(State.NOTHING)) {
            if (player1Score >= 4 && player1Score - player2Score >= 2) {
                Game.state = State.FIRST_PLAYER_WON_GAME;
                resetScore(game);
            } else if (player2Score >= 4 && player2Score - player1Score >= 2) {
                Game.state = State.SECOND_PLAYER_WON_GAME;
                resetScore(game);
            }
            if (player1Score == 4 && player2Score == 4) {
                rollbackPlayersPoint(game);
            }
        }
    }

    private void resetScore(Game game) {
        game.gamePoints.replace(player1, 0);
        game.gamePoints.replace(player2, 0);
    }

    private void rollbackPlayersPoint(Game game) {
        game.gamePoints.replace(player1, 3);
        game.gamePoints.replace(player2, 3);
    }
}
