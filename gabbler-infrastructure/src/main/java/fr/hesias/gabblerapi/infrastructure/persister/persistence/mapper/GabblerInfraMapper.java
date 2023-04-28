package fr.hesias.gabblerapi.infrastructure.persister.persistence.mapper;

import fr.hesias.gabblerapi.domain.model.*;
import fr.hesias.gabblerapi.domain.result.*;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.Gab;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.Interaction;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.Media;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.User;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.model.MediaTypeEnum;

import java.util.ArrayList;
import java.util.List;

import static fr.hesias.gabblerapi.domain.model.DomainAccessStatus.OK;

public class GabblerInfraMapper
{

    public DomainUser toUserToDomainUser(final User user)
    {

        return new DomainUser(user.getUuid(),
                              user.getEmail(),
                              user.getUsername(),
                              user.getFirstname(),
                              user.getLastname(),
                              user.getRoles());
    }

    public User toDomainUserToUser(final DomainUser domainUser)
    {

        return new User(domainUser.getEmail(),
                        domainUser.getUsername(),
                        domainUser.getFirstName(),
                        domainUser.getLastName(),
                        domainUser.getRoles());
    }

    public DomainUserResult toDomainUserToDomainUserResult(final DomainAccessStatus domainAccessStatus,
                                                           final DomainUser domainUser)
    {

        return new DomainUserResult(domainAccessStatus, domainUser);
    }

    public DomainUserResult toUserToDomainUserResult(final User user)
    {

        DomainUser domainUser = toUserToDomainUser(user);
        return new DomainUserResult(OK, domainUser);
    }

    public DomainGab toGabToDomainGab(final Gab gab)
    {

        return new DomainGab(gab.getId(),
                             gab.getContent(),
                             gab.getPostDate(),
                             toUserToDomainUser(gab.getUser()),
                             toMediaListToDomainMediaList(gab.getMedias()));
    }

    public DomainGabResult toGabToDomainGabResult(final Gab gab, final DomainMediasResult domainMediasResult)
    {

        DomainGab domainGab = toGabToDomainGab(gab);
        return new DomainGabResult(OK, domainGab, domainMediasResult);
    }

    public DomainGabsResult toGabsToDomainGabsResult(final List<Gab> gabs,
                                                     final DomainMediasResult domainMediasResult)
    {

        List<DomainGabResult> domainGabResults = new ArrayList<>();
        for (Gab gab : gabs)
        {
            domainGabResults.add(toGabToDomainGabResult(gab, domainMediasResult));
        }
        return new DomainGabsResult(OK, domainGabResults);
    }

    public List<DomainGab> toGabsToDomainGabsResult(final List<Gab> gabs)
    {

        List<DomainGab> domainGabs = new ArrayList<>();
        for (Gab gab : gabs)
        {
            domainGabs.add(toGabToDomainGab(gab));
        }
        return domainGabs;

    }

    public Gab toDomainGabToGab(final DomainGab domainGab)
    {

        return new Gab(domainGab.getId(),
                       domainGab.getContent(),
                       domainGab.getPostDate(),
                       toDomainUserToUser(domainGab.getUser()));
    }

    public Gab toDomainGabCreationToGab(final DomainGabCreation domainCreationGab)
    {

        User user = new User();
        user.setUuid(domainCreationGab.getUserUuid());

        Gab gab = new Gab(domainCreationGab.getContent(), domainCreationGab.getPostDate(), user);
        if (domainCreationGab.getParentId() > 0)
        {
            Gab parentGab = new Gab();
            parentGab.setId(domainCreationGab.getParentId());
            gab.setParentGab(parentGab);
        }
        return gab;
    }

    public List<DomainInteraction> toInteractionsListToDomainInteractionList(final List<Interaction> interactions)
    {

        List<DomainInteraction> domainInteractions = new ArrayList<>();
        for (Interaction interaction : interactions)
        {
            domainInteractions.add(toInteractionToDomainInteraction(interaction));
        }
        return domainInteractions;

    }

    private DomainInteraction toInteractionToDomainInteraction(Interaction interaction)
    {

        return new DomainInteraction(interaction.getAction().getActionType(),
                                     interaction.getActionDate(),
                                     interaction.getUser().getUuid(),
                                     interaction.getGab().getId());

    }


    public DomainMedia toMediaToDomainMedia(final Media media)
    {

        return new DomainMedia(media.getId(),
                               media.getUrl(),
                               media.getType().getMediaType(),
                               media.getDate());
    }

