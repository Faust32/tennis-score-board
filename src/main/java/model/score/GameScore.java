package model.score;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class GameScore extends ScoreDTO<Integer> {
    private boolean isFirstPlayerAdvantage = false;
    private boolean isSecondPlayerAdvantage = false;
}
