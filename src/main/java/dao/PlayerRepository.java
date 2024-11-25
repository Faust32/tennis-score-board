package dao;

import model.Player;

public class PlayerRepository extends RepositoryBase<Long, Player> {

    public PlayerRepository() {
        super(Player.class);
    }
}
