package dev.innov8.triple_triad.common.datasource;

import dev.innov8.triple_triad.common.exceptions.InvalidRequestException;
import dev.innov8.triple_triad.common.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class EntitySearcher {

    private final EntityManager entityManager;

    @Autowired
    public EntitySearcher(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public <T> Set<T> searchForEntity(Map<String, String> searchCriteria, Class<T> entityClass) {

        if (entityClass.getAnnotation(Entity.class) == null) {
            throw new InvalidRequestException("Provided class is not an Entity!");
        }

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(entityClass);
        Root<T> root = query.from(entityClass);
        Predicate predicate = cb.conjunction();

        for(Map.Entry<String, String> searchEntry : searchCriteria.entrySet()) {

            String searchKey = searchEntry.getKey();
            String searchVal = searchEntry.getValue();

            try {
                if (searchKey.contains(".")) {
                    String[] searchKeyFrags = searchKey.split("\\.");
                    String nestedTypeName = searchKeyFrags[0];
                    String nestedTypeFieldName = searchKeyFrags[1];
                    Join joinType = root.join(nestedTypeName);
                    Field nestedTypeField = entityClass.getDeclaredField(nestedTypeName).getType().getDeclaredField(nestedTypeFieldName);
                    predicate = getPredicate(cb, predicate, searchVal, nestedTypeField, joinType.get(nestedTypeFieldName));
                } else {
                    Field searchField = entityClass.getDeclaredField(searchKey);
                    predicate = getPredicate(cb, predicate, searchVal, searchField, root.get(searchKey));
                }
            } catch (NoSuchFieldException e) {
                throw new InvalidRequestException(String.format("No attribute with name: %s found on entity: %s", searchKey, entityClass.getName()));
            }

        }

        query.where(predicate);

        return new HashSet<>(entityManager.createQuery(query).getResultList());
    }

    private Predicate getPredicate(CriteriaBuilder cb, Predicate predicate, String searchVal, Field nestedTypeField, Path path) {
        if (nestedTypeField.getType().isEnum()) {
            try {
                Enum enumVal = Enum.valueOf((Class<Enum>) nestedTypeField.getType(), searchVal.toUpperCase());
                predicate = cb.and(predicate, cb.equal(path, enumVal));
            } catch (IllegalArgumentException e) {
                throw new ResourceNotFoundException();
            }
        } else {
            predicate = cb.and(predicate, cb.equal(path, searchVal));
        }
        return predicate;
    }
}
