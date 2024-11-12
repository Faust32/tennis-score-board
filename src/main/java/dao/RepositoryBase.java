package dao;

import jakarta.persistence.EntityManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class RepositoryBase<K extends Serializable, E> implements Repository<K, E> {

    private final Class<E> clazz;
    @Getter
    private final EntityManager entityManager;

    @Override
    public E save(E entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public void update(E entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(K id) {
        entityManager.remove(entityManager.find(clazz, id));
    }

    @Override
    public Optional<E> findById(K id) {
        return Optional.ofNullable(entityManager.find(clazz, id));
    }

    @Override
    public List<E> findAll() {
        var criteria = entityManager.getCriteriaBuilder().createQuery(clazz);
        criteria.select(criteria.from(clazz));
        return entityManager.createQuery(criteria).getResultList();
    }

    public Optional<E> findByName(String name) {
        var criteria = entityManager.getCriteriaBuilder().createQuery(clazz);
        var root = criteria.from(clazz);
        criteria.select(root).where(entityManager.getCriteriaBuilder().equal(root.get("name"), name));
        return Optional.ofNullable(entityManager.createQuery(criteria).getSingleResult());
    }
}
