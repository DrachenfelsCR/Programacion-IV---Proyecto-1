/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.logic;

import java.sql.Date;

/**
 *
 * @author tidae
 */
public class Tanda {
    
    
    private String idTanda;
    private String idPelicula ;
    private String idSala;
    private int idPrecio;
    private String idHora;
    private String idFecha ;
    private boolean estado;
    private String idCliente;
    private String idAsiento;

    public Tanda(String idTanda, String idPelicula, String idSala, int idPrecio, String idHora, String idFecha, boolean estado, String idCliente, String idAsiento) {
        this.idTanda = idTanda;
        this.idPelicula = idPelicula;
        this.idSala = idSala;
        this.idPrecio = idPrecio;
        this.idHora = idHora;
        this.idFecha = idFecha;
        this.estado = estado;
        this.idCliente = idCliente;
        this.idAsiento = idAsiento;
    }
    public Tanda() {
        this.idTanda = "";
        this.idPelicula = "";
        this.idSala = "";
        this.idPrecio = 0;
        this.idHora = "";
        this.idFecha = "";
        this.estado = true;
        this.idCliente = "";
        this.idAsiento = "";
    }

    public String getIdTanda() {
        return idTanda;
    }

    public String getIdPelicula() {
        return idPelicula;
    }

    public String getIdSala() {
        return idSala;
    }

    public int getIdPrecio() {
        return idPrecio;
    }

    public String getIdHora() {
        return idHora;
    }

    public String getIdFecha() {
        return idFecha;
    }

    public boolean isEstado() {
        return estado;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public String getIdAsiento() {
        return idAsiento;
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

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public void setIdAsiento(String idAsiento) {
        this.idAsiento = idAsiento;
    }
    

    
    
    
}
