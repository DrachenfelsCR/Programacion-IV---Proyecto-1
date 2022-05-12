/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.data;
import cinema.logic.Pelicula;
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
 * @author David
 */
public class PeliculaDao {
    Connection con;
    databaseConnection cn = new databaseConnection();
    PreparedStatement ps;
    
    public void read(Pelicula o) throws Exception{
        String sql="insert into Pelicula (name_pelicula, cartelera) values(?,?)";
        con = cn.getConnections();
        ps= con.prepareStatement(sql);
        ps.setString(1, o.getMovieName());
        ps.setBoolean(2, o.getCartelera());
       
        
        int count=databaseConnection.getInstance().executeUpdate(ps);
        if (count==0){
            
               sql="update Pelicula set cartelera =? where name_pelicula =?;";
        con = cn.getConnections();
        ps= con.prepareStatement(sql);
        ps.setBoolean(1, o.getCartelera());
        ps.setString(2, o.getMovieName());       
        count=databaseConnection.getInstance().executeUpdate(ps);
        }
    }
    
    public Pelicula from (ResultSet rs){
        try {
          
            Pelicula r= new Pelicula();
            r.setMovieName(rs.getString("name_pelicula"));
            r.setCartelera(rs.getBoolean("cartelera"));
            return r;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public ArrayList<Pelicula> listaPeliculas(){
            String sql ="Select * from Pelicula";
             ResultSet rs;
              ArrayList<Pelicula> r= new ArrayList<>();
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
}


