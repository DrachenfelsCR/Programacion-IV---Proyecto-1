/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.Presentation;

import cinema.logic.Model;
import cinema.logic.Sala;
import cinema.logic.Tanda;
import cinema.logic.TandaDR;
import cinema.logic.TandaJS;
import cinema.logic.User;
import java.util.ArrayList;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/crearTanda")
@PermitAll
public class crearTanda {
     @Context
    HttpServletRequest request;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)  
    public Tanda login(Tanda tanda) {  
           
            
            try {
                
            } catch (Exception ex) {
                throw new NotFoundException();
            }  
         return null;
    }
     @GET
    @Path("recuperarSalas")
    @Produces({MediaType.APPLICATION_JSON})
    public ArrayList<Sala> EnviarSala(){
         ArrayList<Sala> salas = Model.instance().ListaSalas();
         
         return salas; 
    }
    
    @POST
     @Path("registrarTanda")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON) 
    public void RegistrarSala(Tanda tanda){
     TandaDR tandita  = new TandaDR();
         
        
        try{
            
            for (int i = 0; i < 48; i++) {
                
                int numero = i;
                
                String idAsiento= String.valueOf(i);
                
                Model.instance().RegistrarTanda(tanda,idAsiento);
            }
            tandita.setIdTanda(tanda.getIdTanda());
            tandita.setNombrePelicula(tanda.getIdPelicula());
            tandita.setPrecio(tanda.getIdPrecio());
            tandita.setHorita(tanda.getIdHora());
            tandita.setFechita(tanda.getIdFecha());
            tandita.setIdSala(tanda.getIdSala());
            Model.instance().insertoLaTandaDeDrachenfels(tandita);
            
           
        }
        catch (Exception ex) {
                throw new NotFoundException();
            }  
    }
    
    
    @POST    
    @Path("recuperarTanda")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public TandaJS EnviarTanda(String idTanda){
         
         return Model.instance().getTandasJS(idTanda);
    }
    
    
}
