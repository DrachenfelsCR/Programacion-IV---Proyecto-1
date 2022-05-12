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
public class TandaDao {
   Connection con;
    databaseConnection cn = new databaseConnection();
    PreparedStatement ps;
    
    
    public void read(Tanda o,String asiento) throws Exception{
        String sql="insert into Tanda (id_tanda,id_asiento,id_cliente,estado,name_pelicula,id_sala,precio,fecha,hora)"+
                "values(?,?,?,?,?,?,?,?,?)";
        con = cn.getConnections();
        ps= con.prepareStatement(sql);
        ps.setString(1, o.getIdTanda());
        ps.setString(2, asiento);
        ps.setString(3, o.getIdCliente());
        ps.setBoolean(4, o.isEstado());
        ps.setString(5, o.getIdPelicula());
        ps.setString(6, o.getIdSala());
        ps.setInt(7, o.getIdPrecio());
        ps.setString(8, o.getIdFecha());
        ps.setString(9, o.getIdHora());
       
        
        int count=databaseConnection.getInstance().executeUpdate(ps);
        if (count==0){
            throw new Exception("Tanda ya existe");
        }
    }
    
    
        public ArrayList<Tanda> findAll(String idTanda) {
        ArrayList<Tanda> r= new ArrayList(); 
        
        String sql = "select * from Tanda where id_tanda=? order by (SELECT CAST(id_asiento AS UNSIGNED)) asc;";
        
        ResultSet rs;
        int r2=0;
        try {
            con = cn.getConnections();
            ps= con.prepareStatement(sql);
            ps.setString(1,idTanda);
            rs= ps.executeQuery();
            while(rs.next()){
                
                r.add(from(rs));
            }
           
            
        }catch(SQLException ex){
           
        }
         return r;
        
        
        
    }
    
    
     public Tanda from (ResultSet rs){
        try {
            Tanda r= new Tanda();
            r.setIdTanda(rs.getString("id_tanda"));
            r.setIdAsiento(rs.getString("id_asiento"));
            r.setIdCliente(rs.getString("id_cliente"));
            r.setEstado(rs.getBoolean("estado"));
            r.setIdPelicula(rs.getString("name_pelicula"));
            r.setIdSala(rs.getString("id_sala"));
            r.setIdPrecio(rs.getInt("precio"));
            r.setIdHora(rs.getString("fecha"));
            r.setIdFecha(rs.getString("hora"));
            
            
            
            
            return r;
        } catch (SQLException ex) {
            return null;
        }
    }
 
}
