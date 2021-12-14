package dev.innov8.triple_triad.common.services;

import dev.innov8.triple_triad.common.web.ResourceCreationResponse;
import dev.innov8.triple_triad.common.web.ResourceRequest;
import dev.innov8.triple_triad.common.web.ResourceResponse;
import dev.innov8.triple_triad.common.web.ResponseFactory;
import dev.innov8.triple_triad.common.exceptions.ResourceNotFoundException;
import dev.innov8.triple_triad.common.models.Resource;
import dev.innov8.triple_triad.common.datasource.EntitySearcher;
import org.springframework.data.repository.CrudRepository;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings({"unchecked"})
public class ResourceService<T extends Resource> {

    protected final CrudRepository<T, String> repo;
    private final EntitySearcher entitySearcher;
    private final Class<? extends Resource> resourceType;
    private final String resourceSimpleName;

    public ResourceService(CrudRepository<T, String> repo, EntitySearcher entitySearcher) {
        this.repo = repo;
        this.entitySearcher = entitySearcher;
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
        T newObj = saveRequest.extract();
        newObj.setId(UUID.randomUUID().toString());
        repo.save(newObj);
        return new ResourceCreationResponse(newObj.getId());
    }

    public void update(@Valid ResourceRequest<T> updateRequest) {

        T updatedObj = updateRequest.extract();
        T originalRecord = repo.findById(updatedObj.getId()).orElseThrow(ResourceNotFoundException::new);

        Arrays.stream(resourceType.getDeclaredFields())
              .flatMap(field -> {
                  try {
                      Map<String, Object> fieldMap = new HashMap<>();
                      Method getter = getFieldAccessorMethod(field);
                      fieldMap.put(field.getName(), getter.invoke(updatedObj));
                      return fieldMap.entrySet().stream();
                  } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                      throw new RuntimeException("An unexpected exception occurred during resource update", e);
                  }
              })
              .filter(entry -> entry.getValue() == null || entry.getValue().equals(0))
              .forEach(entry -> {
                  try {
                      Field field = resourceType.getDeclaredField(entry.getKey());
                      Method getter = getFieldAccessorMethod(field);
                      Method setter = getFieldMutatorMethod(field);
                      Object originalFieldValue = getter.invoke(originalRecord);
                      setter.invoke(updatedObj, originalFieldValue);
                  } catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                      e.printStackTrace();
                  }
              });

        repo.save(updatedObj);

    }

    public void deleteById(String id) {
        repo.deleteById(id);
    }

    private Method getFieldAccessorMethod(Field field) throws NoSuchMethodException {
        return resourceType.getDeclaredMethod("get" + upperCaseFirstLetter(field.getName()));
    }

    private Method getFieldMutatorMethod(Field field) throws NoSuchMethodException {
        return resourceType.getDeclaredMethod("set" + upperCaseFirstLetter(field.getName()), field.getType());
    }

    private String upperCaseFirstLetter(String str) {
        if (str == null || str.isEmpty()) return str;
        if (str.length() == 1) return str.toUpperCase();
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

}
