package fr.hesias.gabblerapi.infrastructure.persister.service;

import fr.hesias.gabblerapi.domain.model.DomainSubscription;
import fr.hesias.gabblerapi.domain.result.DomainSubscriptionResult;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.dao.SubscriptionDao;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.dao.UserDao;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.Subscription;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.mapper.GabblerInfraMapper;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static fr.hesias.gabblerapi.domain.model.DomainAccessStatus.INTERNAL_ERROR;
import static fr.hesias.gabblerapi.domain.model.DomainAccessStatus.OK;

public class SubscriptionPersisterService {

    private final SubscriptionDao subscriptionDao;

    private final UserDao userDao;

    private final GabblerInfraMapper gabblerInfraMapper;

    public SubscriptionPersisterService(SubscriptionDao subscriptionDao,
                                        UserDao userDao,
                                        GabblerInfraMapper gabblerInfraMapper) {

        this.subscriptionDao = subscriptionDao;
        this.userDao = userDao;
        this.gabblerInfraMapper = gabblerInfraMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    public DomainSubscriptionResult subscribeUser(DomainSubscriptionResult domainSubscriptionResult) {

        try {
            var offer_id = domainSubscriptionResult.getDomainSubscription().getSubscriptionOfferId();
            LocalDateTime endDate = switch (offer_id) {
                case 1 -> LocalDateTime.now().plusMonths(1);
                case 2 -> LocalDateTime.now().plusMonths(3);
                case 3 -> LocalDateTime.now().plusMonths(6);
                case 4 -> LocalDateTime.now().plusMonths(12);
                default -> LocalDateTime.now().plusMonths(1);
            };
            var subcription = gabblerInfraMapper.toDomainSubscriptionToSubscription(
                    domainSubscriptionResult.getDomainSubscription());
            subcription.setEndDate(endDate);

            var createdSubscription = subscriptionDao.subscribeUser(subcription);
            userDao.addPremiumRoleByUserUuid(createdSubscription.getUser().getUuid());

            return new DomainSubscriptionResult(OK,
                    gabblerInfraMapper.toSubscriptionToDomainSubscription(
                            createdSubscription));
        } catch (Exception e) {
            return new DomainSubscriptionResult(INTERNAL_ERROR, null);
        }
    }

    @Transactional(readOnly = true)
    public DomainSubscriptionResult getSubscriptionByUserUuid(String uuid) {

        DomainSubscription domainSubscription = null;
        Subscription subscription = subscriptionDao.getSubscriptionByUserUuid(uuid);

        if (subscription != null) {
            domainSubscription = gabblerInfraMapper.toSubscriptionToDomainSubscription(subscription);
        }
        return new DomainSubscriptionResult(OK, domainSubscription);
    }


}
