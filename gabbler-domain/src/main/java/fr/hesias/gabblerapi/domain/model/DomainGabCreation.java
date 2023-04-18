package fr.hesias.gabblerapi.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class DomainGabCreation {

    private String content;

    private LocalDateTime postDate;

    private int parentId;

    private String userUuid;
}
