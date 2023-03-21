package fr.hesias.gabblerapi.application.api.service;


import fr.hesias.gabblerapi.domain.port.secondary.UserPersister;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GabblerApiDelegateImpl {

    private final UserPersister userPersister;

    public GabblerApiDelegateImpl(final UserPersister userPersister) {

        super();
        this.userPersister = userPersister;
    }


}
