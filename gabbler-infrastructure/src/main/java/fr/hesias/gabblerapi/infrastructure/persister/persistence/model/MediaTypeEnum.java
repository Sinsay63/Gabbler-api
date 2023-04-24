package fr.hesias.gabblerapi.infrastructure.persister.persistence.model;

public enum MediaTypeEnum {

    AVATAR("avatar"), BANNER("banner"), POST("post");

    private final String type;

    MediaTypeEnum(final String name) {
        this.type = name;
    }

    public String getMediaType() {
        return type;
    }
}
