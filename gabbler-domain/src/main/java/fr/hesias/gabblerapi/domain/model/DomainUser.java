package fr.hesias.gabblerapi.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DomainUser {

    private String uuid;

    private String email;

    private String username;

    private String firstName;

    private String lastName;

    private List<DomainMedia> medias;

    private String roles;


    public DomainUser() {

        super();
    }

    public DomainUser(final String email,
                      final String username,
                      final String firstName,
                      final String lastName,
                      final String roles) {

        super();
        this.email = email;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
    }

    public DomainUser(final String uuid,
                      final String email,
                      final String username,
                      final String firstName,
                      final String lastName,
                      final String roles) {

        super();
        this.uuid = uuid;
        this.email = email;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
    }

    @Override
    public String toString() {

        return "DomainUser{" +
                "uuid='" + uuid + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", medias=" + medias +
                ", roles='" + roles + '\'' +
                '}';
    }

}
