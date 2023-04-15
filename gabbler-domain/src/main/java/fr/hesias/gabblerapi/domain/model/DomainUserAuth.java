package fr.hesias.gabblerapi.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DomainUserAuth
{

    private DomainUser user;

    private String password;

    public DomainUserAuth()
    {

        super();

    }

    public DomainUserAuth(DomainUser user, String password)
    {

        super();
        this.user = user;
        this.password = password;
    }

}
