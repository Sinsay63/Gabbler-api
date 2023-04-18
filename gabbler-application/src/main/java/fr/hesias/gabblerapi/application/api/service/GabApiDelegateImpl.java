package fr.hesias.gabblerapi.application.api.service;


import fr.hesias.gabblerapi.application.adapter.GabInfosAccessorAdapter;
import fr.hesias.gabblerapi.application.api.mapper.GabApiMapper;
import fr.hesias.gabblerapi.desc.api.server.GabApiDelegate;
import fr.hesias.gabblerapi.desc.api.server.model.Gab;
import fr.hesias.gabblerapi.desc.api.server.model.GabCreation;
import fr.hesias.gabblerapi.desc.api.server.model.Gabs;
import fr.hesias.gabblerapi.domain.result.DomainGabResult;
import fr.hesias.gabblerapi.domain.result.DomainGabsResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public class GabApiDelegateImpl implements GabApiDelegate {

    private final GabInfosAccessorAdapter gabInfosAccessorAdapter;

    private final GabblerApiService gabblerApiService;

    private final GabApiMapper gabApiMapper;

    public GabApiDelegateImpl(final GabInfosAccessorAdapter gabInfosAccessorAdapter, final GabblerApiService gabblerApiService, final GabApiMapper gabApiMapper) {

        super();
        this.gabInfosAccessorAdapter = gabInfosAccessorAdapter;
        this.gabblerApiService = gabblerApiService;
        this.gabApiMapper = gabApiMapper;
    }


    @Override
    public ResponseEntity<Gabs> getGabs() {

        final DomainGabsResult domainGabsResult = gabInfosAccessorAdapter.getGabs();

        return gabblerApiService.getResponse(gabApiMapper.toGabs(domainGabsResult), domainGabsResult);
    }

    @Override
    public ResponseEntity<Gab> getGabById(Integer id) {

        final DomainGabResult domainGabResult = gabInfosAccessorAdapter.getGabById(id);

        return gabblerApiService.getResponse(gabApiMapper.toGab(domainGabResult.getGab()), domainGabResult);
    }

    @Override
    public ResponseEntity<Gabs> getCommentsByGabId(Integer parentId) {

        final DomainGabsResult domainGabsResult = gabInfosAccessorAdapter.getCommentsByParentGabId(parentId);

        return gabblerApiService.getResponse(gabApiMapper.toGabs(domainGabsResult), domainGabsResult);
    }

    @Override
    public ResponseEntity<String> createGab(String uuidUser, @RequestBody GabCreation gab) {
        if (gabInfosAccessorAdapter.createGab(gabApiMapper.toGabCreationToDomainGabCreationResult(gab)).isOk()) {
            return ResponseEntity.ok().body("Gab créé avec succès pour l'utilisateur " + uuidUser);
        }
        return ResponseEntity.badRequest().build();
    }
}
