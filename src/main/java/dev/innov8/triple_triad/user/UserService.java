package dev.innov8.triple_triad.user;

import dev.innov8.triple_triad.common.models.user.AppUser;
import dev.innov8.triple_triad.common.services.ResourceService;
import dev.innov8.triple_triad.common.data.EntitySearcher;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ResourceService<AppUser> {

    private final UserRepository userRepo;

    public UserService(UserRepository userRepo, EntitySearcher entitySearcher) {
        super(userRepo, entitySearcher);
        this.userRepo = userRepo;
    }

}
