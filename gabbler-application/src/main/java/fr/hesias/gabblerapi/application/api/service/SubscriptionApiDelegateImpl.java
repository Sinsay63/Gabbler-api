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
    public ResponseEntity<UserSubscription> subscribeUser(String userUuid, Integer offerId)
    {

        var domainSubscriptionResult = subscriptionAccessorAdapter.subscribeUser(
                this.subscriptionApiMapper.toSubscriptionToDomainSubscription(userUuid, offerId));
        if (domainSubscriptionResult.isOk())
        {
            return this.gabblerApiService.getResponse(this.subscriptionApiMapper.toDomainSubscriptionToSubscriptionResult(
                                                              domainSubscriptionResult),
                                                      domainSubscriptionResult);
        }
        else
        {
            return ResponseEntity.internalServerError().build();
        }
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
