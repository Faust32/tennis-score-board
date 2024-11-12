package dao;

import jakarta.persistence.EntityManager;
import model.Player;

public class PlayerRepository extends RepositoryBase<Long, Player> {

    public PlayerRepository(EntityManager entityManager) {
        super(Player.class, entityManager);
    }
}
