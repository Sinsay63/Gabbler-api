package fr.hesias.gabblerapi.application.api.service;


import fr.hesias.gabblerapi.application.api.mapper.GabblerApiErrorMapper;
import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;
import fr.hesias.gabblerapi.domain.result.DomainResultable;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Handler permettant de gérer les exceptions de l'API.
 *
 * @author bdupont
 */
@ControllerAdvice
public class GabblerApiErrorHandler extends ResponseEntityExceptionHandler {


    private final GabblerApiErrorMapper gabblerApiErrorMapper;

    /**
     * Constructeur
     *
     * @param gabblerApiErrorMapper pour mapper l'erreur du domaine en erreur de l'API
     */
    public GabblerApiErrorHandler(final GabblerApiErrorMapper gabblerApiErrorMapper) {

        super();


        this.gabblerApiErrorMapper = gabblerApiErrorMapper;
    }

    /**
     * Handler pour les exceptions de l'API.
     *
     * @param ex      l'exception captée
     * @param request la requête associée
     * @return une ResponseEntity avec l'erreur
     */
    @ExceptionHandler(value = {GabblerApiException.class})
    protected void handleApiError(final GabblerApiException ex, final WebRequest request) {

        final DomainResultable domainResultable = ex.getDomainResultable();

//        return handleExceptionInternal(ex, gabblerApiErrorMapper.toAccessError(domainResultable, ((ServletWebRequest) request).getRequest().getRequestURI()), new HttpHeaders(), gabblerApiErrorMapper.toHttpStatus(domainResultable), request);
    }

    /**
     * Handler pour les exceptions autour des paramètres lors des appels.
     *
     * @param ex      l'exception captée
     * @param request la requête associée
     * @return une ResponseEntity avec l'erreur
     */
    @ExceptionHandler(value = {HttpMessageConversionException.class})
    protected void handleParametersError(final HttpMessageConversionException ex, final WebRequest request) {

        final DomainResultable domainResultable = new DomainResultable(DomainAccessStatus.BAD_REQUEST);
        domainResultable.updateMessage(ex.getCause().getCause().getMessage());

//        return handleExceptionInternal(ex, gabblerApiErrorMapper.toAccessError(domainResultable, ((ServletWebRequest) request).getRequest().getRequestURI()), new HttpHeaders(), gabblerApiErrorMapper.toHttpStatus(domainResultable), request);
    }

}
