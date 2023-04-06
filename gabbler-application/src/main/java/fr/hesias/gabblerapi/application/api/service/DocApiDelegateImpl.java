package fr.hesias.gabblerapi.application.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.hesias.gabblerapi.desc.api.server.DocApiDelegate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;

@Slf4j
public class DocApiDelegateImpl implements DocApiDelegate
{

    @Value("${openapi.file.path}")
    private String openapiFilePath;

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

}
