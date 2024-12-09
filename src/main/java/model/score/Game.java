package model.score;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;

public class Game {
    @Getter
    protected static State state;
    protected static Integer player1 = 1;
    protected static Integer player2 = 2;
    protected final HashMap<Integer, Integer> matchPoints;
    protected final HashMap<Integer, Integer> setPoints;
    protected final HashMap<Integer, Integer> gamePoints;

    @Getter
    protected final Score score;

    private final List<StateUpdater> stateUpdaters;

    public Game() {
        state = State.NOTHING;
        score = new Score();
        matchPoints = score.getMatchPoints();
        setPoints = score.getSetPoints();
        gamePoints = score.getGamePoints();
        stateUpdaters = List.of(new GameScore(), new SetScore(), new MatchScore(), new TiebreakScore());
    }

    public void addPoint(Integer toPlayer) {
        gamePoints.put(toPlayer, gamePoints.get(toPlayer) + 1);
        updateState();
    }

    private void updateState() {
        for (StateUpdater stateUpdater : stateUpdaters) {
            stateUpdater.updateState(this);
        }
    }
}
