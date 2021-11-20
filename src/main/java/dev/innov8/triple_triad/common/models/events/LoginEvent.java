package dev.innov8.triple_triad.common.models.events;

import dev.innov8.triple_triad.common.models.user.AppUser;

import javax.persistence.*;

// TODO: Review fields

@Entity
public class LoginEvent extends Event {

    @ManyToOne
    @JoinColumn(name = "authenticated_user", nullable = false)
    private AppUser authenticatedUser;

    public AppUser getAuthenticatedUser() {
        return authenticatedUser;
    }

    public void setAuthenticatedUser(AppUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
    }

}
