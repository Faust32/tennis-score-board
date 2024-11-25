import lombok.RequiredArgsConstructor;
import model.score.Game;
import model.score.GameScore;
import model.score.State;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@RequiredArgsConstructor
public class GameScoreTest {
    Game game;
    @Test
    void testPointEnrollment() {
        game = new GameScore();
        game.addPoint(1);
        game.addPoint(1);
        game.addPoint(1);

        game.addPoint(2);
        game.addPoint(2);
        game.addPoint(2);

        game.addPoint(1);
        game.addPoint(2);
        Assertions.assertEquals("3", game.getPlayersPoints(1));
    }

    @Test
    void testScoreEnrollment() {
        game = new GameScore();
        game.addPoint(1);
        game.addPoint(1);
        game.addPoint(1);
        game.addPoint(1);
        Assertions.assertEquals(State.FIRST_PLAYER_WON_GAME, game.getState());
    }
}
