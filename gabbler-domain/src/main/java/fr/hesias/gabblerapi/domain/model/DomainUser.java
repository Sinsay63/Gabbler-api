package fr.hesias.gabblerapi.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DomainUser
{

    private String uuid;

    private String email;

    private String username;

    private String firstName;

    private String lastName;

    private DomainMedia avatar;

    private DomainMedia banner;

    private String roles;

    private boolean isPremium;


    public DomainUser()
    {

        super();
    }

    public DomainUser(final String email,
                      final String username,
                      final String firstName,
                      final String lastName,
                      final String roles)
    {

        super();
        this.email = email;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
    }

    public DomainUser(final String uuid,
                      final String email,
                      final String username,
                      final String firstName,
                      final String lastName,
                      final String roles)
    {

        super();
        this.uuid = uuid;
        this.email = email;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
    }

    @Override
    public String toString()
    {

        return "DomainUser{" +
                "uuid='" + uuid + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", avatar=" + avatar + '\'' +
                ", banner=" + banner + '\'' +
                ", roles='" + roles + '\'' +
                ", isPremium='" + isPremium + '\'' +
                '}';
    }

}
