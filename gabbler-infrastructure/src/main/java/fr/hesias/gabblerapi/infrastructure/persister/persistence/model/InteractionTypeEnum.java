package fr.hesias.gabblerapi.infrastructure.persister.persistence.model;

public enum InteractionTypeEnum {
    BLOCKED("blocked"), AUTHORIZED("authorized");

    private final String type;

    InteractionTypeEnum(final String name) {
        this.type = name;
    }

    public String getInteractionType() {
        return type;
    }
}