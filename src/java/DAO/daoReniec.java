/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alulab14
 */
public class daoReniec extends daoBase {
    public Boolean validarCliente(String dni, String nombre, String apellidoP, String apellidoM){
        String sql = "SELECT * FROM Persona WHERE dni = ?";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();
            String nombreDB = new String();
            String apellidoPDB = new String();
            String apellidoMDB = new String();
            while(rs.next()){
                nombreDB = rs.getString(2);
                apellidoPDB = rs.getString(3);
                apellidoMDB = rs.getString(4);
            }
            return (nombreDB.equals(nombre) && apellidoPDB.equals(apellidoP) && apellidoMDB.equals(apellidoM));
        }
        catch (SQLException ex){
            Logger.getLogger(daoReniec.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public void crearToken(String usuario, String password){
        
    }
    
    public String validarCliente(String usuario, String password){
        // Revisamos si el usuario coincide con el usuario y contraseña
        String sql = "SELECT token FROM Autenticación WHERE usuario = ? AND password = ?";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                return rs.getString(1);
            }
            crearToken(usuario, password);
            return "-1";
        }
        catch (SQLException ex){
            Logger.getLogger(daoReniec.class.getName()).log(Level.SEVERE, null, ex);
            return "-1";
        }
    }
}
