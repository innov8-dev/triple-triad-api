package dev.innov8.triple_triad.common.web.dtos.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

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
