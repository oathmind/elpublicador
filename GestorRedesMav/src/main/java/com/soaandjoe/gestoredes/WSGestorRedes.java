/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soaandjoe.gestoredes;

import com.soaandjoe.twitter.TwitterAppKeys;
import javax.jws.WebService;
import javax.jws.WebMethod;

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
    
    
    
    
    /**
     * This is a sample web service operation
     */
//    @WebMethod(operationName = "obtenerClavesTwitter")
//    public TwitterAppKeys obtenerClavesTwitter(@WebParam(name = "aplicacion") String aplicacion) {
//        TwitterAppKeys obtenerClavesTwitter = new UtilGestorRedes().obtenerClavesTwitter();
//        return obtenerClavesTwitter;
//    }
}
