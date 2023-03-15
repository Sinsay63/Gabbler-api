package fr.hesias.gabblerapi.infrastructure.persister.persistence.entity;

import fr.hesias.gabblerapi.infrastructure.persister.persistence.model.Type;
import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "user_relationships",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"id_user", "id_user_related"})})
public class UserRelationships {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;


    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type type;

    @Column(name = "date")
    private Date date;

    @JoinColumn(name = "id_user", nullable = false, referencedColumnName = "id")
    @OneToOne
    private User user;

    @JoinColumn(name = "id_user_related", nullable = false, referencedColumnName = "id")
    @OneToOne
    private User userRelated;


    public UserRelationships(int id, Type type, Date date, User user, User userRelated) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.user = user;
        this.userRelated = userRelated;
    }


    public UserRelationships() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUserRelated() {
        return userRelated;
    }

    public void setUserRelated(User userRelated) {
        this.userRelated = userRelated;
    }
}
