package dao;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class RepositoryBase<K extends Serializable, E> implements Repository<K, E> {
    private final Class<E> clazz;

    @Override
    public E save(E entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            var transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
            return entity;
        }
    }

    @Override
    public void update(E entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            var transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
        }
    }

    @Override
    public void delete(K id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            var transaction = session.beginTransaction();
            session.remove(session.find(clazz, id));
            transaction.commit();
        }
    }

    @Override
    public Optional<E> findById(K id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            var transaction = session.beginTransaction();
            var object = session.find(clazz, id);
            transaction.commit();
            return Optional.ofNullable(object);
        }
    }

    @Override
    public List<E> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            var transaction = session.beginTransaction();
            var criteria = session.getCriteriaBuilder().createQuery(clazz);
            criteria.select(criteria.from(clazz));
            var list = session.createQuery(criteria).getResultList();
            transaction.commit();
            return list;
        }
    }

    public List<E> findByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            var transaction = session.beginTransaction();
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
        }
    }
}
