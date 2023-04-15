package fr.hesias.gabblerapi.infrastructure.persister.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "gab")
public class Gab
{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(name = "content")
    private String content;

    @Column(name = "post_date")
    private LocalDateTime postDate = LocalDateTime.now();

    @JoinColumn(name = "id_user", nullable = false, referencedColumnName = "id")
    @ManyToOne
    private User user;

}
