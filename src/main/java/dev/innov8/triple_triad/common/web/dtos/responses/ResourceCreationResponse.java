package dev.innov8.triple_triad.common.web.dtos.responses;

public class ResourceCreationResponse {

    private String id;

    public ResourceCreationResponse() {
        super();
    }

    public ResourceCreationResponse(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "NewResourceCreationResponse{" +
                "id='" + id + '\'' +
                '}';
    }

}
