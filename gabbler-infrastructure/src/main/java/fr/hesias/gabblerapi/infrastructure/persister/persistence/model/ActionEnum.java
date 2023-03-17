package fr.hesias.gabblerapi.infrastructure.persister.persistence.model;

public enum ActionEnum {
    ACTION_LIKE("like"), ACTION_DISLIKE("dislike");

    private final String action;

    ActionEnum(final String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}
