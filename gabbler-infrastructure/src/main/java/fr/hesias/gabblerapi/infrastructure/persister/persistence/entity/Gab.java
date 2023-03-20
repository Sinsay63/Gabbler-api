package fr.hesias.gabblerapi.infrastructure.persister.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "gab")
public class Gab
{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    @Column(name = "content")
    private String content;

    @Column(name = "post_date")
    private LocalDate postDate;

    @JoinColumn(name = "id_user", nullable = false, referencedColumnName = "id")
    @OneToMany
    private User user;
}
