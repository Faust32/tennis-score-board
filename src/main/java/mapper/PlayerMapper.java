package mapper;

import dto.PlayersDTO;
import model.Player;

public class PlayerMapper implements Mapper<PlayersDTO, Player[]> {
    @Override
    public Player[] mapFrom(PlayersDTO playersDTO) {
        Player player1 = Player.builder()
                .name(playersDTO.firstPlayerName())
                .build();
        Player player2 = Player.builder()
                .name(playersDTO.secondPlayerName())
                .build();
        return new Player[]{player1, player2};
    }
}
