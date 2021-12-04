package dev.innov8.triple_triad.common.web;

public class NewResourceCreationResponse {

    private String id;

    public NewResourceCreationResponse() {
        super();
    }

    public NewResourceCreationResponse(String id) {
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
