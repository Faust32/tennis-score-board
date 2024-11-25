package model.score;

import lombok.Getter;

import java.util.HashMap;

public abstract class Game {
    //TODO: make a method that converts current score to String type for match/set/game scores.
    @Getter
    protected State state;
    protected static Integer player1 = 1;
    protected static Integer player2 = 2;
    protected final HashMap<Integer, Integer> points;

    public Game() {
        points = new HashMap<>();
        state = State.NOTHING;
        points.put(player1, 0);
        points.put(player2, 0);
    }

    public abstract void updateState();

    public void addPoint(Integer toPlayer) {
        points.put(toPlayer, points.get(toPlayer) + 1);
        updateState();
    }

    public String getPlayersPoints(Integer player) {
        return points.get(player).toString();
    }
}
