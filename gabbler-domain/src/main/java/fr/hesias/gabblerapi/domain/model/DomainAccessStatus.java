package fr.hesias.gabblerapi.domain.model;

/**
 * Enumération des différents status de retour de la couche domaine
 */
public enum DomainAccessStatus {

    OK(200),
    BAD_REQUEST(400),
    FORBIDDEN(403),
    CONFLICT(409),
    NOT_FOUND(404),
    TIME_OUT(408),
    INTERNAL_ERROR(500),
    UNKNOWN(500),
    NOT_YET_IMPLEMENTED(501),
    SERVICE_UNAVAILABLE(503);

    private final int value;

    /**
     * Constructeur par défaut
     */
    private DomainAccessStatus(final int value) {
        this.value = value;
    }

    /**
     * Méthode de récupération d'un status à partir de son code
     *
     * @param code le code pour déterminer le status
     * @return le status correspondant au code. Si on ne trouve pas de correspondance, on retourne BAD_REQUEST
     */
    public static DomainAccessStatus fromCode(final int code) {
        DomainAccessStatus status = BAD_REQUEST;

        for (final DomainAccessStatus domainAccessStatus : DomainAccessStatus.values()) {
            if (domainAccessStatus.toValue() == code) {
                status = domainAccessStatus;
                break;
            }
        }

        return status;
    }

    public int toValue() {
        return value;
    }

    /**
     * Si c'est ok ou non trouvé
     *
     * @return le résultat du test
     */
    public boolean isOkOrNotFound() {
        return this == OK || this == NOT_FOUND;
    }

    /**
     * Si c'est ok
     *
     * @return le résultat du test
     */
    public boolean isOk() {
        return this == OK;
    }

    /**
     * Permet de savoir si le status n'est pas OK
     *
     * @return true si le status n'est pas OK, false sinon
     */
    public boolean isNotOk() {
        return this != OK;
    }
}
