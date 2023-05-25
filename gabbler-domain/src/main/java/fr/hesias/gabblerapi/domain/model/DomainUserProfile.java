package fr.hesias.gabblerapi.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class DomainUserProfile
{

    private String uuid;

    private String username;

    private String firstName;

    private String lastName;

    private String biography;

    private LocalDate birthday;

    private DomainMedia avatar;

    private DomainMedia banner;

    private List<DomainGab> gabs;

    private List<DomainInteraction> interactions;

    private List<DomainUser> followers;

    private List<DomainUser> follows;

    private boolean isPremium;

}
