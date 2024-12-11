import model.score.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TiebreakTest {

    private final Game game = new Game();

    private final GameManager gameManager = new GameManager(game);

    @Test
    void testTiebreakEnrollment() {
        gameManager.getTiebreak();

        game.addPoint(1);
        game.addPoint(2);
        game.addPoint(1);
        game.addPoint(2);

        Assertions.assertEquals("2", game.getScore().getPlayerGamePoints(1));
    }

    @Test
    void testTiebreakEnrollmentCorrectness() {
        gameManager.getTiebreak();

        game.addPoint(1);
        game.addPoint(2);

        game.addPoint(1);
        game.addPoint(2);

        game.addPoint(1);
        game.addPoint(2);

        game.addPoint(1);
        game.addPoint(2);

        game.addPoint(1);
        game.addPoint(2);

        game.addPoint(1);
        game.addPoint(2);

        game.addPoint(1);
        game.addPoint(2);

        game.addPoint(1);
        game.addPoint(2);

        game.addPoint(2);
        game.addPoint(2);

        Assertions.assertEquals("1", game.getScore().getPlayerMatchPoints(2));
    }
}
