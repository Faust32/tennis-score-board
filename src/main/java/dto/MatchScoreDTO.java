package dto;

import model.Match;

public record MatchScoreDTO(Match match, PlayersScoreDTO playersScoreDTO) {
}
