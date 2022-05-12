
package cinema.Presentation;

/**
 *
 * @author tidae
 */
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

@ApplicationPath("api")
public class RegistroApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {

        HashSet<Class<?>> classes = new HashSet<>();
        classes.add(MultiPartFeature.class);
        classes.add(Login.class);
        classes.add(RegisterSala.class);
        classes.add(crearTanda.class);
        classes.add(Register.class);    
        classes.add(RegistrarPelicula.class);    
        classes.add(RecuperarPeliculas.class); 
        classes.add(Facturar.class);       
        return classes;
    }   
}