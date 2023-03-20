package fr.hesias.gabblerapi.infrastructure.persister.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private LocalDateTime creationDate;

    @Column(name = "path_avatar")
    private String pathAvatar;

    @Column(name = "is_validated")
    private Boolean isValidated;

    @ManyToOne()
    private List<Gab> gabs;

    @ManyToOne()
    private List<Subscription> subscriptions;

    @ManyToOne()
    private List<Interaction> interactions;

}
