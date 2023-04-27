package fr.hesias.gabblerapi.infrastructure.persister.persistence.model;

public enum InteractionTypeEnum
{
    LIKE("like"), DISLIKE("dislike");

    private final String action;

    InteractionTypeEnum(final String name)
    {

        this.action = name;
    }

    static public InteractionTypeEnum getInteractionByName(String name)
    {

        for (InteractionTypeEnum interactionTypeEnum : InteractionTypeEnum.values())
        {
            if (interactionTypeEnum.getActionType().equals(name))
            {
                return interactionTypeEnum;
            }
        }
        return null;
    }

    public String getActionType()
    {

        return action;
    }

}
