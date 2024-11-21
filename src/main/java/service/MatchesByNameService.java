package service;

import dao.MatchRepository;
import lombok.RequiredArgsConstructor;
import model.Match;

import java.util.List;

@RequiredArgsConstructor
public class MatchesByNameService {
    private final MatchRepository matchRepository;
    public List<Match> filterByName(String playerName) {
        if (playerName == null || playerName.isEmpty()) {
            return matchRepository.findAll();
        } else {
            return matchRepository.findByName(playerName);
        }
    }
}
