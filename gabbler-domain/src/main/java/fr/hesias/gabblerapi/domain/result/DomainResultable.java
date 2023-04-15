package fr.hesias.gabblerapi.domain.result;

import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;

import static fr.hesias.gabblerapi.domain.model.DomainAccessStatus.OK;

/**
 * Classe représentant le retour des ports secondaires vers le domaine
 */
public class DomainResultable
{

    private DomainAccessStatus domainAccessStatus;

    private String message;

    /**
     * Constructeur
     *
     * @param domainAccessStatus code résultat de l'opération
     */
    public DomainResultable(final DomainAccessStatus domainAccessStatus)
    {

        this(domainAccessStatus, null);
    }

    /**
     * Constructeur
     *
     * @param domainAccessStatus code résultat de l'opération
     * @param message            message d'erreur éventuel
     */
    protected DomainResultable(final DomainAccessStatus domainAccessStatus, final String message)
    {

        super();

        this.domainAccessStatus = domainAccessStatus;
        this.message = message;
    }

    /**
     * Permet de mettre à jour le message et le code de résultat de l'opération
     * par rapport à une autre opération.
     *
     * @param domainResultable l'autre résultat
     * @return le DomainHAPI2O2SResult à jour
     */
    public DomainResultable updateFromAnotherResult(final DomainResultable domainResultable)
    {

        domainAccessStatus = domainResultable.getDomainAccessStatus();
        message = domainResultable.getMessage();

        return this;
    }

    /**
     * Permet de mettre à jour le message de résultat de l'opération.
     *
     * @param message le nouveau message
     */
    public DomainResultable updateMessage(final String message)
    {

        this.message = message;

        return this;
    }

    /**
     * Permet de mettre à jour le status
     *
     * @param accessStatus le nouveau code
     */
    public DomainResultable updateAccessStatus(final DomainAccessStatus accessStatus)
    {

        domainAccessStatus = accessStatus;

        return this;
    }

    /**
     * Permet de savoir si le résultat est OK et permet de savoir si on peut continuer dans notre algorithme.
     *
     * @return si le résultat est OK
     */
    public boolean isOk()
    {

        return domainAccessStatus == OK;
    }

    /**
     * Méthode d'accès au status
     *
     * @return le status
     */
    public DomainAccessStatus getDomainAccessStatus()
    {

        return domainAccessStatus;
    }

    /**
     * Méthode d'accès au message
     *
     * @return le message
     */
    public String getMessage()
    {

        return message;
    }

    @Override
    public String toString()
    {

        return "DomainResultable{" +
                "accessStatus=" + domainAccessStatus +
                ", message='" + message + '\'' +
                '}';
    }

}
