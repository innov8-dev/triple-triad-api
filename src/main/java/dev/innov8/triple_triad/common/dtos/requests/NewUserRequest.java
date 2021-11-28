package dev.innov8.triple_triad.common.dtos.requests;

import dev.innov8.triple_triad.common.models.user.AppUser;
import dev.innov8.triple_triad.common.web.AppRequest;

public class NewUserRequest extends AppRequest<AppUser> {
    @Override
    public AppUser extract() {
        return null;
    }
}
