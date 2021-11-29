package dev.innov8.triple_triad.user.dtos.requests;

import dev.innov8.triple_triad.common.models.user.AppUser;
import dev.innov8.triple_triad.common.web.ResourceRequest;

public class NewUserRequest implements ResourceRequest<AppUser> {
    @Override
    public AppUser extract() {
        return null;
    }
}
