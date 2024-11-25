package model.score;

public class TiebreakScore extends Game {

    @Override
    public void updateState() {
        if (state.equals(State.TIEBREAK)) {
            int firstPlayerScore = points.get(player1);
            int secondPlayerScore = points.get(player2);
            if (firstPlayerScore >= 7 && firstPlayerScore - secondPlayerScore >= 2) {
                state = State.FIRST_PLAYER_WON_GAME;
            }
            if (secondPlayerScore >= 7 && secondPlayerScore - firstPlayerScore >= 2) {
                state = State.SECOND_PLAYER_WON_GAME;
            }
        }
    }
}
