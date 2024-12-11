package repository;

import exceptions.HibernateException;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class RepositoryBase<K extends Serializable, E> implements Repository<K, E> {

    private final Class<E> clazz;

    @Override
    public void save(E entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new HibernateException("An error occurred while trying to save this entity: " + entity.toString());
        }
    }

    @Override
    public void update(E entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new HibernateException("An error occurred while trying to update this entity: " + entity.toString());
        }
    }

    @Override
    public void delete(K id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(session.find(clazz, id));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new HibernateException("An error occurred while trying to delete this entity: " + id.toString());
        }
    }

    @Override
    public Optional<E> findById(K id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            var object = session.find(clazz, id);
            transaction.commit();
            return Optional.ofNullable(object);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new HibernateException("An error occurred while trying to find this entity by id: " + id.toString());
        }
    }

    @Override
    public List<E> findAll() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            var criteria = session.getCriteriaBuilder().createQuery(clazz);
            criteria.select(criteria.from(clazz));
            var list = session.createQuery(criteria).getResultList();
            transaction.commit();
            return list;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new HibernateException("An error occurred while trying to find all entities.");
        }
    }

    public abstract List<E> findByName(String name);
}
