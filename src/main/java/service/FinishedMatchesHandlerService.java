package service;

import dao.MatchRepository;
import lombok.RequiredArgsConstructor;
import model.Match;

@RequiredArgsConstructor
public class FinishedMatchesHandlerService {
    private final MatchRepository matchRepository;
    private final CurrentMatchService currentMatchService;
    public void saveMatch() {
        Match match = currentMatchService.getCurrentMatch();
        matchRepository.save(match);
    }
}
