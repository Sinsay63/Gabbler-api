package fr.hesias.gabblerapi.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DomainUser {

    private String uuid;

    private String username;

    private String firstName;

    private String lastName;

    private LocalDate birthday;

    private String email;

    private String biography;

    private String roles;


    public DomainUser() {

        super();
    }

    public DomainUser(final String username,
                      final String firstName,
                      final String lastName,
                      final LocalDate birthday,
                      final String email,
                      final String biography,
                      final String roles) {

        super();
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.biography = biography;
        this.roles = roles;
    }

    public DomainUser(final String uuid,
                      final String username,
                      final String firstName,
                      final String lastName,
                      final LocalDate birthday,
                      final String email,
                      final String biography,
                      final String roles) {

        super();
        this.uuid = uuid;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.biography = biography;
        this.roles = roles;
    }

    @Override
    public String toString() {

        return "DomainUser{" +
                "uuid='" + uuid + '\'' +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday='" + birthday + '\'' +
                ", email='" + email + '\'' +
                ", biography='" + biography + '\'' +
                '}';
    }

}
