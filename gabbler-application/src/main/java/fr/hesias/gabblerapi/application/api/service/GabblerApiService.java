package fr.hesias.gabblerapi.application.api.service;

import fr.hesias.gabblerapi.domain.result.DomainResultable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
public class GabblerApiService {


    public GabblerApiService() {

        super();

    }

    /**
     * Permet de construire une réponse de l'API en fonction du résultat du domaine.
     *
     * @param result           l'objet à envoyer dans le body de la réponse
     * @param domainResultable un résultat du domaine
     * @param <T>              le type d'objet retourner dans le body
     * @return une ResponseEntity avec le bon code retour et le bon body en fonction du résultat du domaine
     */
    public <T> ResponseEntity<T> getResponse(final T result, final DomainResultable domainResultable) {

        if (domainResultable != null && !domainResultable.isOk()) {
            throw new GabblerApiException(domainResultable);
        }
        else if (domainResultable == null) {
            throw new GabblerApiException();
        }
        else {
            return ResponseEntity.ok(result);
        }
    }

    /**
     * Permet de construire une réponse de l'API en fonction du résultat du domaine.
     *
     * @param domainResultable un résultat du domaine
     * @return une ResponseEntity avec le bon code retour et le bon body en fonction du résultat du domaine
     */
    public ResponseEntity<Void> getResponseNotContent(final DomainResultable domainResultable) {

        if (domainResultable != null && !domainResultable.isOk()) {
            throw new GabblerApiException(domainResultable);
        }
        else if (domainResultable == null) {
            throw new GabblerApiException();
        }
        else {
            return ResponseEntity.noContent().build();
        }
    }

    /**
     * L'URI de la requête en cours.
     *
     * @return l'URI de la requête courante ou vide si on n'arrive pas à récupérer la requête
     */
    public String getUri() {

        final ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        return servletRequestAttributes != null ? servletRequestAttributes.getRequest().getRequestURI() : "";
    }

}
