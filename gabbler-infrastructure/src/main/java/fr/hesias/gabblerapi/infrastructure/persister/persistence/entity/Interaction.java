package fr.hesias.gabblerapi.infrastructure.persister.persistence.entity;

import fr.hesias.gabblerapi.infrastructure.persister.persistence.model.ActionEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "interaction", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"uuid_user", "id_gab"})
})

public class Interaction {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(name = "action")
    @Enumerated(EnumType.STRING)
    private ActionEnum action;

    @Column(name = "action_date")
    private LocalDateTime actionDate = LocalDateTime.now();

    @JoinColumn(name = "uuid_user", nullable = false, referencedColumnName = "uuid")
    @ManyToOne
    private User user;

    @JoinColumn(name = "id_gab", nullable = false, referencedColumnName = "id")
    @ManyToOne
    private Gab gab;

}
