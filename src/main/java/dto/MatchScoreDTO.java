package dto;

import model.score.GameScore;
import model.score.SetScore;
import model.score.TiebreakScore;

public record MatchScoreDTO(GameScore gameScore, SetScore setScore, TiebreakScore tiebreakScore) {
}
