package com.makridin.labfolder;

import com.makridin.labfolder.rest.LevenshtainRestController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("/labfolder")
public class JerseyConfiguration extends ResourceConfig
{
    @PostConstruct
    public void setUp()
    {
        register(LevenshtainRestController.class);
    }
}
