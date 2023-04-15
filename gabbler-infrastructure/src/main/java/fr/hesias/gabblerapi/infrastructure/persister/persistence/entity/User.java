package fr.hesias.gabblerapi.infrastructure.persister.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
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

    @Column(name = "biography")
    private String biography;

    @Column(name = "creation_date")
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(name = "path_avatar")
    private String pathAvatar;

    @Column(name = "is_validated")
    private Boolean isValidated;

    @Column(name = "roles")
    private String roles;

    @OneToMany(mappedBy = "user")
    private List<Gab> gabs;

    @OneToMany(mappedBy = "user")
    private List<Interaction> interactions;


    public User()
    {

        super();
    }

    public User(final String username,
                final String firstname,
                final String lastname,
                final LocalDate birthday,
                final String email,
                final String password,
                final String authToken,
                final String biography,
                final String pathAvatar,
                final Boolean isValidated,
                final String roles)
    {

        super();
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.authToken = authToken;
        this.biography = biography;
        this.pathAvatar = pathAvatar;
        this.isValidated = isValidated;
        this.roles = roles;
    }

    public User(final int id,
                final String username,
                final String firstname,
                final String lastname,
                final LocalDate birthday,
                final String email,
                final String password,
                final String authToken,
                final String biography,
                final String pathAvatar,
                final Boolean isValidated,
                final String roles)
    {

        super();
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.authToken = authToken;
        this.biography = biography;
        this.pathAvatar = pathAvatar;
        this.isValidated = isValidated;
        this.roles = roles;
    }

    public User(
            final String username,
            final String firstname,
            final String lastname,
            final LocalDate birthday,
            final String email,
            final String biography,
            final String roles
               )
    {

        super();
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.email = email;
        this.biography = biography;
        this.roles = roles;
    }

}
