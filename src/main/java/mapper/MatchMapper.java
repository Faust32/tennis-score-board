package mapper;

import dao.PlayerRepository;
import dto.PlayersDTO;
import exceptions.NotFoundModelException;
import lombok.RequiredArgsConstructor;
import model.Match;
import model.Player;

@RequiredArgsConstructor
public class MatchMapper implements Mapper<PlayersDTO, Match> {
    private final PlayerRepository playerRepository;
    @Override
    public Match mapFrom(PlayersDTO playersDTO) {
        Player player1 = playerRepository.findByName(playersDTO.firstPlayerName()).isEmpty(() -> new NotFoundModelException(playersDTO.firstPlayerName()));
        Player player2 = playerRepository.findByName(playersDTO.secondPlayerName()).orElseThrow(() -> new  NotFoundModelException(playersDTO.secondPlayerName()));

        return Match.builder()
                .player1(player1)
                .player2(player2)
                .build();
    }
}
