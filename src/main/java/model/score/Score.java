package model.score;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
public class Score {
    @Setter
    private State state = Game.state;
    private final HashMap<Integer, Integer> matchPoints;
    private final HashMap<Integer, Integer> setPoints;
    private final HashMap<Integer, Integer> gamePoints;

    public Score() {
        matchPoints = new HashMap<>();
        matchPoints.put(1, 0);
        matchPoints.put(2, 0);
        setPoints = new HashMap<>();
        setPoints.put(1, 0);
        setPoints.put(2, 0);
        gamePoints = new HashMap<>();
        gamePoints.put(1, 0);
        gamePoints.put(2, 0);
    }

    public String getPlayerGamePoints(Integer player) {
        if (setPoints.get(1) == 6 && setPoints.get(2) == 6) {
            return gamePoints.get(player).toString();
        }
        return GamePoints.getValue(gamePoints.get(player));
    }

    public String getPlayerSetPoints(Integer player) {
        return setPoints.get(player).toString();
    }

    public String getPlayerMatchPoints(Integer player) {
        return matchPoints.get(player).toString();
    }

}
