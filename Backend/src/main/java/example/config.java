package example;


import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("test")
public class config extends ResourceConfig {

    public config() {

        packages("org.blimit.external.verger.inventory");

    }
}
