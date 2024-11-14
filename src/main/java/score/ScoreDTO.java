package score;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public abstract class ScoreDTO<T extends Number> {
    protected T firstPlayerScore;
    protected T secondPlayerScore;

    public void updateFirstPlayerScore(T newScore) {
        firstPlayerScore = sumScores(firstPlayerScore, newScore);
    }

    public void updateSecondPlayerScore(T newScore) {
        secondPlayerScore = sumScores(secondPlayerScore, newScore);
    }

    @SuppressWarnings("unchecked")
    private T sumScores(T score1, T score2) {
        if (score1 == null) {
            return score2;
        }
        if (score2 == null) {
            return score1;
        }
        if (score1 instanceof Integer && score2 instanceof Integer) {
            return (T) Integer.valueOf(score1.intValue() + score2.intValue());
        } else if (score1 instanceof Double && score2 instanceof Double) {
            return (T) Double.valueOf(score1.doubleValue() + score2.doubleValue());
        }
        throw new UnsupportedOperationException("Unsupported score type");
    }
}
