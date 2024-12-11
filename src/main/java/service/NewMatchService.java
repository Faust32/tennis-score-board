package service;

import repository.MatchRepository;
import repository.PlayerRepository;
import dto.PlayersDTO;
import jakarta.transaction.Transactional;
import model.Match;
import model.Player;

import java.util.List;

@Transactional
public class NewMatchService {

    private final PlayerRepository playerRepository = new PlayerRepository();

    private final MatchRepository matchRepository = new MatchRepository();

    public Player[] savePlayers(PlayersDTO playersDTO) {
        Player player1;
        Player player2;
        List<Player> player1List = playerRepository.findByName(playersDTO.firstPlayerName());
        List<Player> player2List = playerRepository.findByName(playersDTO.secondPlayerName());
        if (player1List.isEmpty()) {
            player1 = Player.builder()
                    .name(playersDTO.firstPlayerName())
                    .build();
            playerRepository.save(player1);
        } else {
            player1 = player1List.getFirst();
        }
        if (player2List.isEmpty()) {
            player2 = Player.builder()
                            .name(playersDTO.secondPlayerName())
                            .build();
            playerRepository.save(player2);
        } else {
            player2 = player2List.getFirst();
        }
        return new Player[]{player1, player2};
    }

    public Match createMatch(Player[] players) {
        Match match = Match.builder()
                        .player1(players[0])
                        .player2(players[1])
                        .build();
        matchRepository.save(match);
        return match;
    }
}
