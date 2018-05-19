/*
 * To change this license header, choose License Headers in Project dbCredentials.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Macedo
 */
public class dbCredentials {
    
    private String connection;
    private String user;
    private String password;
    
    public dbCredentials(String path){
        getCredentials(path);
    }
    
    private void getCredentials(String path){
        Properties prop = new Properties();
        InputStream input = null;
        
        try{
            input = dbCredentials.class.getClassLoader().getResourceAsStream(path);
            prop.load(input);
            
            this.setConnection("jdbc:mysql://" + prop.getProperty("host") + ":"
                    + prop.getProperty("port") +"/"
                    + prop.getProperty("database"));
            this.setUser(prop.getProperty("user"));
            this.setPassword(prop.getProperty("password"));
        }
        catch (IOException ex){
            Logger.getLogger(dbCredentials.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(input != null){
                try {
                    input.close();
                } catch (IOException ex) {
                    Logger.getLogger(dbCredentials.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }    

    /**
     * @return the connection
     */
    public String getConnection() {
        return connection;
    }

    /**
     * @param connection the connection to set
     */
    public void setConnection(String connection) {
        this.connection = connection;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
