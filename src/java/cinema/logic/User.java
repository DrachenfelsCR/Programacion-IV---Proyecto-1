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
public class User {
    
    private String id;
    private String clave;
    private String rol;
    private String userName;
    
    public User(String id, String clave, String rol, String userName) {
    this.id = id;
    this.clave = clave;
    this.rol = rol;
    this.userName = userName;
    }
    
    public User() {
    this.id = "";
    this.clave = "";
    this.rol = "";
    this.userName = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String user_name) {
        this.userName = user_name;
    }


    
    
    
    
}
