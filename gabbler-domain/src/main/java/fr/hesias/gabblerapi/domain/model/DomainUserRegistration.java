package fr.hesias.gabblerapi.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DomainUserRegistration {


    private String email;

    private String password;

    private String username;

    private String firstName;

    private String lastName;

    private LocalDate birthday;


    public DomainUserRegistration() {
    }

    public DomainUserRegistration(String email, String password, String username, String firstName, String lastName, LocalDate birthday) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }
}
