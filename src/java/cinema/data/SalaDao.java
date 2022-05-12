/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.data;
import cinema.logic.Sala;
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
public class SalaDao {
    
    Connection con;
    databaseConnection cn = new databaseConnection();
    PreparedStatement ps;
         
     public void read(Sala o) throws Exception{
        String sql="insert into Sala (id_sala)"+
                "values(?)";
             con = cn.getConnections();
            ps= con.prepareStatement(sql);
        ps= con.prepareStatement(sql);
        ps.setString(1, o.getIdSala());
       
        
        int count=databaseConnection.getInstance().executeUpdate(ps);
        if (count==0){
            throw new Exception("Sala ya existe");
        }
    }
     public Sala Confirm(Sala u) {
        String sql = "Select * from Sala where id_sala=?";
        String x="";
        ResultSet rs;
        int r2=0;
        try {
            con = cn.getConnections();
            ps= con.prepareStatement(sql);
            ps.setString(1,u.getIdSala());
         
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
     public ArrayList<Sala> listaSalas(){
            String sql ="Select * from Sala";
             ResultSet rs;
              ArrayList<Sala> r= new ArrayList<>();
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
     
     
     
     
     public Sala from (ResultSet rs){
        try {
            Sala r= new Sala();
            r.setIdSala(rs.getString("id_sala"));
            return r;
        } catch (SQLException ex) {
            return null;
        }
    }
}
