package dev.innov8.triple_triad.common.web;

import dev.innov8.triple_triad.common.exceptions.InvalidRequestException;
import dev.innov8.triple_triad.common.exceptions.ResourceNotFoundException;
import dev.innov8.triple_triad.common.exceptions.ResourcePersistenceException;
import dev.innov8.triple_triad.models.Resource;
import dev.innov8.triple_triad.common.services.ResourceService;
import dev.innov8.triple_triad.common.web.dtos.requests.ResourceRequest;
import dev.innov8.triple_triad.common.web.dtos.responses.ErrorResponse;
import dev.innov8.triple_triad.common.web.dtos.responses.ResourceCreationResponse;
import dev.innov8.triple_triad.common.web.dtos.responses.ResourceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"unchecked"})
public class ResourceController<T extends Resource> {

    protected final ResourceService<T> service;

    public ResourceController(ResourceService<T> service) {
        this.service = service;
    }

    @GetMapping
    public List<ResourceResponse> findAll() {
        return (List<ResourceResponse>) service.findAll();
    }

    @GetMapping("/search")
    public List<ResourceResponse> findBy(@RequestParam Map<String, String> params) {
        return (List<ResourceResponse>) service.search(params);
    }

    @GetMapping("/{id}")
    public ResourceResponse findById(@PathVariable @NotEmpty String id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResourceCreationResponse save(@RequestBody @Valid ResourceRequest<T> newObj) {
        return service.save(newObj);
    }

    @PatchMapping
    public void update(@RequestBody @Valid ResourceRequest<T> updatedObj) {
        service.update(updatedObj);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable @NotEmpty String id) {
        service.deleteById(id);
    }

    @ExceptionHandler({InvalidRequestException.class, MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidRequestException(Exception e) {
        e.printStackTrace();
        return new ErrorResponse(400, e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleRetrievalException(ResourceNotFoundException e) {
        return new ErrorResponse(404, e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handlePersistenceException(ResourcePersistenceException e) {
        return new ErrorResponse(409, e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleInternalServerException(Exception e) {
        e.printStackTrace();
        return new ErrorResponse(500, e.getMessage());
    }


}
