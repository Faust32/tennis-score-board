package controller;

import dao.PlayerRepository;
import dto.MatchScoreDTO;

import model.Match;
import model.Player;
import jakarta.servlet.http.HttpServletRequest;
import service.CurrentMatchService;
import service.MatchScoreHandlerService;

import java.util.Optional;

public class MatchScoreController {
    private final MatchScoreHandlerService matchScoreHandlerService = new MatchScoreHandlerService();
    private final CurrentMatchService currentMatchService = CurrentMatchService.getInstance();
    private final PlayerRepository playerRepository  = new PlayerRepository();

    public void handle(HttpServletRequest request) {
        matchScoreHandlerService.setGame(currentMatchService.getGame());
        Long playerId = Long.valueOf(request.getParameter("round_winner"));
        Match match = currentMatchService.getCurrentMatch().match();
        boolean someoneWon = false;
        if (match.getPlayer1().getId().equals(playerId)) {
            someoneWon = matchScoreHandlerService.updateScore(1L);
        }
        if (match.getPlayer2().getId().equals(playerId)) {
            someoneWon = matchScoreHandlerService.updateScore(2L);
        }
        if (someoneWon) {
            Optional<Player> winner = playerRepository.findById(playerId);
            winner.ifPresent(match::setWinner);
            currentMatchService.updateMatch(match);
        }
        setAttributes(request);
    }

    public void setAttributes(HttpServletRequest request) {
        matchScoreHandlerService.setGame(currentMatchService.getGame());
        MatchScoreDTO matchScoreDTO = currentMatchService.getCurrentMatch();
        matchScoreDTO.playersScoreDTO().setScore(matchScoreHandlerService.getScore());
        request.setAttribute("matchScore", matchScoreDTO);
    }
}
