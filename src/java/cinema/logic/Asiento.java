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
public class Asiento {
    private String id;
    private boolean estado;

    public Asiento() {
        this.id = "";
        this.estado = true;
    }

    public Asiento(String id, boolean estado) {
        this.id = id;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
    
    
}
