package fr.hesias.gabblerapi.domain.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DomainGabCreation
{

    private String content;

    private int parentId;

    private String userUuid;

}
