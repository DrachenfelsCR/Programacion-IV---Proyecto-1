/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.data;

/**
 *
 * @author tidae
 */
import cinema.logic.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.util.Set;
 
/**
 *
 * @author beto
 */
public class UserDao {

         Connection con;
         databaseConnection cn = new databaseConnection();
         PreparedStatement ps;
         
  
   
    
        public int validar(User u,String a) {
        String sql = "Select * from Usuario where id=? and clave=?";
        String x="";
          int r2=0;
        ResultSet rs;
        try {
            con = cn.getConnections();
            ps= con.prepareStatement(sql);
            ps.setString(1,u.getId());
            ps.setString(2,a);
            rs= ps.executeQuery();
            while(rs.next()){
                r2=r2+1;
                u.setId(rs.getString("id"));
                x=rs.getString("clave");
                u.setRol(rs.getString("rol"));
                u.setUserName(rs.getString("user_name"));
                
            }
            if(r2==1){
                return 1;
            }
            else {
                return 0;
            }
            
        }catch(Exception e){
            return 0;
        }
        
        
        
    }
    
     public int validarUsuario(User u) {
        String sql = "Select * from Usuario where id=?";
        
          int r2=0;
        ResultSet rs;
        try {
            con = cn.getConnections();
            ps= con.prepareStatement(sql);
            ps.setString(1,u.getId());
            rs= ps.executeQuery();
            while(rs.next()){
                r2=r2+1;
                u.setId(rs.getString("id"));
               
               
                
            }
            if(r2==1){
                return 1;
            }
            else {
                return 0;
            }
            
        }catch(Exception e){
            return 0;
        }
        
        
        
    }
    
        
        
       

   public User read(User u,String a) {
        String sql = "Select * from Usuario where id=? and clave=?";
        String x="";
        ResultSet rs;
        int r2=0;
        try {
            con = cn.getConnections();
            ps= con.prepareStatement(sql);
            ps.setString(1,u.getId());
            ps.setString(2,a);
            rs= ps.executeQuery();
            if(rs.next()){
                 return from(rs);      
            }
            else {
                return null;
            }
            
        }catch(Exception e){
           
        }
         return null;
        
        
        
    }
   

   public ArrayList<User> findAll() {
        ArrayList<User> r= new ArrayList(); 
        String sql = "Select * from Usuario";
        String x="";
        ResultSet rs;
        int r2=0;
        try {
            con = cn.getConnections();
            ps= con.prepareStatement(sql);
            rs= ps.executeQuery();
            while(rs.next()){
                 r.add(from(rs));
            }
           
            
        }catch(SQLException ex){
           
        }
         return r;
        
        
        
    }


public User from (ResultSet rs){
        try {
            User r= new User();
            r.setId(rs.getString("id"));
            r.setRol(rs.getString("rol"));
            r.setUserName(rs.getString("user_name"));
            return r;
        } catch (SQLException ex) {
            return null;
        }
    }



 public  void close(){
    }

public void insertUser(String instruccionSql){
  
 
    databaseConnection connector = databaseConnection.getInstance();
    java.sql.Statement dbStatement = null;
    try {
        dbStatement = connector.getConnection();
    } 
    catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    try{
        dbStatement.executeUpdate(instruccionSql);
        }
        catch(SQLException exc){
            System.out.println(exc.getMessage());
        }

                

}

}




