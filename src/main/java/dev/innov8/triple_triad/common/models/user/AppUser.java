package dev.innov8.triple_triad.common.models.user;

import javax.persistence.*;
import java.util.Objects;

// TODO: Review fields

@Entity
@Table(name = "app_users")
public class AppUser {

    @Id
    @Column(name = "user_id", nullable = false, unique = true)
    private String id;

    @Column(name = "first_name", nullable = false, columnDefinition = "VARCHAR CHECK first_name <> ''")
    private String firstName;

    @Column(name = "last_name", nullable = false, columnDefinition = "VARCHAR CHECK last_name <> ''")
    private String lastName;

    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR CHECK email <> ''")
    private String email;

    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR CHECK username <> ''")
    private String username;

    @Column(nullable = false, columnDefinition = "VARCHAR CHECK password <> ''")
    private String password;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean accountActivated;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAccountActivated() {
        return accountActivated;
    }

    public void setAccountActivated(boolean accountActivated) {
        this.accountActivated = accountActivated;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return accountActivated == appUser.accountActivated && Objects.equals(id, appUser.id) && Objects.equals(firstName, appUser.firstName) && Objects.equals(lastName, appUser.lastName) && Objects.equals(email, appUser.email) && Objects.equals(username, appUser.username) && Objects.equals(password, appUser.password) && accountType == appUser.accountType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, username, password, accountActivated, accountType);
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", accountActivated=" + accountActivated +
                ", accountType=" + accountType +
                '}';
    }
}
