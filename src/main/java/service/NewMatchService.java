package service;

import dao.MatchRepository;
import dao.PlayerRepository;
import dto.PlayersDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mapper.MatchMapper;
import mapper.PlayerMapper;
import model.Match;
import model.Player;

@RequiredArgsConstructor
@Transactional
public class NewMatchService {
    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;
    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;

    public void savePlayers(PlayersDTO playersDTO) {
        Player[] players = playerMapper.mapFrom(playersDTO);
        playerRepository.save(players[0]);
        playerRepository.save(players[1]);
    }

    public Match createMatch(PlayersDTO playersDTO) {
        Match match = matchMapper.mapFrom(playersDTO);
        matchRepository.save(match);
        return match;
    }
}
