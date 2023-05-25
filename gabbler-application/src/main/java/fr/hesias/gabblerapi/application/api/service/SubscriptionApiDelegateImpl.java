package fr.hesias.gabblerapi.application.api.service;

import fr.hesias.gabblerapi.application.adapter.SubscriptionAccessorAdapter;
import fr.hesias.gabblerapi.application.api.mapper.SubscriptionApiMapper;
import fr.hesias.gabblerapi.desc.api.server.SubscriptionApiDelegate;
import fr.hesias.gabblerapi.desc.api.server.model.UserSubscription;
import fr.hesias.gabblerapi.domain.result.DomainSubscriptionResult;
import org.springframework.http.ResponseEntity;

public class SubscriptionApiDelegateImpl implements SubscriptionApiDelegate
{

    private final SubscriptionAccessorAdapter subscriptionAccessorAdapter;

    private final SubscriptionApiMapper subscriptionApiMapper;

    private final GabblerApiService gabblerApiService;

    public SubscriptionApiDelegateImpl(SubscriptionAccessorAdapter subscriptionAccessorAdapter,
                                       SubscriptionApiMapper subscriptionApiMapper, GabblerApiService gabblerApiService)
    {

        this.subscriptionAccessorAdapter = subscriptionAccessorAdapter;
        this.subscriptionApiMapper = subscriptionApiMapper;
        this.gabblerApiService = gabblerApiService;
    }

    @Override
    public ResponseEntity<UserSubscription> subscribeUser(UserSubscription userSubscription)
    {

        DomainSubscriptionResult domainSubscriptionResult = subscriptionAccessorAdapter.getSubscriptionByUserUuid(
                "7b64bc72-ed0a-43cc-8cb5-8014cae763db");
        return this.gabblerApiService.getResponse(this.subscriptionApiMapper.toDomainSubscriptionToSubscriptionResult(
                                                          domainSubscriptionResult),
                                                  domainSubscriptionResult);
    }

    @Override
    public ResponseEntity<UserSubscription> getSubscriptionByUserUuid(String uuid)
    {

        DomainSubscriptionResult domainSubscriptionResult = subscriptionAccessorAdapter.getSubscriptionByUserUuid(uuid);
        return this.gabblerApiService.getResponse(this.subscriptionApiMapper.toDomainSubscriptionToSubscriptionResult(
                                                          domainSubscriptionResult),
                                                  domainSubscriptionResult);
    }

}
