package dto;

import lombok.Getter;

@Getter
public class SetScore {
    private int firstPlayerScore;
    private int secondPlayerScore;

    public void updateFirstPlayerScore(int newScore) {
        firstPlayerScore += newScore;
    }

    public void updateSecondPlayerScore(int newScore) {
        secondPlayerScore += newScore;
    }
}
