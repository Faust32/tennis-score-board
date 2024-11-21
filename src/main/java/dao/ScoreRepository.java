package dao;

import model.score.GameScore;
import jakarta.persistence.EntityManager;

public class ScoreRepository extends RepositoryBase<Long, GameScore> {
    public ScoreRepository(EntityManager entityManager) {
        super(GameScore.class, entityManager);
    }
}
