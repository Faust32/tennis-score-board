package dao;

import exceptions.HibernateException;
import model.Match;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;
import java.util.UUID;

public class MatchRepository extends RepositoryBase<UUID, Match> {
    public MatchRepository() {
        super(Match.class);
    }

    @Override
    public List<Match> findByName(String name) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            var builder = session.getCriteriaBuilder();
            var criteria = builder.createQuery(Match.class);
            var root = criteria.from(Match.class);

            var player1Join = root.join("player1");
            var player2Join = root.join("player2");

            var predicate = builder.or(
                    builder.like(builder.lower(player1Join.get("name")), '%' + name.toLowerCase() + '%'),
                    builder.like(builder.lower(player2Join.get("name")), '%' + name.toLowerCase() + '%')
            );

            criteria.where(predicate);

            var list = session.createQuery(criteria).getResultList();
            transaction.commit();
            return list;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new HibernateException("An error occurred while trying to find matches with player with this name: " + name);
        }
    }
}
