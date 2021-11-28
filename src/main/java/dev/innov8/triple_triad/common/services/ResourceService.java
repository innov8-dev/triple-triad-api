package dev.innov8.triple_triad.common.services;

import dev.innov8.triple_triad.common.dtos.responses.ResourceResponse;
import dev.innov8.triple_triad.common.dtos.responses.ResponseFactory;
import dev.innov8.triple_triad.common.exceptions.ResourceNotFoundException;
import dev.innov8.triple_triad.common.models.Resource;
import dev.innov8.triple_triad.common.util.EntitySearcher;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.Valid;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@SuppressWarnings({"unchecked"})
public abstract class ResourceService<T extends Resource> {

    protected final JpaRepository<T, String> repo;
    private final EntitySearcher entitySearcher;
    private final Class<? extends Resource> resourceType;
    private final String resourceSimpleName;

    public ResourceService(JpaRepository<T, String> repo, EntitySearcher entitySearcher) {
        this.repo = repo;
        this.entitySearcher = entitySearcher;
        this.resourceType = (Class<? extends Resource>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
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
        return repo.findAll()
                   .stream()
                   .map(resource -> ResponseFactory.buildResponse(resourceSimpleName, resource))
                   .collect(Collectors.toList());
    }

    public ResourceResponse findById(String id) {
        return repo.findById(id)
                   .map(resource -> ResponseFactory.buildResponse(resourceSimpleName, resource))
                   .orElseThrow(ResourceNotFoundException::new);

    }

    public void save(@Valid T newObj) {
        newObj.setId(UUID.randomUUID().toString());
        repo.save(newObj);
    }

    public void update(@Valid T updatedObj) {
        repo.save(updatedObj);
    }

    public void deleteById(String id) {
        repo.deleteById(id);
    }

}
