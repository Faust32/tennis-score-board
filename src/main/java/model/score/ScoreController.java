package model.score;

import model.Match;

import java.util.Optional;

public class ScoreController {

    public boolean isTie(SetScore setScore) {
        return setScore.getFirstPlayerScore() == 6 && setScore.getSecondPlayerScore() == 6;
    }

    public boolean isDeuce(GameScore gameScore) {
        return gameScore.getFirstPlayerScore() == 40 && gameScore.getSecondPlayerScore() == 40;
    }

    public boolean isFortyScore(GameScore gameScore) {
        return gameScore.getFirstPlayerScore() == 40 || gameScore.getSecondPlayerScore() == 40;
    }

    public Optional<Long> handeTie(TiebreakScore tiebreakScore, Match match, Long winnerId) {
        Long player1Id = match.getPlayer1().getId();
        Long player2Id = match.getPlayer2().getId();
        if (player1Id.equals(winnerId)) {
            tiebreakScore.updateFirstPlayerScore(1);
        } else {
            tiebreakScore.updateSecondPlayerScore(1);
        }
        Integer firstPlayerScore = tiebreakScore.getFirstPlayerScore();
        Integer secondPlayerScore = tiebreakScore.getSecondPlayerScore();
        if (firstPlayerScore >= 7 && firstPlayerScore - secondPlayerScore >= 2) {
            return Optional.of(player1Id);
        }
        if (secondPlayerScore >= 7 && secondPlayerScore - firstPlayerScore >= 2) {
            return Optional.of(player2Id);
        }
        return Optional.empty();
    }

    public void handleDeuce(GameScore gameScore, SetScore setScore, Long player1Id, Long winnerId) {
        if (!gameScore.isFirstPlayerAdvantage() && !gameScore.isSecondPlayerAdvantage()) {
            setAdvantage(gameScore, player1Id.equals(winnerId));
        } else {
            resolveAdvantage(gameScore, setScore, player1Id, winnerId);
        }
    }

    public Optional<Long> handleFortyScore(GameScore gameScore, SetScore setScore, Match match, Long winnerId) {
        Long player1Id = match.getPlayer1().getId();
        Long player2Id = match.getPlayer2().getId();
        if (gameScore.getFirstPlayerScore() == 40 && player1Id.equals(winnerId)) {
            setScore.updateFirstPlayerScore(1);
            if ((gameScore.getFirstPlayerScore() >= 6) &&
                    ((gameScore.getFirstPlayerScore() - gameScore.getSecondPlayerScore()) >= 2)) {
                return Optional.of(player1Id);
            }
        } else if (gameScore.getSecondPlayerScore() == 40 && !player1Id.equals(winnerId)) {
            setScore.updateSecondPlayerScore(1);
            if ((gameScore.getSecondPlayerScore() >= 6) &&
                    ((gameScore.getSecondPlayerScore() - gameScore.getFirstPlayerScore()) >= 2)) {
                return Optional.of(player2Id);
            }
        } else {
            incrementPlayerScore(gameScore, player1Id.equals(winnerId));
        }
        return Optional.empty();
    }

    private void setAdvantage(GameScore gameScore, boolean isFirstPlayer) {
        if (isFirstPlayer) {
            gameScore.setFirstPlayerAdvantage(true);
        } else {
            gameScore.setSecondPlayerAdvantage(true);
        }
    }

    private void resolveAdvantage(GameScore gameScore, SetScore setScore, Long player1Id, Long winnerId) {
        if (gameScore.isFirstPlayerAdvantage() && player1Id.equals(winnerId)) {
            resetGameScore(gameScore);
            setScore.updateFirstPlayerScore(1);
        } else if (gameScore.isSecondPlayerAdvantage() && !player1Id.equals(winnerId)) {
            resetGameScore(gameScore);
            setScore.updateSecondPlayerScore(1);
        } else {
            removeAdvantage(gameScore, player1Id.equals(winnerId));
        }
    }

    private void resetGameScore(GameScore gameScore) {
        gameScore.setFirstPlayerScore(0);
        gameScore.setSecondPlayerScore(0);
    }

    private void removeAdvantage(GameScore gameScore, boolean isFirstPlayer) {
        if (isFirstPlayer) {
            gameScore.setFirstPlayerAdvantage(false);
        } else {
            gameScore.setSecondPlayerAdvantage(false);
        }
    }

    public void incrementPlayerScore(GameScore gameScore, boolean isFirstPlayer) {
        if (isFirstPlayer) {
            gameScore.updateFirstPlayerScore(15);
        } else {
            gameScore.updateSecondPlayerScore(15);
        }
    }

}
