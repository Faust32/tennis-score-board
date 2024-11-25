package model.score;

import lombok.Getter;

@Getter
public class GameScore extends Game {

    private void resetScore() {
        points.replace(player1, 0);
        points.replace(player2, 0);
    }

    @Override
    public void updateState() {
        int player1Score = points.get(player1);
        int player2Score = points.get(player2);
        if (state.equals(State.NOTHING)) {
            if (player1Score > 3 && player1Score - player2Score >= 2) {
                state = State.FIRST_PLAYER_WON_GAME;
                resetScore();
            } else if (player2Score > 3 && player2Score - player1Score >= 2) {
                state = State.SECOND_PLAYER_WON_GAME;
                resetScore();
            }
            if (player1Score == 4 && player2Score == 4) {
                rollbackPlayersPoint();
            }
        }
    }

    private void rollbackPlayersPoint() {
        points.replace(player1, 3);
        points.replace(player2, 3);
    }

    private enum Point {
        NONE("0"),
        FIFTEEN("15"),
        THIRTY("30"),
        FOURTY("40"),
        ADVANTAGE("AD");

        Point(String number) {
        }

        static Point getNextValue(Point currentPoint) {
            Point[] values = Point.values();
            int index = currentPoint.ordinal() + 1;
            if (index >= values.length) {
                index = 0;
            }
            return values[index];
        }

    }
}
