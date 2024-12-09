package service;

import dao.MatchRepository;
import dto.MatchScoreDTO;
import dto.PlayersScoreDTO;
import exceptions.NotFoundException;
import lombok.Getter;
import model.Match;
import model.score.Game;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CurrentMatchService {
    @Getter
    private Game game;
    private static CurrentMatchService instance;
    private static UUID currentMatchId;
    private final Map<UUID, MatchScoreDTO> currentMatches = new ConcurrentHashMap<>();
    private final MatchRepository matchRepository = new MatchRepository();

    private CurrentMatchService() {}

    public synchronized static CurrentMatchService getInstance() {
        if (instance == null) {
            instance = new CurrentMatchService();
        }
        return instance;
    }

    public void addMatch(Match match) {
        PlayersScoreDTO playersScoreDTO = initPlayersScore(match);
        MatchScoreDTO matchScoreDTO = new MatchScoreDTO(match, playersScoreDTO);
        currentMatchId = match.getId();
        currentMatches.put(currentMatchId, matchScoreDTO);
        game = new Game();
    }

    public void updateMatch(Match match) {
        matchRepository.update(match);
    }

    public synchronized MatchScoreDTO getCurrentMatch() {
        try {
            return currentMatches.get(currentMatchId);
        } catch (Exception e) {
            throw new NotFoundException("There is no current match available.");
        }
    }

    public PlayersScoreDTO initPlayersScore(Match match) {
        PlayersScoreDTO playersScoreDTO = new PlayersScoreDTO();
        playersScoreDTO.setFirstPlayerName(match.getPlayer1().getName());
        playersScoreDTO.setSecondPlayerName(match.getPlayer2().getName());
        return playersScoreDTO;
    }
}
