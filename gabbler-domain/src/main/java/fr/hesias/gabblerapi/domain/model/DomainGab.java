package fr.hesias.gabblerapi.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DomainGab {

    private int id;

    private String content;

    private LocalDateTime postDate;

    private int nbLikes;

    private int nbDislikes;

    private DomainUser user;


    public DomainGab() {
    }

    public DomainGab(int id, String content, LocalDateTime postDate, DomainUser user) {
        this.id = id;
        this.content = content;
        this.postDate = postDate;
        this.user = user;
    }

    public DomainGab(final int id, final String content, final LocalDateTime postDate, final int nbLikes, final int nbDislikes, final DomainUser user) {
        this.id = id;
        this.content = content;
        this.postDate = postDate;
        this.nbLikes = nbLikes;
        this.nbDislikes = nbDislikes;
        this.user = user;
    }
}
