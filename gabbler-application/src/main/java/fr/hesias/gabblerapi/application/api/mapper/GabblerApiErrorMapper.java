package fr.hesias.gabblerapi.application.api.mapper;

import fr.hesias.gabblerapi.desc.api.server.model.AccessError;
import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;
import fr.hesias.gabblerapi.domain.result.DomainResultable;
import org.springframework.http.HttpStatus;

import static fr.hesias.gabblerapi.domain.model.DomainAccessStatus.*;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class GabblerApiErrorMapper
{


    /**
     * Permet de convertir le résultat d'une opération du domaine dans un modèle de l'API.
     *
     * @param domainResultable le résultat provenant du domaine
     * @param path             chemin de l'appel qui a provoqué l'erreur
     * @return le modèle de l'API associé au résultat du domaine
     */
    public AccessError toAccessError(final DomainResultable domainResultable, final String path)
    {

        String error = null;
        String message = null;

        if (domainResultable != null)
        {
            message = domainResultable.getMessage();

            if (domainResultable.getDomainAccessStatus() != null)
            {
                error = domainResultable.getDomainAccessStatus().name();
            }
        }

        return new AccessError().error(error).message(message).path(path);
    }

    /**
     * Permet de retourner un code http à partir d'un code d'erreur du domaine
     *
     * @param domainResultable résultat du domaine
     * @return le code http
     */
    public HttpStatus toHttpStatus(final DomainResultable domainResultable)
    {

        final DomainAccessStatus domainAccessStatus = domainResultable != null
                                                      ? domainResultable.getDomainAccessStatus()
                                                      : null;

        HttpStatus httpStatus = INTERNAL_SERVER_ERROR;

        if (domainAccessStatus != null)
        {
            if (domainAccessStatus == DomainAccessStatus.OK)
            {
                httpStatus = HttpStatus.OK;
            }
            else if (domainAccessStatus == DomainAccessStatus.NOT_FOUND)
            {
                httpStatus = HttpStatus.NOT_FOUND;
            }
            else if (domainAccessStatus == DomainAccessStatus.BAD_REQUEST)
            {
                httpStatus = HttpStatus.BAD_REQUEST;
            }
            else if (domainAccessStatus == SERVICE_UNAVAILABLE)
            {
                httpStatus = HttpStatus.SERVICE_UNAVAILABLE;
            }
            else if (domainAccessStatus == DomainAccessStatus.CONFLICT)
            {
                httpStatus = HttpStatus.CONFLICT;
            }
            else if (domainAccessStatus == TIME_OUT)
            {
                httpStatus = HttpStatus.REQUEST_TIMEOUT;
            }
            else if (domainAccessStatus == FORBIDDEN)
            {
                httpStatus = HttpStatus.FORBIDDEN;
            }
            else if (domainAccessStatus == NOT_YET_IMPLEMENTED)
            {
                httpStatus = HttpStatus.NOT_IMPLEMENTED;
            }

        }

        return httpStatus;
    }

}
