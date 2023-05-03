package fr.hesias.gabblerapi.domain.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DomainUserRelationshipsCreation
{

    private String userUuid;

    private String userRelatedUuid;

    private String type;

    public DomainUserRelationshipsCreation(String userUuid, String userRelatedUuid, String type)
    {

        this.userUuid = userUuid;
        this.userRelatedUuid = userRelatedUuid;
        this.type = type;
    }


}
