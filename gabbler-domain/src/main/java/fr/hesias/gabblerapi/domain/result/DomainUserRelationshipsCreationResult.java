package fr.hesias.gabblerapi.domain.result;

import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;
import fr.hesias.gabblerapi.domain.model.DomainUserRelationshipsCreation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DomainUserRelationshipsCreationResult extends DomainResultable
{

    private DomainUserRelationshipsCreation domainUserRelationshipsCreation;


    public DomainUserRelationshipsCreationResult(DomainAccessStatus domainAccessStatus)
    {

        super(domainAccessStatus);
    }

    public DomainUserRelationshipsCreationResult(DomainAccessStatus domainAccessStatus,
                                                 DomainUserRelationshipsCreation domainUserRelationshipsCreation)
    {

        super(domainAccessStatus);
        this.domainUserRelationshipsCreation = domainUserRelationshipsCreation;
    }

}
