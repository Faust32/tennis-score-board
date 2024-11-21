package service;

import dao.PlayerRepository;
import dto.RoundWinnerDTO;
import model.Player;
import model.score.ScoreController;
import model.score.SetScore;
import model.score.TiebreakScore;
import lombok.RequiredArgsConstructor;
import model.score.GameScore;
import model.Match;

import java.util.Optional;

@RequiredArgsConstructor
public class MatchScoreHandlerService {
    private final CurrentMatchService currentMatchService;
    private final ScoreController scoreController;
    private final PlayerRepository playerRepository;

    public boolean updateScore(RoundWinnerDTO roundWinnerDTO) {
        Long winnerId = roundWinnerDTO.playerId();
        GameScore gameScore = roundWinnerDTO.matchScoreDTO().gameScore();
        SetScore setScore = roundWinnerDTO.matchScoreDTO().setScore();
        TiebreakScore tiebreakScore = roundWinnerDTO.matchScoreDTO().tiebreakScore();
        Match match = currentMatchService.getCurrentMatch();
        Long player1Id = match.getPlayer1().getId();
        long finalWinnerId = 0L;

        if (scoreController.isTie(setScore)) {
            Optional<Long> tempId = scoreController.handeTie(tiebreakScore, match, winnerId);
            if (tempId.isPresent()) {
                finalWinnerId = tempId.get();
            }
        }
        if (scoreController.isDeuce(gameScore)) {
            scoreController.handleDeuce(gameScore, setScore, player1Id, winnerId);
        } else if (scoreController.isFortyScore(gameScore)) {
           Optional<Long> tempId = scoreController.handleFortyScore(gameScore, setScore, match, winnerId);
           if (tempId.isPresent()) {
               finalWinnerId = tempId.get();
           }
        } else {
            scoreController.incrementPlayerScore(gameScore, player1Id.equals(winnerId));
        }

        if (finalWinnerId != 0) {
            Optional<Player> player = playerRepository.findById(finalWinnerId);
            player.ifPresent(match::setWinner);
            return true;
        }
        return false;
    }
}
