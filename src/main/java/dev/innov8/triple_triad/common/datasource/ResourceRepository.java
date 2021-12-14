package dev.innov8.triple_triad.common.datasource;

import dev.innov8.triple_triad.common.util.Reflector;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static dev.innov8.triple_triad.common.util.Reflector.findFirstFieldMeetingCondition;

// TODO implement methods

public class ResourceRepository<T> implements CrudRepository<T, String> {

    private final Class<T> resourceType;
    private final EntityManager entityManager;

    public ResourceRepository(Class<T> resourceType, EntityManager entityManager) {
        this.resourceType = resourceType;
        this.entityManager = entityManager;
    }

    @Override
    public List<T> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(resourceType);
        Root<T> rootEntry = cq.from(resourceType);
        TypedQuery<T> allQuery = entityManager.createQuery(cq.select(rootEntry));
        return allQuery.getResultList();
    }


    @Override
    public List<T> findAllById(Iterable<String> ids) {
        List<T> results = new ArrayList<>();
        ids.forEach(id -> findById(id).ifPresent(results::add));
        return results;
    }

    @Override
    public long count() {
        return findAll().size();
    }

    @Override
    public void deleteById(String id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaDelete<T> cd = cb.createCriteriaDelete(resourceType);
        Root<T> fromTable = cd.from(resourceType);
        Predicate condition = cb.equal(fromTable.get("id"), id);
        Query deleteQuery = entityManager.createQuery(cd.where(condition));
        deleteQuery.executeUpdate();
    }

    @Override
    public void delete(T entity) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaDelete<T> cd = cb.createCriteriaDelete(resourceType);
        Root<T> fromTable = cd.from(resourceType);

        Field entityIdField = findFirstFieldMeetingCondition(resourceType, field -> field.isAnnotationPresent(Id.class))
                              .orElseThrow(() -> new RuntimeException("No field annotated with @Id in Entity class"));

        Predicate condition = cb.equal(fromTable.get("id"), Reflector.getObjectFieldValue(entity, entityIdField));
        Query deleteQuery = entityManager.createQuery(cd.where(condition));
        deleteQuery.executeUpdate();
    }

    @Override
    public void deleteAllById(Iterable<? extends String> ids) {
        ids.forEach(this::deleteById);
    }

    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        entities.forEach(this::delete);
    }

    @Override
    public void deleteAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaDelete<T> cd = cb.createCriteriaDelete(resourceType);
        Root<T> fromTable = cd.from(resourceType);
        Query deleteQuery = entityManager.createQuery(cd);
        deleteQuery.executeUpdate();
    }

    @Override
    public <S extends T> S save(S entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        List<S> results = new ArrayList<>();
        entities.forEach(entity -> results.add(save(entity)));
        return results;
    }

    @Override
    public Optional<T> findById(String id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(resourceType);
        Root<T> fromTable = cq.from(resourceType);
        Predicate condition = cb.equal(fromTable.get("id"), id);
        TypedQuery<T> idQuery = entityManager.createQuery(cq.select(fromTable).where(condition));
        return idQuery.getResultList().stream().findFirst();
    }

    @Override
    public boolean existsById(String id) {
        return findById(id).isPresent();
    }

}
