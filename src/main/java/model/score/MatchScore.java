package model.score;

import java.util.HashMap;

public class MatchScore extends Game {
    private final HashMap<Integer, Integer> matchPoints;

    public MatchScore() {
        matchPoints = new HashMap<>();
        matchPoints.put(player1, 0);
        matchPoints.put(player2, 0);
    }
    @Override
    public void updateState() {
        int player1Score = matchPoints.get(player1);
        int player2Score = matchPoints.get(player2);
        if (state.equals(State.FIRST_PLAYER_WON_SET) || state.equals(State.SECOND_PLAYER_WON_SET)) {
            if (state.equals(State.FIRST_PLAYER_WON_SET)) {
                player1Score++;
                matchPoints.put(player1, player1Score);
            } else {
                player2Score++;
                matchPoints.put(player2, player2Score);
            }
            state = State.NOTHING;
            if (player1Score == 2) {
                state = State.FIRST_PLAYER_WON_MATCH;
            }
            if (player2Score == 2) {
                state = State.SECOND_PLAYER_WON_MATCH;
            }
        }
    }
}
