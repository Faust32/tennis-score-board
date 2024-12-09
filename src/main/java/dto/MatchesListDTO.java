package dto;

import model.Match;

import java.util.List;

public record MatchesListDTO(MatchPageDTO matchPageDTO, List<Match> matches) {
}
