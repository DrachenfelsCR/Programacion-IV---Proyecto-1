/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.Presentation;

/**
 *
 * @author tidae
 */
import cinema.logic.Factura;
import cinema.logic.Model;
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

@Path("/facturar")
@PermitAll
public class Facturar {
    @Context
    HttpServletRequest request;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)    
    public boolean facturar(Factura fact) {    
        
        
       try {
           
            for(int i = 0; i < fact.getButacas().size();i++){
                Model.instance().confirmButacas((String)(fact.getButacas().get(i)) , fact.getTanda());
            }
            for(int i = 0; i < fact.getButacas().size();i++){
                Model.instance().changeButacas((String)(fact.getButacas().get(i)) , fact.getTanda(),fact.getIdCliente());
                Model.instance().insertFactura(fact, i);
            }
            Model.instance().imprimirFact(fact);
            return true;
   
            } catch (Exception ex) {
                throw new NotFoundException();
            }  
    }
    
    @POST
    @Path("recuperarFactura")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)    
    public ArrayList<Factura> getfactura(String factId) {    
        
        return Model.instance().getFacturas(factId);
       
    }
    
    @POST
    @Path("recuperarFacturasId")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)    
    public ArrayList<String> getfacturasId(String idCliente) {    
        
        return Model.instance().getFacturasId(idCliente);
       
    }
    
    @GET
    @Path("recuperarFacturasCant")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)    
    public int getfacturasCant() {    
        
        return Model.instance().getFacturasCant();
       
    }
    
    @POST
    @Path("imprimirPdf")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)    
    public void imprimirTiquetesPdf(ArrayList<Factura> fact) {    
        
        Model.instance().imprimirTiquetes(fact);
       
    }
    
}
