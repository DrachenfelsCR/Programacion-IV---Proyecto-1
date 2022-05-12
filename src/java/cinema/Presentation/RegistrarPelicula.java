/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.Presentation;

import cinema.logic.Model;
import cinema.logic.Pelicula;
import cinema.logic.User;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataParam;


/**
 * REST Web Service
 *
 * @author David
 */
@Path("/peliculas")
@PermitAll
public class RegistrarPelicula {
    @Context
    HttpServletRequest request;
     String location="C:/Users/David/Documents/NetBeansProjects/Proyecto2Progra4/Proyecto2-Progra4/web/images";
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA) 
    @Path("{movieName}/imagen")
    public void addImage(@PathParam("movieName") String movieName, @FormDataParam("imagen") InputStream imagenStream) {  
        try{
                int read = 0;
                byte[] bytes = new byte[1024];

                OutputStream out = new FileOutputStream(new File(location + movieName));
                while ((read = imagenStream.read(bytes)) != -1){out.write(bytes, 0, read);}
                out.flush();
                out.close();
            } catch (Exception ex) {
                throw new NotAcceptableException(); 
            }
    }
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)  
    @Path("{movieName}/register")
    public void RegistrarPelicula(Pelicula peli) {  
            Pelicula currentPeli=null;
            
            try {
                
                   Model.instance().RegistarPelicula(peli);
   
            } catch (Exception ex) {
                throw new NotFoundException();
            }  

    }
    
    @GET
    @Path("{movieName}/imagen")
    @Produces("image/png")
    public Response getImge(@PathParam("movieName") String movieName) throws IOException {
        File file = new File(location+movieName);
        Response.ResponseBuilder response = Response.ok((Object) file);
        return response.build();
    } 
    
    
}
