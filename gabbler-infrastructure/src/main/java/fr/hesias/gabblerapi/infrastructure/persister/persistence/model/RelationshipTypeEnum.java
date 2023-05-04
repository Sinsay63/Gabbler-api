package fr.hesias.gabblerapi.infrastructure.persister.persistence.model;

public enum RelationshipTypeEnum
{
    BLOCKED("blocked"), AUTHORIZED("authorized"), FOLLOWED("followed");

    private final String type;

    RelationshipTypeEnum(final String name)
    {

        this.type = name;
    }

    static public RelationshipTypeEnum getInteractionByName(String name)
    {

        for (RelationshipTypeEnum interactionTypeEnum : RelationshipTypeEnum.values())
        {
            if (interactionTypeEnum.getInteractionType().equals(name))
            {
                return interactionTypeEnum;
            }
        }
        return null;
    }

    public String getInteractionType()
    {

        return type;
    }
}