package dev.innov8.triple_triad.common.models.events;

import dev.innov8.triple_triad.common.models.user.AppUser;

import javax.persistence.*;

// TODO: Review fields

@Entity
@Table(name = "registration_events")
public class RegistrationEvent extends Event {

    @OneToOne
    @JoinColumn(name = "registered_user_id")
    private AppUser registeredUser;

    public AppUser getRegisteredUser() {
        return registeredUser;
    }

    public void setRegisteredUser(AppUser registeredUser) {
        this.registeredUser = registeredUser;
    }

}
