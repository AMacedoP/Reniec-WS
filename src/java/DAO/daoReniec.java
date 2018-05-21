/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.StringHash;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ThreadLocalRandom;
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
    
    public String crearToken(String usuario){
        int min = 4564843;
        int max = 9999953;
        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);   
        
        String token = null;
        try {
            token = StringHash.getSHA(Integer.toString(randomNum));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(daoReniec.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String sql = "UPDATE Autenticación SET token = ? Where usuario = ?";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, token);
            stmt.setString(2, usuario);
            stmt.executeUpdate();
            return token;
        }
        catch (SQLException ex){
            Logger.getLogger(daoReniec.class.getName()).log(Level.SEVERE, null, ex);
            return "-1";
        }
    }
    
    public Boolean validarToken(String token){
        String sql = "SELECT * FROM Autenticación WHERE token = ? AND TIMESTAMPDIFF(MINUTE, NOW(), dateCreated) < 30 AND validToken = 1";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, token);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())return true;
            return false;
        }
        catch (SQLException ex){
            Logger.getLogger(daoReniec.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public String validarUsuario(String usuario, String password){
        // Revisamos si el usuario coincide con el usuario y contraseña
        String sql = "SELECT * FROM Autenticación WHERE usuario = ? AND password = ?";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                // Si es válido, creamos un nuevo token y lo insertamos en la tabla
                // además de devolverlo
                return crearToken(usuario);
            }
            // Si no existe usuario, se devuelve error
            return "-1";
        }
        catch (SQLException ex){
            Logger.getLogger(daoReniec.class.getName()).log(Level.SEVERE, null, ex);
            return "-1";
        }
    }
}
