package service;

import dto.RoundWinnerDTO;
import score.ScoreController;
import score.SetScore;
import score.TiebreakScore;
import lombok.RequiredArgsConstructor;
import score.GameScore;
import model.Match;

@RequiredArgsConstructor
public class MatchScoreHandlerService {
    private final CurrentMatchService currentMatchService;
    private final ScoreController scoreController;

    public void updateScore(RoundWinnerDTO roundWinnerDTO) {
        Long winnerId = roundWinnerDTO.playerId();
        GameScore gameScore = roundWinnerDTO.matchScoreDTO().gameScore();
        SetScore setScore = roundWinnerDTO.matchScoreDTO().setScore();
        TiebreakScore tiebreakScore = roundWinnerDTO.matchScoreDTO().tiebreakScore();
        Match match = currentMatchService.getCurrentMatch();
        Long player1Id = match.getPlayer1().getId();
        if (scoreController.isTie(setScore)) {
            scoreController.handeTie(tiebreakScore, match, winnerId);
            //TODO: add one more check for set games (6-6, 7-5 etc.)
        }
        if (scoreController.isDeuce(gameScore)) {
            scoreController.handleDeuce(gameScore, setScore, player1Id, winnerId);
        } else if (scoreController.isFortyScore(gameScore)) {
            scoreController.handleFortyScore(gameScore, setScore, player1Id, winnerId);
        } else {
            scoreController.incrementPlayerScore(gameScore, player1Id.equals(winnerId));
        }
    }

}
