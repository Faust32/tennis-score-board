package dao;

import model.Match;

import java.util.UUID;

public class MatchRepository extends RepositoryBase<UUID, Match> {
    public MatchRepository() {
        super(Match.class);
    }
}
