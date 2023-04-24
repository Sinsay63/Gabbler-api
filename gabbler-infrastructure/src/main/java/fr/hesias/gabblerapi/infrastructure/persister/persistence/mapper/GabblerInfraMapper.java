package fr.hesias.gabblerapi.infrastructure.persister.persistence.mapper;

import fr.hesias.gabblerapi.domain.model.*;
import fr.hesias.gabblerapi.domain.result.*;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.Gab;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.Media;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.User;

import java.util.ArrayList;
import java.util.List;

import static fr.hesias.gabblerapi.domain.model.DomainAccessStatus.OK;

public class GabblerInfraMapper {

    public DomainUser toUserToDomainUser(final User user) {

        return new DomainUser(user.getUuid(),
                user.getEmail(),
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getRoles());
    }

    public User toDomainUserToUser(final DomainUser domainUser) {

        return new User(domainUser.getEmail(),
                domainUser.getUsername(),
                domainUser.getFirstName(),
                domainUser.getLastName(),
                domainUser.getRoles());
    }

    public DomainUserResult toDomainUserToDomainUserResult(final DomainAccessStatus domainAccessStatus,
                                                           final DomainUser domainUser) {

        return new DomainUserResult(domainAccessStatus, domainUser);
    }

    public DomainUserInfosResult toDomainUserToDomainUserInfosResult(final DomainAccessStatus domainAccessStatus,
                                                                     final DomainUser domainUser,
                                                                     final DomainMediasResult domainMediasResult) {

        return new DomainUserInfosResult(domainAccessStatus, domainUser, domainMediasResult);
    }

    public DomainGab toGabToDomainGab(final Gab gab) {

        return new DomainGab(gab.getId(), gab.getContent(), gab.getPostDate(), toUserToDomainUser(gab.getUser()));
    }


    public Gab toDomainGabToGab(final DomainGab domainGab) {

        return new Gab(domainGab.getId(), domainGab.getContent(), domainGab.getPostDate(), toDomainUserToUser(domainGab.getUser()));
    }

    public Gab toDomainGabCreationToGab(final DomainGabCreation domainCreationGab) {

        User user = new User();
        user.setUuid(domainCreationGab.getUserUuid());

        Gab gab = new Gab(domainCreationGab.getContent(), domainCreationGab.getPostDate(), user);
        if (domainCreationGab.getParentId() > 0) {
            Gab parentGab = new Gab();
            parentGab.setId(domainCreationGab.getParentId());
            gab.setParentGab(parentGab);
        }
        return gab;
    }


    public DomainMedia toMediaToDomainMedia(final Media media) {

        return new DomainMedia(media.getId(),
                media.getUrl(),
                media.getType().getMediaType(),
                media.getDate());
    }

    public DomainUserAuth toUserToDomainUserAuth(final User user) {

        return new DomainUserAuth(toUserToDomainUser(user), user.getPassword());
    }

    public User toDomainUserRegistrationToUser(final DomainUserRegistration domainUserRegistration) {

        return new User(domainUserRegistration.getEmail(),
                domainUserRegistration.getPassword(),
                domainUserRegistration.getUsername(),
                domainUserRegistration.getFirstName(),
                domainUserRegistration.getLastName(),
                domainUserRegistration.getBirthday());

    }

    public DomainUserRegistration toDomainUserRegistrationInfosResultToDomainUserRegistration(final DomainUserRegistrationInfosResult domainUserRegistrationInfosResult) {

        return domainUserRegistrationInfosResult.getUserRegistration();
    }

    public User toDomainUserRegistrationInfosResultToUser(final DomainUserRegistrationInfosResult domainUserRegistrationInfosResult) {

        return toDomainUserRegistrationToUser(toDomainUserRegistrationInfosResultToDomainUserRegistration(domainUserRegistrationInfosResult));
    }

    public DomainMediasResult toDomainMediasToDomainMediasResult(final DomainAccessStatus domainAccessStatus,
                                                                 final List<DomainMediaResult> domainMediasResults) {

        return new DomainMediasResult(domainAccessStatus, domainMediasResults);
    }

    public DomainMediaResult toDomainMediaToDomainMediaResult(final DomainAccessStatus domainAccessStatus,
                                                              final DomainMedia domainMedia) {

        return new DomainMediaResult(domainAccessStatus, domainMedia);
    }

    public DomainMediasResult toMediaListToDomainMediasResult(final List<Media> mediaList) {
        DomainAccessStatus domainAccessStatus = OK;
        List<DomainMediaResult> domainMediaResultList = new ArrayList<>();
        for (Media media : mediaList) {
            domainMediaResultList.add(toDomainMediaToDomainMediaResult(domainAccessStatus, toMediaToDomainMedia(media)));
        }

        return new DomainMediasResult(domainAccessStatus, domainMediaResultList);

    }
}
