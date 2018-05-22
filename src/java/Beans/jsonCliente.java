/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author Macedo
 */
public class jsonCliente {
    private int error;
    private Boolean valido;
    private String message;

    /**
     * @return the error
     */
    public int getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(int error) {
        this.error = error;
    }

    /**
     * @return the valido
     */
    public Boolean getValido() {
        return valido;
    }

    /**
     * @param valido the valido to set
     */
    public void setValido(Boolean valido) {
        this.valido = valido;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
