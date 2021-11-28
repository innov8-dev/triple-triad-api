package dev.innov8.triple_triad.common.web;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import dev.innov8.triple_triad.common.dtos.requests.NewCreatureRequest;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(NewCreatureRequest.class)
}
)
public abstract class AppRequest<T> {
    public abstract T extract();
}
