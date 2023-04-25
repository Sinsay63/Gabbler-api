package fr.hesias.gabblerapi.application.api.service;

import fr.hesias.gabblerapi.application.adapter.GabInfosAccessorAdapter;
import fr.hesias.gabblerapi.application.api.mapper.SearchApiMapper;
import fr.hesias.gabblerapi.desc.api.server.SearchApiDelegate;
import fr.hesias.gabblerapi.desc.api.server.model.SearchContent;
import fr.hesias.gabblerapi.domain.result.DomainSearchResult;
import org.springframework.http.ResponseEntity;

public class SearchApiDelegateImpl implements SearchApiDelegate
{

    private final GabblerApiService gabblerApiService;

    private final GabInfosAccessorAdapter gabInfosAccessorAdapter;

    private final SearchApiMapper searchApiMapper;


    public SearchApiDelegateImpl(final GabblerApiService gabblerApiService,
                                 final GabInfosAccessorAdapter gabInfosAccessorAdapter,
                                 final SearchApiMapper searchApiMapper)
    {

        super();
        this.gabblerApiService = gabblerApiService;
        this.gabInfosAccessorAdapter = gabInfosAccessorAdapter;
        this.searchApiMapper = searchApiMapper;
    }

    /**
     * @param content le contenu de la recherche
     * @return l'objet avec les résultats de la recherche
     */
    @Override
    public ResponseEntity<SearchContent> searchUser(String content)
    {

        DomainSearchResult domainSearchResult = this.gabInfosAccessorAdapter.getResultForSearch(content);
        return this.gabblerApiService.getResponse(this.searchApiMapper.toDomainSearchResultToSearchContent(
                                                          domainSearchResult),
                                                  domainSearchResult);
    }

}
