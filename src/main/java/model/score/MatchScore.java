package model.score;

import static model.score.Game.player1;
import static model.score.Game.player2;

public class MatchScore implements StateUpdater {
    @Override
    public void updateState(Game game) {
        int player1Score = game.matchPoints.get(player1);
        int player2Score = game.matchPoints.get(player2);
        if (Game.state.equals(State.FIRST_PLAYER_WON_SET) || Game.state.equals(State.SECOND_PLAYER_WON_SET)) {
            if (Game.state.equals(State.FIRST_PLAYER_WON_SET)) {
                player1Score++;
                game.matchPoints.put(player1, player1Score);
            } else {
                player2Score++;
                game.matchPoints.put(player2, player2Score);
            }
            resetGameScore(game);
            resetSetScore(game);
            Game.state = State.NOTHING;
            if (player1Score == 2) {
                Game.state = State.FIRST_PLAYER_WON_MATCH;
                game.getScore().setState(State.FIRST_PLAYER_WON_MATCH);
            }
            if (player2Score == 2) {
                Game.state = State.SECOND_PLAYER_WON_MATCH;
                game.getScore().setState(State.SECOND_PLAYER_WON_MATCH);
            }
        }
    }

    private void resetSetScore(Game game) {
        game.setPoints.replace(1, 0);
        game.setPoints.replace(2, 0);
    }

    private void resetGameScore(Game game) {
        game.gamePoints.replace(1, 0);
        game.gamePoints.replace(2, 0);
    }
}
