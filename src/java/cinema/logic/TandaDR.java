/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.logic;

/**
 *
 * @author beto
 */
public class TandaDR {
    String idTanda;
    String idSala;
    String nombrePelicula;
    int precio;
    String fechita;
    String horita;

    public TandaDR(String idTanda, String idSala, String nombrePelicula, int precio, String fechita, String horita) {
        this.idTanda = idTanda;
        this.idSala = idSala;
        this.nombrePelicula = nombrePelicula;
        this.precio = precio;
        this.fechita = fechita;
        this.horita = horita;
    }
    public TandaDR() {
        this.idTanda = "";
        this.idSala = "";
        this.nombrePelicula = "";
        this.precio = 0;
        this.fechita = "";
        this.horita = "";
    }

    public String getIdTanda() {
        return idTanda;
    }

    public String getIdSala() {
        return idSala;
    }

    public String getNombrePelicula() {
        return nombrePelicula;
    }

    public int getPrecio() {
        return precio;
    }

    public String getFechita() {
        return fechita;
    }

    public String getHorita() {
        return horita;
    }

    public void setIdTanda(String idTanda) {
        this.idTanda = idTanda;
    }

    public void setIdSala(String idSala) {
        this.idSala = idSala;
    }

    public void setNombrePelicula(String nombrePelicula) {
        this.nombrePelicula = nombrePelicula;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setFechita(String fechita) {
        this.fechita = fechita;
    }

    public void setHorita(String horita) {
        this.horita = horita;
    }
    

   
    
    
    
    
}
