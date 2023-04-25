package fr.hesias.gabblerapi.application.api.mapper;

import fr.hesias.gabblerapi.desc.api.server.model.SearchContent;
import fr.hesias.gabblerapi.domain.result.DomainSearchResult;

public class SearchApiMapper
{

    private final GabApiMapper gabApiMapper;

    private final UserApiMapper userApiMapper;

    public SearchApiMapper(final GabApiMapper gabApiMapper,
                           final UserApiMapper userApiMapper)
    {

        super();
        this.gabApiMapper = gabApiMapper;
        this.userApiMapper = userApiMapper;
    }


    public SearchContent toDomainSearchResultToSearchContent(DomainSearchResult domainSearchResult)
    {

        SearchContent searchContent = new SearchContent();
        searchContent.setGabs(this.gabApiMapper.toDomainGabsResultToGabsList(domainSearchResult.getDomainGabsResult()));
        searchContent.setUsers(this.userApiMapper.toDomainUsersResultToUsersList(domainSearchResult.getDomainUsersResult()));
        return searchContent;
    }

}
