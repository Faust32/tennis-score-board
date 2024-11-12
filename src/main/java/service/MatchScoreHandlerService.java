package service;

import dto.RoundWinnerDTO;
import dto.SetScore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import dto.GameScore;
import model.Match;

@RequiredArgsConstructor
public class MatchScoreHandlerService {
    private final CurrentMatchService currentMatchService;
    @Getter
    private final GameScore currentMatchGameScore;

    public void updateScore(RoundWinnerDTO roundWinnerDTO) {
        GameScore gameScore = roundWinnerDTO.gameScore();
        SetScore setScore = new SetScore();
        Match match = currentMatchService.getCurrentMatch();
        if (gameScore.getFirstPlayerPoints() == 40 && gameScore.getSecondPlayerPoints() == 40) {
            if (!gameScore.isFirstPlayerAdvantage() && !gameScore.isSecondPlayerAdvantage()) {
                if (match.getPlayer1() != null && match.getPlayer1().getId().equals(roundWinnerDTO.playerId())){
                    gameScore.setFirstPlayerAdvantage(true);
                }
                else {
                    gameScore.setSecondPlayerAdvantage(true);
                }
            }
            if (gameScore.isFirstPlayerAdvantage()) {
                if (match.getPlayer1() != null && match.getPlayer1().getId().equals(roundWinnerDTO.playerId())){
                    setScore.updateFirstPlayerScore(1);
                }
                else {
                    gameScore.setFirstPlayerAdvantage(false);
                }
            }
            if (gameScore.isSecondPlayerAdvantage()) {
                if (match.getPlayer1() != null && match.getPlayer1().getId().equals(roundWinnerDTO.playerId())){
                    gameScore.setSecondPlayerAdvantage(false);
                }
                else {
                    setScore.updateSecondPlayerScore(1);
                }
            }
        }
        else {
            if (match.getPlayer1() != null && match.getPlayer1().getId().equals(roundWinnerDTO.playerId())){
                gameScore.updateFirstPlayerScore(15);
            }
            else {
                gameScore.updateSecondPlayerScore(15);
            }
        }

    }
}
