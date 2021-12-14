package dev.innov8.triple_triad.common.web.dtos.requests;

import dev.innov8.triple_triad.models.user.AppUser;
import dev.innov8.triple_triad.common.web.dtos.requests.ResourceRequest;

public class NewUserRequest implements ResourceRequest<AppUser> {
    @Override
    public AppUser extract() {
        return null;
    }
}
