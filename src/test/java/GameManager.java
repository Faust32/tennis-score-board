import model.score.Game;

public class GameManager {

    private final Game game;

    public GameManager(Game game) {
        this.game = game;
    }

    public void addSetPoint(Integer playerNumber) {
        game.addPoint(playerNumber);
        game.addPoint(playerNumber);
        game.addPoint(playerNumber);
        game.addPoint(playerNumber);
    }

    public void getTiebreak() {
        addSetPoint(1);
        addSetPoint(1);
        addSetPoint(1);
        addSetPoint(1);
        addSetPoint(1);

        addSetPoint(2);
        addSetPoint(2);
        addSetPoint(2);
        addSetPoint(2);
        addSetPoint(2);

        addSetPoint(1);
        addSetPoint(2);
    }

    public void addMatchPoint(Integer playerNumber) {
        addSetPoint(playerNumber);
        addSetPoint(playerNumber);
        addSetPoint(playerNumber);
        addSetPoint(playerNumber);
        addSetPoint(playerNumber);
        addSetPoint(playerNumber);
    }
}
