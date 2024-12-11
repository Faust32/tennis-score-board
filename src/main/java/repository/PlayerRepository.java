package repository;

import exceptions.HibernateException;
import model.Player;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;

public class PlayerRepository extends RepositoryBase<Long, Player> {

    private static final Class<Player> clazz = Player.class;

    public PlayerRepository() {
        super(clazz);
    }

    @Override
    public List<Player> findByName(String name) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            var builder = session.getCriteriaBuilder();
            var criteria = session.getCriteriaBuilder().createQuery(clazz);
            var root = criteria.from(clazz);
            criteria.select(root).where(builder.equal(
                    builder.lower(root.get("name")),
                    name.toLowerCase()
            ));
            var list = session.createQuery(criteria).getResultList();
            transaction.commit();
            return list;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new HibernateException("An error occurred while trying to find players with this name: " + name);
        }
    }
}
