package fr.hesias.gabblerapi.application.api.service;

import fr.hesias.gabblerapi.application.adapter.RelationshipsAccessorAdapter;
import fr.hesias.gabblerapi.desc.api.server.RelationshipApiDelegate;
import fr.hesias.gabblerapi.desc.api.server.model.RelationUser;
import fr.hesias.gabblerapi.domain.model.DomainUserRelationshipsCreation;
import fr.hesias.gabblerapi.domain.result.DomainUserRelationshipsCreationResult;
import org.springframework.http.ResponseEntity;

import static fr.hesias.gabblerapi.domain.model.DomainAccessStatus.OK;

public class RelationshipsApiDelegateImpl implements RelationshipApiDelegate {

    private final RelationshipsAccessorAdapter relationshipsAccessorAdapter;

    public RelationshipsApiDelegateImpl(RelationshipsAccessorAdapter relationshipsAccessorAdapter) {

        this.relationshipsAccessorAdapter = relationshipsAccessorAdapter;
    }

    @Override
    public ResponseEntity<String> relationshipsCUD(String userUuid, String userRelatedUuid, String relation) {

        DomainUserRelationshipsCreation domainUserRelationshipsCreation = new DomainUserRelationshipsCreation(
                userUuid,
                userRelatedUuid,
                relation);
        relationshipsAccessorAdapter.relationshipsCUD(new DomainUserRelationshipsCreationResult(OK,
                domainUserRelationshipsCreation));
        return ResponseEntity.ok("OK");
    }

    @Override
    public ResponseEntity<RelationUser> getRelationByUserAndUserRelated(String userUuid, String userRelatedUuid) {
        RelationUser response = new RelationUser();
        response.setType(
                relationshipsAccessorAdapter.getRelationByUserAndUserRelated(userUuid, userRelatedUuid));

        return ResponseEntity.ok(response);
    }

}
