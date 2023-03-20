package fr.hesias.gabblerapi.infrastructure.persister.persistence.entity;

import fr.hesias.gabblerapi.infrastructure.persister.persistence.model.TypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_relationships",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"id_user", "id_user_related"})})
public class UserRelationships
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;


    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TypeEnum type;

    @Column(name = "date")
    private LocalDateTime date;

    @JoinColumn(name = "id_user", nullable = false, referencedColumnName = "id")
    @OneToMany
    private User user;

    @JoinColumn(name = "id_user_related", nullable = false, referencedColumnName = "id")
    @OneToOne
    private User userRelated;

}
