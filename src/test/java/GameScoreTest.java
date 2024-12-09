import lombok.RequiredArgsConstructor;
import model.score.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


@RequiredArgsConstructor
public class GameScoreTest {
    Game game = new Game();
    @Test
    void testPointEnrollment() {
        game.addPoint(1);
        game.addPoint(1);
        game.addPoint(1);

        game.addPoint(2);
        game.addPoint(2);
        game.addPoint(2);

        game.addPoint(1);
        game.addPoint(2);
        Assertions.assertEquals("40", game.getScore().getPlayerGamePoints(1));
    }

    @Test
    void testScoreEnrollment() {
        game.addPoint(1);
        game.addPoint(1);
        game.addPoint(1);
        game.addPoint(1);
        Assertions.assertEquals(1, game.getScore().getSetPoints().get(1));
    }

    @Test
    void testTiebreak() {
        for (int i = 0; i < 20; i++) {
            game.addPoint(1);
        }
        for (int i = 0; i < 20; i++) {
            game.addPoint(2);
        }
        game.addPoint(1);
        game.addPoint(1);
        game.addPoint(1);
        game.addPoint(1);

        game.addPoint(2);
        game.addPoint(2);
        game.addPoint(2);
        game.addPoint(2);

        game.addPoint(2);
        game.addPoint(2);
        Assertions.assertEquals(2, game.getScore().getGamePoints().get(2));
    }

}
