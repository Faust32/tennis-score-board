package service;

import exceptions.NotFoundModelException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import model.Match;

import java.util.List;

@RequiredArgsConstructor
public class CurrentMatchService {
    private final List<Match> matchesList;
    @Getter
    private static Long currentMatchId;

    public void addMatch(Match match) {
        matchesList.add(match);
        currentMatchId = match.getId();
    }

    public Match getCurrentMatch() {
        return matchesList.stream().filter(match -> match.getId().equals(currentMatchId))
                .findFirst()
                .orElseThrow(() -> new NotFoundModelException("There is no such match!"));
    }

    public void deleteCurrentMatch() {
        matchesList.removeIf(match -> match.getId().equals(currentMatchId));
    }
}
