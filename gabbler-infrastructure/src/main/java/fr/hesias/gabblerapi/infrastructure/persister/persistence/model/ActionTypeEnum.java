package fr.hesias.gabblerapi.infrastructure.persister.persistence.model;

public enum ActionTypeEnum {
    LIKE("like"), DISLIKE("dislike");

    private final String action;

    ActionTypeEnum(final String name) {
        this.action = name;
    }

    public String getActionType() {
        return action;
    }

}
