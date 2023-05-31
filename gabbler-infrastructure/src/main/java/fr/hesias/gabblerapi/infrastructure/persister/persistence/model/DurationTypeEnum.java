package fr.hesias.gabblerapi.infrastructure.persister.persistence.model;

public enum DurationTypeEnum
{
    UN_MOIS("1 mois"),
    TROIS_MOIS("3 mois"),
    SIX_MOIS("6 mois"),
    DOUZE_MOIS("12 mois");

    private final String duration;

    DurationTypeEnum(final String name)
    {

        this.duration = name;
    }

    public String getDurationType()
    {

        return duration;
    }
}
