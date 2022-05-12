/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.Presentation;

import cinema.data.PeliculaDao;
import cinema.data.TandaDRDAO;
import cinema.logic.Model;
import cinema.logic.Pelicula;
import cinema.logic.TandaDR;
import java.util.ArrayList;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author David
 */
@Path("/RecuperarPeliculas")
@PermitAll
public class RecuperarPeliculas {

    @Context
     HttpServletRequest request;

    /**
     * Creates a new instance of RecuperarPeliculas
     */
    public RecuperarPeliculas() {
    }

    /**
     * Retrieves representation of an instance of cinema.Presentation.RecuperarPeliculas
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("movies")
    public ArrayList<Pelicula> getJSON() {
        ArrayList<Pelicula> arrPelis = Model.instance().recuperarPelicula();
        return arrPelis;    
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("tandas")
    public ArrayList<TandaDR> getJSON2() {
        ArrayList<TandaDR> arrPelis = Model.instance().recuperarTandas();
        return arrPelis;    
    }
    /**
     * PUT method for updating or creating an instance of RecuperarPeliculas
     * @param content representation for the resource
     */
}
