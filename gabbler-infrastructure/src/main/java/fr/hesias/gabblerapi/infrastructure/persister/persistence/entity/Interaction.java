package fr.hesias.gabblerapi.infrastructure.persister.persistence.entity;

import fr.hesias.gabblerapi.infrastructure.persister.persistence.model.ActionEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "interaction")

public class Interaction
{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(name = "action")
    @Enumerated(EnumType.STRING)
    private ActionEnum action;

    @Column(name = "action_date")
    private LocalDate actionDate = LocalDate.now();

    @JoinColumn(name = "id_user", nullable = false, referencedColumnName = "id")
    @ManyToOne
    private User user;

    @JoinColumn(name = "id_gab", nullable = false, referencedColumnName = "id")
    @ManyToOne
    private Gab gab;

}
