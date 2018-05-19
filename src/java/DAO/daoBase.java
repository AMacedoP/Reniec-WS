/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alulab14
 */
public abstract class daoBase {

    Connection conn;

    public daoBase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://54.146.152.235:3306/reniec", "sw2", "123456");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(daoBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}