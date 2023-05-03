package fr.hesias.gabblerapi.infrastructure.persister.persistence.entity;

import fr.hesias.gabblerapi.infrastructure.persister.persistence.model.RelationshipTypeEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Check;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_relationships")
public class UserRelationships
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private RelationshipTypeEnum type;

    @Column(name = "date")
    private LocalDateTime date = LocalDateTime.now();

    @JoinColumn(name = "uuid_user", nullable = false, referencedColumnName = "uuid")
    @ManyToOne
    private User user;

    @JoinColumn(name = "uuid_user_related", nullable = false, referencedColumnName = "uuid")
    @ManyToOne
    private User userRelated;

    public UserRelationships()
    {

        super();
    }

    public UserRelationships(final RelationshipTypeEnum type, final User user, final User userRelated)
    {

        super();
        this.type = type;
        this.user = user;
        this.userRelated = userRelated;
    }

    @Check(constraints = "(type = 'AUTHORIZED' AND uuid_user_related IS NULL) "
            + "OR (type = 'BLOCKED' AND uuid_user_related IS NULL) "
            + "OR (type = 'FOLLOWED' AND uuid_user_related IS NOT NULL AND uuid_user_related <> uuid_user) "
            + "OR (type NOT IN ('AUTHORIZED', 'BLOCKED', 'FOLLOWED'))")
    @Transient
    public void checkTypeAndUuid()
    {

    }

}
