/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soaandjoe.gestoredes;

import com.soaandjoe.twitter.TwitterAppKeys;
import com.soaandjoe.twitter.TwitterUserTokens;
import com.soaandjoe.twitter.TwitterUtil;
import com.soaandjoe.twitter.UrlKey;
import com.soaandjoe.twitter.UrlKeyUsuario;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Joel
 */
@WebService(serviceName = "WSGestorRedes")
public class WSGestorRedes {

    @WebMethod(operationName = "obtenerURLVincularTwitter")
    public UrlKey obtenerURLVincularTwitter() {
        TwitterAppKeys clavesTwitter = new UtilGestorRedes().obtenerClavesTwitter();
        TwitterUtil tu = new TwitterUtil(clavesTwitter);
        tu.obtenerUrlParaKeyUsuario();
        return tu.obtenerUrlParaKeyUsuario();
    }

    @WebMethod(operationName = "obtenerTokensFinalesTwitter")
    public TwitterUserTokens obtenerTokensFinalesTwitter(@WebParam(name = "clavesTemporales") UrlKeyUsuario clavesTemporales) {
        TwitterAppKeys clavesTwitter = new UtilGestorRedes().obtenerClavesTwitter();
        TwitterUtil tu = new TwitterUtil(clavesTwitter);
        TwitterUserTokens tokens = tu.obtenerTokensUsuario(clavesTemporales);
        return tokens;
    }

    @WebMethod(operationName = "publicarMensajes")
    public boolean publicarMensajes(@WebParam(name = "mensaje") String mensaje,
            @WebParam(name = "publicarTwitter") boolean publicarTwitter,
            @WebParam(name = "clavesTwitter") TwitterUserTokens clavesUsuarioTwitter,
            @WebParam(name = "publicarFacebook") boolean publicarFacebook,
            @WebParam(name = "publicarGooglePlus") boolean publicarGooglePlus) {
        if (publicarTwitter) {
            TwitterAppKeys clavesTwitter = new UtilGestorRedes().obtenerClavesTwitter();
            TwitterUtil tu = new TwitterUtil(clavesTwitter);
            tu.publicarTwitter(mensaje, clavesUsuarioTwitter);
        }
        if (publicarFacebook) {
        }
        if (publicarGooglePlus) {
        }
        return true;
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
