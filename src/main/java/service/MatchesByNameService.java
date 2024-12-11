package service;

import repository.MatchRepository;
import model.Match;

import java.util.List;

public class MatchesByNameService {

    private final MatchRepository matchRepository = new MatchRepository();

    public List<Match> filterByName(String playerName) {
        if (playerName == null || playerName.isEmpty() || playerName.equals("null")) {
            return matchRepository.findAll();
        } else {
            return matchRepository.findByName(playerName);
        }
    }
}
