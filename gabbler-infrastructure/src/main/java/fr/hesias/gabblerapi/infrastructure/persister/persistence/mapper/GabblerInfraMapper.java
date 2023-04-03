package fr.hesias.gabblerapi.infrastructure.persister.persistence.mapper;

import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;
import fr.hesias.gabblerapi.domain.model.DomainGab;
import fr.hesias.gabblerapi.domain.model.DomainUser;
import fr.hesias.gabblerapi.domain.result.DomainGabResult;
import fr.hesias.gabblerapi.domain.result.DomainUserResult;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.Gab;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.User;

public class GabblerInfraMapper {

    public DomainUser toUserToDomainUser(final User user) {
        return new DomainUser(user.getUuid(), user.getUsername(), user.getFirstname(), user.getLastname(), user.getBirthday(), user.getEmail(), user.getBiography());
    }

    public User toDomainUserToUser(final DomainUser domainUser) {
        return new User(domainUser.getUsername(), domainUser.getFirstName(), domainUser.getLastName(), domainUser.getBirthday(), domainUser.getEmail(), domainUser.getBiography());
    }

    public DomainUserResult toDomainUserToDomainUserResult(final DomainAccessStatus domainAccessStatus, final DomainUser domainUser) {
        return new DomainUserResult(domainAccessStatus, domainUser);
    }

    public DomainGab toGabToDomainGab(final Gab gab) {
        return new DomainGab(gab.getId(), gab.getContent(), gab.getPostDate(), toUserToDomainUser(gab.getUser()));
    }

    public DomainGabResult toDomainGabToDomainGabResult(final DomainAccessStatus domainAccessStatus, final DomainGab domainGab) {
        return new DomainGabResult(domainAccessStatus, domainGab);
    }
}
