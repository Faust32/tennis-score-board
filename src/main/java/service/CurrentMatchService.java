package service;

import exceptions.NotFoundModelException;
import lombok.RequiredArgsConstructor;
import model.Match;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class CurrentMatchService {
    private final List<Match> matchesList; //TODO: think about is it really worth it to have this list?
    private static UUID currentMatchId;

    public void addMatch(Match match) {
        matchesList.add(match);
        currentMatchId = match.getId();
    }

    public Match getCurrentMatch() {
        return matchesList.stream().filter(match -> match.getId().equals(currentMatchId))
                .findFirst()
                .orElseThrow(() -> new NotFoundModelException("There is no such match!"));
    }

}
