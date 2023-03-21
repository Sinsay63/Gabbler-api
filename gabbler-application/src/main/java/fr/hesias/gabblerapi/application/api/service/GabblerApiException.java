package fr.hesias.gabblerapi.application.api.service;

import fr.hesias.gabblerapi.domain.result.DomainResultable;
import lombok.Getter;

@Getter
public class GabblerApiException extends RuntimeException {

    private final DomainResultable domainResultable;

    /**
     * Constructeur par défaut
     */
    public GabblerApiException() {

        super();

        domainResultable = null;
    }

    /**
     * Constructeur
     *
     * @param domainResultable l'erreur rencontrée par le domaine
     */
    public GabblerApiException(final DomainResultable domainResultable) {

        super(domainResultable.getMessage());

        this.domainResultable = domainResultable;
    }

}
