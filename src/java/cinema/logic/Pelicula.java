/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.logic;

/**
 *
 * @author tidae
 */
public class Pelicula {
    
    private String movieName;
    private Boolean cartelera;

    public Pelicula(String movieName, Boolean cartelera) {
        this.movieName = movieName;
        this.cartelera = cartelera;
    }

    public Pelicula() {
        this.movieName = "";
        this.cartelera = false;
    }
    
    

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Boolean getCartelera() {
        return cartelera;
    }

    public void setCartelera(Boolean cartelera) {
        this.cartelera = cartelera;
    }
    
    
    
    
}
