import model.score.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ScoreTest {

    @Nested
    class GameScoreTest {

        private final Game game = new Game();

        @Test
        void testGameScoreEnrollment() {
            game.addPoint(1);
            Assertions.assertEquals("15", game.getScore().getPlayerGamePoints(1));
        }

        @Test
        void testAdvantageWork() {
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
    }

    @Nested
    class SetScoreTest {

        private final Game game = new Game();

        private final GameManager gameManager = new GameManager(game);

        @Test
        void testScoreEnrollment() {
            gameManager.addSetPoint(1);
            Assertions.assertEquals(1, game.getScore().getSetPoints().get(1));
        }
    }

    @Nested
    class TiebreakTest {

        private final Game game = new Game();

        private final GameManager gameManager = new GameManager(game);

        @Test
        void testTiebreakEnrollment() {
            gameManager.getTiebreak();

            game.addPoint(1);
            game.addPoint(1);
            game.addPoint(1);
            game.addPoint(1);

            Assertions.assertEquals("4", game.getScore().getPlayerGamePoints(1));
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

    @Nested
    class MatchTest {

        private final Game game = new Game();

        private final GameManager gameManager = new GameManager(game);

        @Test
        void testMatchEnrollment() {
            gameManager.addMatchPoint(1);
            Assertions.assertEquals(1, game.getScore().getMatchPoints().get(1));
        }

        @Test
        void testMatchWinner() {
            gameManager.addMatchPoint(1);
            gameManager.addMatchPoint(1);
            Assertions.assertEquals(State.FIRST_PLAYER_WON_MATCH, game.getScore().getState());
        }
    }
}
