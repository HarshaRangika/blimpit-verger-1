package example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.awt.*;

@Path("/hello")
public class Hello {
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getMessage(){
        return "Hello World";
    }
}
