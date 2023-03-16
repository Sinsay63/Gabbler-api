package fr.hesias.gabblerapi.infrastructure.persister.persistence.model;

public enum Type {
    blocked("blocked"), authorized("authorized");

    private final String name;

    Type(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}