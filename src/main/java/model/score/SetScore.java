package model.score;

import lombok.Getter;

import java.util.HashMap;

@Getter
public class SetScore extends Game {
    private final HashMap<Integer, Integer> setPoints;

    public SetScore() {
        setPoints = new HashMap<>();
        setPoints.put(player1, 0);
        setPoints.put(player2, 0);
    }

    @Override
    public void updateState() {
        int player1Score = setPoints.get(player1);
        int player2Score = setPoints.get(player2);
        if (state.equals(State.FIRST_PLAYER_WON_GAME) || state.equals(State.SECOND_PLAYER_WON_GAME)) {
            if (state.equals(State.FIRST_PLAYER_WON_GAME)) {
                player1Score++;
                setPoints.put(player1, player1Score);
            } else {
                player2Score++;
                setPoints.put(player2, player2Score);
            }
            state = State.NOTHING;
            if (player1Score > player2Score && player1Score - player2Score >= 2) {
                state = State.FIRST_PLAYER_WON_SET;
            }
            if (player2Score > player1Score && player2Score - player1Score >= 2) {
                state = State.SECOND_PLAYER_WON_SET;
            }
            if (player1Score == 6 && player2Score == 6) {
                state = State.TIEBREAK;
            }
        }
    }
}
