package fr.hesias.gabblerapi.infrastructure.persister.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
    @Column(name = "auth_token")
    private String authToken;
    private String biography;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @Column(name = "path_avatar")
    private String pathAvatar;
    @Column(name = "is_validated")
    private Boolean isValidated;


    public User() {
        super();
    }

    public User(final String username, final String firstname, final String lastname, final LocalDate birthday, final String email, final String password, final String biography, final LocalDateTime creationDate, final String pathAvatar, final Boolean isValidated) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.biography = biography;
        this.creationDate = creationDate;
        this.pathAvatar = pathAvatar;
        this.isValidated = isValidated;
    }

    public User(final int id, final String username, final String firstname, final String lastname) {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
    }

}
