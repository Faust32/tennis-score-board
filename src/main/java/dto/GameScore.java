package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameScore {
    private int firstPlayerPoints;
    private boolean isFirstPlayerAdvantage = false;
    private int secondPlayerPoints;
    private boolean isSecondPlayerAdvantage = false;

    public void updateFirstPlayerScore(int points) {
        firstPlayerPoints += points;
    }

    public void updateSecondPlayerScore(int points) {
        secondPlayerPoints += points;
    }
}
