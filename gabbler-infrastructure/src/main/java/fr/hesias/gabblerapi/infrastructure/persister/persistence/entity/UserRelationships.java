package fr.hesias.gabblerapi.infrastructure.persister.persistence.entity;

import fr.hesias.gabblerapi.infrastructure.persister.persistence.model.TypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
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
    private TypeEnum typeEnum;

    @Column(name = "date")
    private LocalDateTime date;

    @JoinColumn(name = "id_user", nullable = false, referencedColumnName = "id")
    @OneToOne
    private User user;

    @JoinColumn(name = "id_user_related", nullable = false, referencedColumnName = "id")
    @OneToOne
    private User userRelated;


    public UserRelationships(final int id, final TypeEnum typeEnum, final LocalDateTime date, final User user, final User userRelated) {
        this.id = id;
        this.typeEnum = typeEnum;
        this.date = date;
        this.user = user;
        this.userRelated = userRelated;
    }

    public UserRelationships() {

    }
}
