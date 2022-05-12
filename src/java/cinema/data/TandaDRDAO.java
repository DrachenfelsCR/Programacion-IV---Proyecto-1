/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.data;

import cinema.logic.Tanda;
import cinema.logic.TandaDR;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author beto
 */
public class TandaDRDAO {
    
    
     Connection con;
    databaseConnection cn = new databaseConnection();
    PreparedStatement ps;
    
    
    public void insertaTandaDR(TandaDR o) throws Exception{
        String sql="insert into TandaDR (idtanda,idsala,name_movie,precioN,fechita,horita)"+
                "values(?,?,?,?,?,?)";
        con = cn.getConnections();
        ps= con.prepareStatement(sql);
        ps.setString(1, o.getIdTanda());
        ps.setString(2, o.getIdSala());
        ps.setString(3, o.getNombrePelicula());
        ps.setInt(4, o.getPrecio());
        ps.setString(5, o.getFechita());
        ps.setString(6, o.getHorita());
       
        
        int count=databaseConnection.getInstance().executeUpdate(ps);
        if (count==0){
            throw new Exception("Tanda ya existe");
        }
    }
    
    public ArrayList<TandaDR> getTandasDR() {
        ArrayList<TandaDR> r= new ArrayList(); 
        
        String sql="select * from TandaDR order by name_movie asc";
        
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
    
     public TandaDR from (ResultSet rs){
        try {
            TandaDR r= new TandaDR();
            r.setIdTanda(rs.getString("idtanda"));
            r.setIdSala(rs.getString("idsala"));
            r.setNombrePelicula(rs.getString("name_movie"));
            r.setPrecio(rs.getInt("precioN"));
            r.setFechita(rs.getString("fechita"));
            r.setHorita(rs.getString("horita"));  
            return r;
        } catch (SQLException ex) {
            return null;
        }
    }
}