    public List<DomainMedia> toMediaListToDomainMediaList(final List<Media> mediaList)
    {

        List<DomainMedia> domainMediaList = new ArrayList<>();
        for (Media media : mediaList)
        {
            domainMediaList.add(toMediaToDomainMedia(media));
        }
        return domainMediaList;
    }

    public DomainUserAuth toUserToDomainUserAuth(final User user)
    {

        return new DomainUserAuth(toUserToDomainUser(user), user.getPassword());
    }

    public User toDomainUserRegistrationToUser(final DomainUserRegistration domainUserRegistration)
    {

        return new User(domainUserRegistration.getEmail(),
                        domainUserRegistration.getPassword(),
                        domainUserRegistration.getUsername(),
                        domainUserRegistration.getFirstName(),
                        domainUserRegistration.getLastName(),
                        domainUserRegistration.getBirthday());

    }

    public DomainUserRegistration toDomainUserRegistrationInfosResultToDomainUserRegistration(final DomainUserRegistrationInfosResult domainUserRegistrationInfosResult)
    {

        return domainUserRegistrationInfosResult.getUserRegistration();
    }

    public User toDomainUserRegistrationInfosResultToUser(final DomainUserRegistrationInfosResult domainUserRegistrationInfosResult)
    {

        return toDomainUserRegistrationToUser(toDomainUserRegistrationInfosResultToDomainUserRegistration(
                domainUserRegistrationInfosResult));
    }

    public DomainMediasResult toDomainMediasToDomainMediasResult(final DomainAccessStatus domainAccessStatus,
                                                                 final List<DomainMediaResult> domainMediasResults)
    {

        return new DomainMediasResult(domainAccessStatus, domainMediasResults);
    }

    public DomainMediaResult toDomainMediaToDomainMediaResult(final DomainAccessStatus domainAccessStatus,
                                                              final DomainMedia domainMedia)
    {

        return new DomainMediaResult(domainAccessStatus, domainMedia);
    }

    public DomainMediasResult toMediaListToDomainMediasResult(final List<Media> mediaList)
    {

        DomainAccessStatus domainAccessStatus = OK;
        List<DomainMediaResult> domainMediaResultList = new ArrayList<>();
        for (Media media : mediaList)
        {
            domainMediaResultList.add(toDomainMediaToDomainMediaResult(domainAccessStatus,
                                                                       toMediaToDomainMedia(media)));
        }

        return new DomainMediasResult(domainAccessStatus, domainMediaResultList);

    }


    public DomainUserInteractionResult toInteractionToDomainUserInteractionResult(final Interaction interaction)
    {

        return new DomainUserInteractionResult(OK,
                                               (interaction.getUser().getUuid()),
                                               interaction.getAction().getActionType(),
                                               interaction.getGab().getId());
    }

    public DomainUserInteractionsResult toInteractionsListToDomainUserInteractionsResult(final List<Interaction> interactions)
    {

        List<DomainUserInteractionResult> domainUserInteractionResults = new ArrayList<>();
        for (Interaction interaction : interactions)
        {
            domainUserInteractionResults.add(toInteractionToDomainUserInteractionResult(interaction));
        }
        return new DomainUserInteractionsResult(OK, domainUserInteractionResults);
    }

    public DomainUserProfile toUserToDomainUserProfile(User daoUser,
                                                       List<Media> mediaList,
                                                       List<Interaction> interactions,
                                                       List<Gab> gabs)
    {

        DomainUserProfile domainUserProfile = new DomainUserProfile();
        domainUserProfile.setUuid(daoUser.getUuid());
        domainUserProfile.setUsername(daoUser.getUsername());
        domainUserProfile.setFirstName(daoUser.getFirstname());
        domainUserProfile.setLastName(daoUser.getLastname());
        domainUserProfile.setBiography(daoUser.getBiography());
        domainUserProfile.setBirthday(daoUser.getBirthday());
        domainUserProfile.setGabs(toGabsToDomainGabsResult(gabs));
        domainUserProfile.setInteractions(toInteractionsListToDomainInteractionList(interactions));
        if (mediaList != null)
        {
            for (Media media : mediaList)
            {
                if (media.getType() == MediaTypeEnum.AVATAR)
                {
                    domainUserProfile.setAvatar(toMediaToDomainMedia(media));
                }
                else if (media.getType() == MediaTypeEnum.BANNER)
                {
                    domainUserProfile.setBanner(toMediaToDomainMedia(media));
                }
            }
        }

        return domainUserProfile;
    }

}
