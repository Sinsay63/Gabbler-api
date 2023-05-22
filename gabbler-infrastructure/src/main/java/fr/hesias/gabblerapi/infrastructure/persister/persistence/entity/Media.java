package fr.hesias.gabblerapi.infrastructure.persister.persistence.entity;

import fr.hesias.gabblerapi.infrastructure.persister.persistence.model.MediaTypeEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "media", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"uuid_user", "type"})
})
public class Media
{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private MediaTypeEnum type;

    @Column(name = "url")
    private String url;

    @Column(name = "date")
    private LocalDateTime date = LocalDateTime.now();

    @JoinColumn(name = "uuid_user", referencedColumnName = "uuid")
    @ManyToOne
    private User user;

    @JoinColumn(name = "id_gab", referencedColumnName = "id")
    @ManyToOne
    private Gab gab;

}
