package fr.hesias.gabblerapi.infrastructure.persister.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
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

    @JoinColumn(name = "uuid_user", nullable = false, referencedColumnName = "uuid")
    @ManyToOne
    private User user;

    @JoinColumn(name = "id_parent_gab")
    @ManyToOne
    private Gab parentGab = null;

    @OneToMany(mappedBy = "parentGab")
    private List<Gab> subGabs = new ArrayList<>();

    @OneToMany(mappedBy = "gab")
    private List<Media> medias = new ArrayList<>();

    public Gab(int id, String content, LocalDateTime postDate, User user)
    {

        this.id = id;
        this.content = content;
        this.postDate = postDate;
        this.user = user;
    }

    public Gab(int id, String content, LocalDateTime postDate, User user, Gab parentGab)
    {

        this.id = id;
        this.content = content;
        this.postDate = postDate;
        this.user = user;
        this.parentGab = parentGab;
    }

    public Gab(int id, String content, LocalDateTime postDate, User user, Gab parentGab, List<Gab> subGabs)
    {

        this.id = id;
        this.content = content;
        this.postDate = postDate;
        this.user = user;
        this.parentGab = parentGab;
        this.subGabs = subGabs;
    }

    public Gab(String content, LocalDateTime postDate, User user)
    {

        this.content = content;
        this.postDate = postDate;
        this.user = user;
    }

    public Gab(int id)
    {

        this.id = id;
    }

//    @Override
//    public String toString()
//    {
//
//        String parentGabId = parentGab == null ? "aucun" : String.valueOf(parentGab.getId());
//        return "Gab{" +
//                "id=" + id +
//                ", content='" + content + '\'' +
//                ", postDate=" + postDate +
//                ", userUuid=" + user.getUuid() +
//                ", parentGab=" + parentGabId +
//                ", subGabsSize=" + subGabs +
//                '}';
//    }

}
