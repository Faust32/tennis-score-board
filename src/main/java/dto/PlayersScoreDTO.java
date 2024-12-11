package dto;

import lombok.Getter;
import lombok.Setter;
import model.score.Score;

@Getter
@Setter
public class PlayersScoreDTO {

    private String firstPlayerName;

    private String secondPlayerName;

    private Score score;

}
