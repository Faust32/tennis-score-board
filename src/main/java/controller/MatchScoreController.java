package controller;

import dao.PlayerRepository;
import model.Match;
import model.Player;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import service.CurrentMatchService;
import service.FinishedMatchesHandlerService;
import service.MatchScoreHandlerService;

import java.util.Optional;

@RequiredArgsConstructor
public class MatchScoreController {
    private final MatchScoreHandlerService matchScoreHandlerService;
    private final CurrentMatchService currentMatchService;
    private final FinishedMatchesHandlerService finishedMatchesHandlerService;
    private final PlayerRepository playerRepository;

    public void handle(HttpServletRequest request) {
        Long playerId = Long.valueOf(request.getParameter("round_winner"));
        Match match = currentMatchService.getCurrentMatch();
        Long result = 0L;
        if (match.getPlayer1().getId().equals(playerId)) {
            result = matchScoreHandlerService.updateScore(1L);
        }
        if (match.getPlayer2().getId().equals(playerId)) {
            result = matchScoreHandlerService.updateScore(2L);
        }
        if (result != 0L) {
            Optional<Player> winner = playerRepository.findById(playerId);
            winner.ifPresent(match::setWinner);
            finishedMatchesHandlerService.saveMatch();
        }
    }
}
