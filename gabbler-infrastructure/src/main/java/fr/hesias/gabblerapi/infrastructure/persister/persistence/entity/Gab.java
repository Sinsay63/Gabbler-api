package fr.hesias.gabblerapi.infrastructure.persister.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "gab")
public class Gab {

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

    @JoinColumn(name = "id_parent_gab")
    @ManyToOne
    private Gab parentGab;

    @OneToMany(mappedBy = "parentGab")
    private List<Gab> subGabs;

    public Gab(int id, String content, LocalDateTime postDate, User user) {
        this.id = id;
        this.content = content;
        this.postDate = postDate;
        this.user = user;
    }

    public Gab(int id, String content, LocalDateTime postDate, User user, Gab parentGab) {
        this.id = id;
        this.content = content;
        this.postDate = postDate;
        this.user = user;
        this.parentGab = parentGab;
    }

    public Gab(int id, String content, LocalDateTime postDate, User user, Gab parentGab, List<Gab> subGabs) {
        this.id = id;
        this.content = content;
        this.postDate = postDate;
        this.user = user;
        this.parentGab = parentGab;
        this.subGabs = subGabs;
    }
}
