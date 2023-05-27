package fr.hesias.gabblerapi.application.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.hesias.gabblerapi.application.adapter.UserInfosAccessorAdapter;
import fr.hesias.gabblerapi.application.security.service.EmailService;
import fr.hesias.gabblerapi.desc.api.server.DocApiDelegate;
import fr.hesias.gabblerapi.desc.api.server.model.EmailDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;

@Slf4j
public class DocApiDelegateImpl implements DocApiDelegate
{

    private final EmailService emailService;

    private final UserInfosAccessorAdapter userInfosAccessorAdapter;

    @Value("${openapi.file.path}")
    private String openapiFilePath;

    public DocApiDelegateImpl(EmailService emailService, UserInfosAccessorAdapter userInfosAccessorAdapter)
    {

        this.emailService = emailService;
        this.userInfosAccessorAdapter = userInfosAccessorAdapter;
    }

    @Override
    public ResponseEntity<String> getJsonDoc()
    {

        File file = new File(openapiFilePath);
        if (file.exists())
        {
            ObjectMapper mapper = new ObjectMapper();
            String json = null;
            try
            {
                json = mapper.writeValueAsString(mapper.readTree(file));
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
            return ResponseEntity.ok(json);
        }
        else
        {
            return ResponseEntity.ok("CAN'T FIND FILE");
        }
    }

    @Override
    public ResponseEntity<String> sendMail(EmailDetails emailDetails)
    {

        String returned = emailService.sendMail(emailDetails);

        return ResponseEntity.ok(returned);
    }

    @Override
    public ResponseEntity<String> confirmMail(String uuid)
    {

        userInfosAccessorAdapter.confirmEmailByUserUuid(uuid);
        return ResponseEntity.ok("Email bien confirmé");
    }

}
