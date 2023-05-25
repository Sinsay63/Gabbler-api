package fr.hesias.gabblerapi.infrastructure.persister.service;

import fr.hesias.gabblerapi.domain.model.DomainSubscription;
import fr.hesias.gabblerapi.domain.result.DomainSubscriptionResult;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.dao.SubscriptionDao;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.Subscription;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.mapper.GabblerInfraMapper;
import org.springframework.transaction.annotation.Transactional;

import static fr.hesias.gabblerapi.domain.model.DomainAccessStatus.OK;

public class SubscriptionPersisterService
{

    private final SubscriptionDao subscriptionDao;

    private final GabblerInfraMapper gabblerInfraMapper;

    public SubscriptionPersisterService(SubscriptionDao subscriptionDao, GabblerInfraMapper gabblerInfraMapper)
    {

        this.subscriptionDao = subscriptionDao;
        this.gabblerInfraMapper = gabblerInfraMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    public DomainSubscriptionResult subscribeUser(DomainSubscription domainSubscription)
    {

//        subscriptionDao.s

        return new DomainSubscriptionResult(OK, domainSubscription);
    }

    @Transactional(readOnly = true)
    public DomainSubscriptionResult getSubscriptionByUserUuid(String uuid)
    {

        DomainSubscription domainSubscription = null;
        Subscription subscription = subscriptionDao.getSubscriptionByUserUuid(uuid);

        if (subscription != null)
        {
            domainSubscription = gabblerInfraMapper.toSubscriptionToDomainSubscription(subscription);
        }
        return new DomainSubscriptionResult(OK, domainSubscription);
    }


}
