package fr.hesias.gabblerapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DomainEditUserProfile
{

    private String editType;

    private String value;

    private String userUuid;

}
