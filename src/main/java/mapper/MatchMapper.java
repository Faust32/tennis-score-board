package mapper;

import dao.PlayerRepository;
import dto.PlayersDTO;
import exceptions.NotFoundModelException;
import lombok.RequiredArgsConstructor;
import model.Match;
import model.Player;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class MatchMapper implements Mapper<PlayersDTO, Match> {
    private final PlayerRepository playerRepository;
    @Override
    public Match mapFrom(PlayersDTO playersDTO) {
        Player player1 = findPlayerByName(playersDTO.firstPlayerName())
                .orElseThrow(() -> new NotFoundModelException(playersDTO.firstPlayerName()));

        Player player2 = findPlayerByName(playersDTO.secondPlayerName())
                .orElseThrow(() -> new NotFoundModelException(playersDTO.secondPlayerName()));

        return Match.builder()
                .player1(player1)
                .player2(player2)
                .build();
    }

    private Optional<Player> findPlayerByName(String playerName) {
        List<Player> players = playerRepository.findByName(playerName);
        return players.isEmpty() ? Optional.empty() : Optional.of(players.getFirst());
    }
}
