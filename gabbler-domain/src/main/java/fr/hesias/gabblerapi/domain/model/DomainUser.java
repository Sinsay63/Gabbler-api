package fr.hesias.gabblerapi.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DomainUser {
    private int id;

    private String username;

    private String firstName;

    private String lastName;

    private String birthday;

    private String email;

    private String password;

    private String authToken;

    private String biography;


    public DomainUser() {
        super();
    }

    public DomainUser(final String username, final String firstName, final String lastName, final String birthday, final String email, final String password, final String authToken, final String biography) {

        super();
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.authToken = authToken;
        this.biography = biography;
    }

    public DomainUser(final int id, final String username, final String firstName, final String lastName, final String birthday, final String email, final String password, final String authToken, final String biography) {

        super();
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.authToken = authToken;
        this.biography = biography;
    }

    @Override
    public String toString() {
        return "DomainUser{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday='" + birthday + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", authToken='" + authToken + '\'' +
                ", biography='" + biography + '\'' +
                '}';
    }
}
