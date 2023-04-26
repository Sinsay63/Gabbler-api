package fr.hesias.gabblerapi.infrastructure.persister.persistence.model;

public enum InteractionTypeEnum
{
    LIKE("like"), DISLIKE("dislike");

    private final String action;

    InteractionTypeEnum(final String name)
    {

        this.action = name;
    }

    public String getActionType()
    {

        return action;
    }

}
