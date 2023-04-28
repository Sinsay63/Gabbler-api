package fr.hesias.gabblerapi.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class DomainGab
{

    private int id;

    private String content;

    private LocalDateTime postDate;

    private int nbLikes;

    private int nbDislikes;

    private int nbComments;

    private DomainUser user;

    private List<DomainMedia> medias;


    public DomainGab()
    {

    }

    public DomainGab(int id, String content, LocalDateTime postDate, DomainUser user, List<DomainMedia> medias)
    {

        this.id = id;
        this.content = content;
        this.postDate = postDate;
        this.user = user;
        this.medias = medias;
    }

    public DomainGab(String content, LocalDateTime postDate, DomainUser user)
    {

        this.content = content;
        this.postDate = postDate;
        this.user = user;
    }

    public DomainGab(final int id, final String content,
                     final LocalDateTime postDate,
                     final int nbLikes,
                     final int nbDislikes,
                     final int nbComments,
                     final DomainUser user)
    {

        this.id = id;
        this.content = content;
        this.postDate = postDate;
        this.nbLikes = nbLikes;
        this.nbDislikes = nbDislikes;
        this.nbComments = nbComments;

        this.user = user;
    }

    public DomainGab(int id, String content, LocalDateTime postDate, DomainUser userToDomainUser)
    {

        this.id = id;
        this.content = content;
        this.postDate = postDate;
        this.user = userToDomainUser;

    }

}
