package fr.hesias.gabblerapi.infrastructure.persister.persistence.model;

public enum RelationshipTypeEnum
{
    BLOCKED("blocked"), AUTHORIZED("authorized"), FOLLOWED("followed");

    private final String type;

    RelationshipTypeEnum(final String name)
    {

        this.type = name;
    }

    public String getInteractionType()
    {

        return type;
    }
}