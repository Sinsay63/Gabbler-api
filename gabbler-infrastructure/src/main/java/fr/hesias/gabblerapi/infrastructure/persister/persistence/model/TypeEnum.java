package fr.hesias.gabblerapi.infrastructure.persister.persistence.model;

public enum TypeEnum {
    TYPE_BLOCKED("blocked"), TYPE_AUTHORIZED("authorized");

    private final String type;

    TypeEnum(final String name) {
        this.type = name;
    }

    public String getType() {
        return type;
    }
}