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
import cinema.data.databaseConnection;
import cinema.logic.Factura;
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
public class FacturaDao {

         Connection con;
         databaseConnection cn = new databaseConnection();
         PreparedStatement ps;
         
  
   
    
        public boolean validarButaca(String butaca, String tanda) {
        String sql = "Select * from Tanda where id_asiento=? and id_tanda=? and estado = true;";
        String x="";
          int r2=0;
        ResultSet rs;
        try {
            con = cn.getConnections();
            ps= con.prepareStatement(sql);
            ps.setString(1,butaca);
            ps.setString(2,tanda);
            rs= ps.executeQuery();
            if(rs.next()){
                r2=r2+1;
            }
            if(r2==1){
                return true;
            }
            else {
                return false;
            }
            
        }catch(Exception e){
           return false;
        }

        
        }
        
        
        public void updateButacaEstado(String butaca,String tanda) {
        String sql = "update Tanda set estado=false where id_asiento=? and id_tanda=?";
            ResultSet rs;
            try {
            con = cn.getConnections();
            ps= con.prepareStatement(sql);            
            ps.setString(1,butaca);
            ps.setString(2,tanda);
            int count=databaseConnection.getInstance().executeUpdate(ps);
           
            }catch(Exception e){
           System.out.println(e.getMessage());
        }
            
        
        
     }
      
        
    public void updateButacaUser(String idUser, String butaca,String tanda ) {
            String sql = "update Tanda set id_cliente=? where id_asiento=? and id_tanda=?";
            ResultSet rs;
            try {
            con = cn.getConnections();
            ps= con.prepareStatement(sql);    
            ps.setString(1,idUser);
            ps.setString(2,butaca);
            ps.setString(3,tanda);
            
            int count=databaseConnection.getInstance().executeUpdate(ps);
           
            }catch(Exception e){
           System.out.println(e.getMessage());
        }
    }
        
    public void insertFactura(String instruccionSql){
  
 
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
        
    public ArrayList<Factura> findAll(String idFact) {
        ArrayList<Factura> r= new ArrayList(); 
        
        String sql = "Select * from Factura where id_factura=?";
        
        ResultSet rs;
        int r2=0;
        try {
            con = cn.getConnections();
            ps= con.prepareStatement(sql);
            ps.setString(1,idFact);
            rs= ps.executeQuery();
            while(rs.next()){
                
                r.add(from(rs));
            }
           
            
        }catch(SQLException ex){
           
        }
         return r;
        
        
        
    }
    
    
    public ArrayList<String> findAllForUser(String idCliente) {
        ArrayList<String> r= new ArrayList(); 
        
        String sql = "Select distinct id_factura from Factura where id_cliente=?";
        
        ResultSet rs;
        int r2=0;
        try {
            con = cn.getConnections();
            ps= con.prepareStatement(sql);
            ps.setString(1,idCliente);
            rs= ps.executeQuery();
            while(rs.next()){
                
                r.add(fromString(rs));
            }
           
            
        }catch(SQLException ex){
           
        }
         return r;
        
        
        
    }
    
    
    public int findCantFact() {
        int r= 0; 
        
        String sql = "Select count(distinct id_factura)cant from Factura";
        
        ResultSet rs;
        int r2=0;
        try {
            con = cn.getConnections();
            ps= con.prepareStatement(sql);
            rs= ps.executeQuery();
            while(rs.next()){
                
                r = rs.getInt("cant");
            }
           
            
        }catch(SQLException ex){
           
        }
         return r;
        
        
        
    }
    
    
    public Factura from (ResultSet rs){
        try {
            Factura r= new Factura();
            r.setId(rs.getString("id_factura"));
            r.setTanda(rs.getString("id_tanda"));
            r.setNameCliente(rs.getString("name_cliente"));
            r.setIdCliente(rs.getString("id_cliente"));
            r.setIdButaca(rs.getString("id_butaca"));
            
            return r;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    
    public String fromString (ResultSet rs){
        try {
            String r = rs.getString("id_factura");

            return r;
        } catch (SQLException ex) {
            return null;
        }
    }
        
        
}