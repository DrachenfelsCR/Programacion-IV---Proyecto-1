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
public class Factura {
    
    private String id;
    private String tanda;
    private String nameCliente;
    private String idCliente;
    private ArrayList butacas;

    public Factura(String id, String tanda, String nameCliente, String idCliente, ArrayList butacas) {
        this.id = id;
        this.tanda = tanda;
        this.nameCliente = nameCliente;
        this.idCliente = idCliente;
        this.butacas = butacas;
    }

    public ArrayList getButacas() {
        return butacas;
    }

    public void setButacas(ArrayList butacas) {
        this.butacas = butacas;
    }
    
    public Factura() {
        this.id = "";
        this.tanda = "";
        this.nameCliente = "";
        this.idCliente = "";
        this.butacas = new ArrayList();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTanda() {
        return tanda;
    }

    public void setTanda(String tanda) {
        this.tanda = tanda;
    }

    public String getNameCliente() {
        return nameCliente;
    }

    public void setNameCliente(String nameCliente) {
        this.nameCliente = nameCliente;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }
    
    public void setIdButaca(String idButaca){
        butacas.add(idButaca);
    }
    
}
