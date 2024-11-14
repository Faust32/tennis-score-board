package dto;

import score.GameScore;
import score.SetScore;
import score.TiebreakScore;

public record MatchScoreDTO(GameScore gameScore, SetScore setScore, TiebreakScore tiebreakScore) {
}
