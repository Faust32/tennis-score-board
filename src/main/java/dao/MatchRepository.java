package dao;

import jakarta.persistence.EntityManager;
import model.Match;

public class MatchRepository extends RepositoryBase<Long, Match> {
    public MatchRepository(EntityManager entityManager) {
        super(Match.class, entityManager);
    }
}
