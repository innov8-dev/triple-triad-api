package dev.innov8.triple_triad.common.services;

import dev.innov8.triple_triad.common.datasource.ResourceRepository;
import dev.innov8.triple_triad.common.util.Reflector;
import dev.innov8.triple_triad.common.web.dtos.responses.ResourceCreationResponse;
import dev.innov8.triple_triad.common.web.dtos.requests.ResourceRequest;
import dev.innov8.triple_triad.common.web.dtos.responses.ResourceResponse;
import dev.innov8.triple_triad.common.web.dtos.responses.ResponseFactory;
import dev.innov8.triple_triad.common.exceptions.ResourceNotFoundException;
import dev.innov8.triple_triad.models.Resource;
import dev.innov8.triple_triad.common.datasource.EntitySearcher;
import org.hibernate.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityTransaction;
import javax.validation.Valid;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.stream.Collectors;

import static dev.innov8.triple_triad.common.util.Reflector.*;

@SuppressWarnings({"unchecked"})
public class ResourceService<T extends Resource> {

    protected final ResourceRepository<T> repo;
    private final EntitySearcher entitySearcher;
    private final PlatformTransactionManager txManager;
    private final Class<? extends Resource> resourceType;
    private final String resourceSimpleName;

    public ResourceService(ResourceRepository<T> repo, EntitySearcher entitySearcher, PlatformTransactionManager txManager) {
        this.repo = repo;
        this.entitySearcher = entitySearcher;
        this.txManager = txManager;
        this.resourceType = (Class<? extends Resource>) ((ParameterizedType) repo.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.resourceSimpleName = resourceType.getSimpleName();
    }

    public List<? extends ResourceResponse> search(Map<String, String> requestParamMap) {
        if (requestParamMap.isEmpty()) return findAll();
        Set<? extends Resource> matchingCards = entitySearcher.searchForEntity(requestParamMap, resourceType);
        if (matchingCards.isEmpty()) throw new ResourceNotFoundException();
        return matchingCards.stream()
                            .map(resource -> ResponseFactory.buildResponse(resourceSimpleName, resource))
                            .collect(Collectors.toList());
    }

    public List<? extends ResourceResponse> findAll() {
        return ((Collection<?>) repo.findAll())
                   .stream()
                   .map(resource -> ResponseFactory.buildResponse(resourceSimpleName, (Resource) resource))
                   .collect(Collectors.toList());
    }

    public ResourceResponse findById(String id) {
        return repo.findById(id)
                   .map(resource -> ResponseFactory.buildResponse(resourceSimpleName, resource))
                   .orElseThrow(ResourceNotFoundException::new);

    }

    public ResourceCreationResponse save(@Valid ResourceRequest<T> saveRequest) {
        TransactionStatus tx = txManager.getTransaction(TransactionDefinition.withDefaults());
        T newObj = saveRequest.extract();
        newObj.setId(UUID.randomUUID().toString());
        repo.saveAndFlush(newObj);
        txManager.commit(tx);
        repo.clear();
        System.out.println("IS TX COMPLETE: " + tx.isCompleted());
        return new ResourceCreationResponse(newObj.getId());
    }

    public void update(@Valid ResourceRequest<T> updateRequest) {

        TransactionStatus tx = txManager.getTransaction(TransactionDefinition.withDefaults());
        T updatedObj = updateRequest.extract();
        T originalRecord = repo.findById(updatedObj.getId()).orElseThrow(ResourceNotFoundException::new);

        Arrays.stream(resourceType.getDeclaredFields())
              .flatMap(field -> {
                  Map<String, Object> fieldMap = new HashMap<>();
                  fieldMap.put(field.getName(), getObjectFieldValue(updatedObj, field));
                  return fieldMap.entrySet().stream();
              })
              .filter(entry -> entry.getValue() != null && !entry.getValue().equals(0))
              .forEach(entry -> {
                  try {
                      Field field = resourceType.getDeclaredField(entry.getKey());
                      invokeMethod(getMutatorForField(resourceType, field), originalRecord, getObjectFieldValue(updatedObj, field));
                  } catch (Exception e) {
                      e.printStackTrace();
                      txManager.rollback(tx);
                      repo.clear();
                  }
              });

        txManager.commit(tx);
        repo.clear();

    }

    @Transactional
    public void deleteById(String id) {
        repo.deleteById(id);
    }

}
