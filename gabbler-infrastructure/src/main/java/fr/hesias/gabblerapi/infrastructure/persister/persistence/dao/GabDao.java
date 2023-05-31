package fr.hesias.gabblerapi.infrastructure.persister.persistence.dao;

import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.Gab;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.User;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.repository.GabRepository;

import java.util.List;

public class GabDao
{

    private final GabRepository gabRepository;

    public GabDao(final GabRepository gabRepository)
    {

        super();
        this.gabRepository = gabRepository;
    }

    public Gab getGabById(final int id)
    {

        return gabRepository.findById(id).orElse(null);
    }

    public List<Gab> getGabs()
    {

        return gabRepository.findAll();
    }

    public List<Gab> getGabsByUserUuid(final String userUuid)
    {

        return gabRepository.findAllByUserUuidAndParentGabNullOrderByPostDateDesc(userUuid);
    }

    public List<Gab> getCommentsByParentGabId(final int parentGabId)
    {

        return gabRepository.findAllByParentGabIdOrderByPostDateAsc(parentGabId);
    }

    public Gab createGab(final Gab gab)
    {

        return gabRepository.save(gab);
    }

    public List<Gab> getFeed()
    {

        return gabRepository.findAllByOrderByPostDateDesc();
    }

    public List<Gab> getGabsBySearch(String researchContent)
    {

        return gabRepository.findAllByContentContainingIgnoreCaseAndParentGabNull(researchContent);
    }

    public List<Gab> getGabsByUsersFollowed(List<User> users)
    {

        return gabRepository.findAllByUserInAndParentGabNullOrderByPostDateDesc(users);
    }

    public List<Gab> getGabsForUserConnectedFeed(String userUuid)
    {

        return gabRepository.findAllByUser_UuidNotAndParentGabNull(userUuid);
    }

}
