/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.logic;

import java.util.ArrayList;

/**
 *
 * @author tidae
 */
public class TandaJS {
    private String idTanda;
    private String idPelicula ;
    private String idSala;
    private int idPrecio;
    private String idHora;
    private String idFecha ;


    private ArrayList<Asiento> asientos;

    public TandaJS(String idTanda, String idPelicula, String idSala, int idPrecio, String idHora, String idFecha, ArrayList asientos) {
        this.idTanda = idTanda;
        this.idPelicula = idPelicula;
        this.idSala = idSala;
        this.idPrecio = idPrecio;
        this.idHora = idHora;
        this.idFecha = idFecha;


        this.asientos = asientos;
    }
    public TandaJS() {
        this.idTanda = "";
        this.idPelicula = "";
        this.idSala = "";
        this.idPrecio = 0;
        this.idHora = "";
        this.idFecha = "";
        this.asientos = new ArrayList();
    }

    public String getIdTanda() {
        return idTanda;
    }

    public String getPelicula() {
        return idPelicula;
    }

    public String getIdSala() {
        return idSala;
    }

    public int getPrecio() {
        return idPrecio;
    }

    public String getHora() {
        return idHora;
    }

    public String getFecha() {
        return idFecha;
    }

    public ArrayList getAsientos() {
        return asientos;
    }

    public void setIdTanda(String idTanda) {
        this.idTanda = idTanda;
    }

    public void setIdPelicula(String idPelicula) {
        this.idPelicula = idPelicula;
    }

    public void setIdSala(String idSala) {
        this.idSala = idSala;
    }

    public void setIdPrecio(int idPrecio) {
        this.idPrecio = idPrecio;
    }

    public void setIdHora(String idHora) {
        this.idHora = idHora;
    }

    public void setIdFecha(String idFecha) {
        this.idFecha = idFecha;
    }

    public void setList(ArrayList asientos) {
        this.asientos = asientos;
    }
    
    public void setAsientos(String idAsientos, boolean estado) {
        Asiento aux = new Asiento(idAsientos, estado);
        asientos.add(aux);
    }
}
