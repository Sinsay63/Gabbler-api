package fr.hesias.gabblerapi.application.security.service;

import fr.hesias.gabblerapi.desc.api.server.model.EmailDetails;

// Interface
public interface EmailService
{

    String sendMail(EmailDetails details);


}
