package fr.hesias.gabblerapi.infrastructure.persister.persistence.model;

public enum DurationEnum {
    DURATION_1_MOIS("1 mois"),
    DURATION_3_MOIS("3 mois"),
    DURATION_6_MOIS("6 mois"),
    DURATION_12_MOIS("12 mois");

    private final String duration;

    DurationEnum(final String duration) {
        this.duration = duration;
    }
}
