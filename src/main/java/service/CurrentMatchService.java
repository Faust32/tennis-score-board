package service;

import dao.MatchRepository;
import model.Match;

import java.util.UUID;

public class CurrentMatchService {
    private final MatchRepository matchRepository = new MatchRepository();
    private static UUID currentMatchId;

    public void addMatch(Match match) {
        currentMatchId = match.getId();
    }

    public Match getCurrentMatch() {
        return matchRepository.findById(currentMatchId).orElseThrow();
    }
}
