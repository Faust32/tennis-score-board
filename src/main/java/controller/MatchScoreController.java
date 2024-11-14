package controller;

import dto.MatchScoreDTO;
import dto.RoundWinnerDTO;
import score.GameScore;
import score.SetScore;
import score.TiebreakScore;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import service.CurrentMatchService;
import service.MatchScoreHandlerService;

@RequiredArgsConstructor
public class MatchScoreController {
    private final GameScore gameScore;
    private final SetScore setScore;
    private final TiebreakScore tiebreakScore;
    private final MatchScoreHandlerService matchScoreHandlerService;
    public void handle(HttpServletRequest request) {
        Long playerId = Long.valueOf(request.getParameter("roundWinner"));
        Long matchId = CurrentMatchService.getCurrentMatchId();
        MatchScoreDTO matchScoreDTO = new MatchScoreDTO(gameScore, setScore, tiebreakScore);
        RoundWinnerDTO roundWinnerDTO = new RoundWinnerDTO(playerId, matchScoreDTO);
        matchScoreHandlerService.updateScore(roundWinnerDTO);
    }
}
