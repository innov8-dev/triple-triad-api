package dev.innov8.triple_triad.common.datasource;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static dev.innov8.triple_triad.common.util.Reflector.findFirstFieldMeetingCondition;
import static dev.innov8.triple_triad.common.util.Reflector.getObjectFieldValue;

public class ResourceRepository<T> implements JpaRepository<T, String> {

    private final Class<T> resourceType;
    private final EntityManager entityManager;

    public ResourceRepository(Class<T> resourceType, EntityManager entityManager) {
        this.resourceType = resourceType;
        this.entityManager = entityManager;
    }

    public void clear() {
        System.out.println("CLEARING ENTITY MANAGER");
        entityManager.clear();
    }

    @Override
    public List<T> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(resourceType);
        Root<T> rootEntry = cq.from(resourceType);
        TypedQuery<T> allQuery = entityManager.createQuery(cq.select(rootEntry));
        return allQuery.getResultList();
    }

    // TODO implement me
    @Override
    public List<T> findAll(Sort sort) {
        throw new RuntimeException("Method not yet implemented");
    }

    // TODO implement me
    @Override
    public Page<T> findAll(Pageable pageable) {
        throw new RuntimeException("Method not yet implemented");
    }

    // TODO implement me
    @Override
    public <S extends T> List<S> findAll(Example<S> example) {
        throw new RuntimeException("Method not yet implemented");
    }

    // TODO implement me
    @Override
    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        throw new RuntimeException("Method not yet implemented");
    }

    // TODO implement me
    @Override
    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        throw new RuntimeException("Method not yet implemented");
    }

    // TODO implement me
    @Override
    public List<T> findAllById(Iterable<String> ids) {
        List<T> results = new ArrayList<>();
        ids.forEach(id -> findById(id).ifPresent(results::add));
        return results;
    }

    // TODO implement me
    @Override
    public T getOne(String s) {
        throw new RuntimeException("Method not yet implemented");
    }

    // TODO implement me
    @Override
    public T getById(String s) {
        throw new RuntimeException("Method not yet implemented");
    }

    // TODO implement me
    @Override
    public <S extends T> Optional<S> findOne(Example<S> example) {
        throw new RuntimeException("Method not yet implemented");
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

    // TODO implement me
    @Override
    public <S extends T, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        throw new RuntimeException("Method not implemented");
    }

    @Override
    public boolean existsById(String id) {
        return findById(id).isPresent();
    }

    @Override
    public <S extends T> boolean exists(Example<S> example) {
        throw new RuntimeException("Method not implemented");
    }

    // TODO implement me
    @Override
    public <S extends T> long count(Example<S> example) {
        throw new RuntimeException("Method not yet implemented");
    }

    @Override
    public long count() {
        return findAll().size();
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
    public void flush() {
        entityManager.flush();
    }

    @Override
    public <S extends T> S saveAndFlush(S entity) {
        save(entity);
        flush();
        return entity;
    }

    @Override
    public <S extends T> List<S> saveAllAndFlush(Iterable<S> entities) {
        List<S> results = new ArrayList<>();
        entities.forEach(entity -> results.add(save(entity)));
        return results;
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

        Predicate condition = cb.equal(fromTable.get("id"), getObjectFieldValue(entity, entityIdField));
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
    public void deleteAllInBatch(Iterable<T> entities) {
        throw new RuntimeException("Method not implemented");
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<String> strings) {
        throw new RuntimeException("Method not implemented");
    }

    @Override
    public void deleteAllInBatch() {
        throw new RuntimeException("Method not implemented");
    }

}
