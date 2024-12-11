import model.score.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SetScoreTest {

    private final Game game = new Game();

    private final GameManager gameManager = new GameManager(game);

    @Test
    void testScoreEnrollment() {
        gameManager.addSetPoint(1);
        Assertions.assertEquals(1, game.getScore().getSetPoints().get(1));
    }
}
