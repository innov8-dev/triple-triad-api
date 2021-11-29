package dev.innov8.triple_triad.common.web;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import dev.innov8.triple_triad.creature.dtos.requests.UpsertCreatureRequest;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
    @JsonSubTypes.Type(UpsertCreatureRequest.class)
})
public interface ResourceRequest<T> {
    T extract();
}
