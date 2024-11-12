package controller;

import dto.RoundWinnerDTO;
import dto.GameScore;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import service.CurrentMatchService;
import service.MatchScoreHandlerService;

@RequiredArgsConstructor
public class MatchScoreController {
    private final GameScore gameScore;
    private final MatchScoreHandlerService matchScoreHandlerService;
    public void handle(HttpServletRequest request) {
        Long playerId = Long.valueOf(request.getParameter("roundWinner"));
        Long matchId = CurrentMatchService.getCurrentMatchId();
        RoundWinnerDTO roundWinnerDTO = new RoundWinnerDTO(playerId, gameScore);
        matchScoreHandlerService.updateScore(roundWinnerDTO);
    }
}
