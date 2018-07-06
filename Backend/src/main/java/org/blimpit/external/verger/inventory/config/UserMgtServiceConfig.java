package org.blimpit.external.verger.inventory.config;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class UserMgtServiceConfig extends ResourceConfig {
    public UserMgtServiceConfig() {

        packages("org.blimpit.external.verger.inventory");
        register(MultiPartFeature.class);

    }
}
