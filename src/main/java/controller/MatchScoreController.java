package controller;

import dto.MatchScoreDTO;
import dto.RoundWinnerDTO;
import model.score.GameScore;
import model.score.SetScore;
import model.score.TiebreakScore;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import service.FinishedMatchesHandlerService;
import service.MatchScoreHandlerService;

@RequiredArgsConstructor
public class MatchScoreController {
    private final GameScore gameScore;
    private final SetScore setScore;
    private final TiebreakScore tiebreakScore;
    private final MatchScoreHandlerService matchScoreHandlerService;
    private final FinishedMatchesHandlerService finishedMatchesHandlerService;

    public void handle(HttpServletRequest request) {
        Long playerId = Long.valueOf(request.getParameter("round_winner"));
        MatchScoreDTO matchScoreDTO = new MatchScoreDTO(gameScore, setScore, tiebreakScore);
        RoundWinnerDTO roundWinnerDTO = new RoundWinnerDTO(playerId, matchScoreDTO);
        boolean isFinished = matchScoreHandlerService.updateScore(roundWinnerDTO);
        if (isFinished) {
            finishedMatchesHandlerService.saveMatch();
        }
    }
}
