package fr.hesias.gabblerapi.infrastructure.persister.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})

public class User
{

    @Id
    @Column(name = "uuid")
    private String uuid = UUID.randomUUID().toString();

    @Size(min = 3, max = 12)
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

    @Column(name = "is_validated")
    private Boolean isValidated = false;

    @Column(name = "roles")
    private String roles = "USER";

    @OneToMany(mappedBy = "user")
    private List<Gab> gabs;

    @OneToMany(mappedBy = "user")
    private List<Interaction> interactions;

    @OneToMany(mappedBy = "user")
    private List<Media> medias;

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
        this.isValidated = isValidated;
        this.roles = roles;
    }

    public User(final String uuid,
                final String username,
                final String firstname,
                final String lastname,
                final LocalDate birthday,
                final String email,
                final String password,
                final String authToken,
                final String biography,
                final Boolean isValidated,
                final String roles)
    {

        super();
        this.uuid = uuid;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.authToken = authToken;
        this.biography = biography;
        this.isValidated = isValidated;
        this.roles = roles;
    }

    public User(
            final String email,
            final String username,
            final String firstname,
            final String lastname,
            final String roles
               )
    {

        super();
        this.email = email;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.roles = roles;
    }

    //Pour la création d'un utilisateur
    public User(
            final String email,
            final String password,
            final String username,
            final String firstname,
            final String lastname,
            final LocalDate birthday
               )
    {

        super();
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
    }

    public User(String uuid)
    {

        this.uuid = uuid;
    }

}
