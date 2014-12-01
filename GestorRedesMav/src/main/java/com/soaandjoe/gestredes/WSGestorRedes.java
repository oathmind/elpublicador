/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soaandjoe.gestredes;

import com.soaandjoe.gestredes.persistencia.Conexion;
import com.soaandjoe.twitter.TwitterAppKeys;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.naming.NamingException;

/**
 *
 * @author Joel
 */
@WebService(serviceName = "WSGestorRedes")
public class WSGestorRedes {

    /**
     * This is a sample web service operation
     * @return 
     */
    @WebMethod(operationName = "obtenerClavesTwitter")
    public TwitterAppKeys obtenerClavesTwitter() {
        TwitterAppKeys obtenerClavesTwitter = new UtilGestorRedes().obtenerClavesTwitter();
        return obtenerClavesTwitter;
    }
    
    
    @WebMethod(operationName = "testDatabase")
    public boolean testDatabase() {
        Connection c = null;
        try {
            c = Conexion.getConnexio();
            c.close();
        } catch (NamingException ex) {
            Logger.getLogger(WSGestorRedes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(WSGestorRedes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c != null;
    }
    
    
    
    
    /**
     * This is a sample web service operation
     */
//    @WebMethod(operationName = "obtenerClavesTwitter")
//    public TwitterAppKeys obtenerClavesTwitter(@WebParam(name = "aplicacion") String aplicacion) {
//        TwitterAppKeys obtenerClavesTwitter = new UtilGestorRedes().obtenerClavesTwitter();
//        return obtenerClavesTwitter;
//    }
}
