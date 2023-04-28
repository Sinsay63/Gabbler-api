package fr.hesias.gabblerapi.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DomainInteraction
{

    private String interactionType;

    private LocalDateTime actionDate;

    private String userUuid;

    private int gabId;


    public DomainInteraction()
    {

        super();
    }

    public DomainInteraction(String interactionType, LocalDateTime actionDate, String userUuid, int gabId)
    {

        this.interactionType = interactionType;
        this.actionDate = actionDate;
        this.userUuid = userUuid;
        this.gabId = gabId;
    }

}
