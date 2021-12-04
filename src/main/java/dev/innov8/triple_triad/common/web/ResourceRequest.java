package dev.innov8.triple_triad.common.web;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import dev.innov8.triple_triad.creature.dtos.requests.NewCreatureRequest;
import dev.innov8.triple_triad.creature.dtos.requests.UpdateCreatureRequest;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
    @JsonSubTypes.Type(value = NewCreatureRequest.class, name = "new-creature"),
    @JsonSubTypes.Type(value = UpdateCreatureRequest.class, name = "update-creature")
})
public interface ResourceRequest<T> extends Serializable {
    T extract();
}
