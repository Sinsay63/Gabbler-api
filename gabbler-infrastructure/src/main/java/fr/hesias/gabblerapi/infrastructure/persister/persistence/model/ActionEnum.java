package fr.hesias.gabblerapi.infrastructure.persister.persistence.model;

public enum ActionEnum {
    LIKE("like"), DISLIKE("dislike");

    private final String action;

    ActionEnum(final String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

}
