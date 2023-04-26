package fr.hesias.gabblerapi.infrastructure.persister.persistence.entity;

import fr.hesias.gabblerapi.infrastructure.persister.persistence.model.RelationshipTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_relationships", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"uuid_user", "uuid_user_related"})
})
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

}
