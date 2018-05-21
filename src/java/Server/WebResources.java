/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Beans.jsonCliente;
import Beans.jsonToken;
import DAO.daoReniec;
import com.google.gson.Gson;
import java.util.HashMap;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author alulab14
 */
@Path("")
public class WebResources {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of resources
     */
    public WebResources() {
    }
    
    @POST
    @Path("validarUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String validarUsuario(@FormParam("usuario") String usuario,
                                 @FormParam("password") String password){
        daoReniec dao = new daoReniec();
        String token  = dao.validarCliente(usuario, password);
        jsonToken json = new jsonToken();
        json.setToken(token);
        if (token.equals("-1")) json.setError(true);
        else json.setError(false);
        Gson gson = new Gson();
        return gson.toJson(json);
    }
            
    @POST
    @Path("validarCliente")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String validarCliente(@FormParam("dni") String dni,
                                 @FormParam("nombre") String nombre,
                                 @FormParam("apellidoP") String apellidoP,
                                 @FormParam("apellidoM") String apellidoM,
                                 @FormParam("token") String token) {
        daoReniec dao = new daoReniec();
        Boolean validToken = dao.validarToken(token);
        Gson gson = new Gson();
        // Si el token es valido, el error es 0. Caso contrario es 1
        if(validToken){
            Boolean result = dao.validarCliente(dni, nombre, apellidoP, apellidoM);
            jsonCliente json = new jsonCliente();
            json.setError(0);
            json.setValido(result);
            return gson.toJson(json);
        }
        else{
            HashMap<String, Integer> json = new HashMap<>();
            json.put("error", 1);
            return gson.toJson(json);
        }
    }
}
