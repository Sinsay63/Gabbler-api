package fr.hesias.gabblerapi.application.api.service;

import fr.hesias.gabblerapi.application.adapter.UserRelationshipsAccessorAdapter;
import fr.hesias.gabblerapi.desc.api.server.RelationshipApiDelegate;
import fr.hesias.gabblerapi.domain.model.DomainUserRelationshipsCreation;
import fr.hesias.gabblerapi.domain.result.DomainUserRelationshipsCreationResult;
import org.springframework.http.ResponseEntity;

import static fr.hesias.gabblerapi.domain.model.DomainAccessStatus.OK;

public class RelationshipsApiDelegateImpl implements RelationshipApiDelegate
{

    private final UserRelationshipsAccessorAdapter userRelationshipsAccessorAdapter;

    public RelationshipsApiDelegateImpl(UserRelationshipsAccessorAdapter userRelationshipsAccessorAdapter)
    {

        this.userRelationshipsAccessorAdapter = userRelationshipsAccessorAdapter;
    }

    @Override
    public ResponseEntity<String> relationshipsCUD(String userUuid, String userRelatedUuid, String relation)
    {

        DomainUserRelationshipsCreation domainUserRelationshipsCreation = new DomainUserRelationshipsCreation(
                userUuid,
                userRelatedUuid,
                relation);
        userRelationshipsAccessorAdapter.relationshipsCUD(new DomainUserRelationshipsCreationResult(OK,
                                                                                                    domainUserRelationshipsCreation));
        return ResponseEntity.ok("OK");
    }

}
