package fr.hesias.gabblerapi.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DomainMedia {

    private int id;

    private String url;

    private String type;

    private LocalDateTime date;

    public DomainMedia() {
        super();
    }


    public DomainMedia(int id, String url, String type, LocalDateTime date) {
        super();
        this.id = id;
        this.url = url;
        this.type = type;
        this.date = date;
    }

    public DomainMedia(String url, String type, LocalDateTime date) {
        super();
        this.url = url;
        this.type = type;
        this.date = date;
    }

    @Override
    public String toString() {
        return "DomainMedia{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                ", date=" + date +
                '}';
    }
}
