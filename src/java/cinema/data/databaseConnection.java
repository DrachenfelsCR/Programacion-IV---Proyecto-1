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
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 
 */
public class databaseConnection {
    
    private static databaseConnection connector = null;
    Connection con;
    public databaseConnection() {
       
    }
    
    public static databaseConnection getInstance()
    {
     if (connector == null) {
            connector = new databaseConnection();
        }
     return connector;
    }       
    
    public Statement getConnection() throws SQLException
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(databaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
       java.sql.Connection dbConnection = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto2?useSSL=false&user=root&password=1234&serverTimezone=UTC");
       java.sql.Statement dbStatement = dbConnection.createStatement();
        return dbStatement;
    }
    
    public Connection getConnections(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto2?useSSL=false&user=root&password=1234&serverTimezone=UTC");
        }catch(Exception e){   
        }
        return con;
    }
      public PreparedStatement prepareStatement(String statement) throws SQLException {
        return con.prepareStatement(statement);
    }
    public int executeUpdate(PreparedStatement statement) {
        try {
            statement.executeUpdate();
            return statement.getUpdateCount();
        } catch (SQLException ex) {
            return 0;
        }
    }
    public ResultSet executeQuery(PreparedStatement statement){
        try {
            return statement.executeQuery();
        } catch (SQLException ex) {
        }
        return null;
    }
    
}