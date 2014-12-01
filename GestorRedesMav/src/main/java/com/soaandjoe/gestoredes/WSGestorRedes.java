/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soaandjoe.gestoredes;

import com.soaandjoe.twitter.TwitterAppKeys;
import com.soaandjoe.twitter.TwitterUtil;
import com.soaandjoe.twitter.UrlKey;
import java.util.HashMap;
import java.util.Map;
import javax.jws.WebService;
import javax.jws.WebMethod;

/**
 *
 * @author Joel
 */
@WebService(serviceName = "WSGestorRedes")
public class WSGestorRedes {

    @WebMethod(operationName = "obtenerURLVincularTwitter")
    public HashMap obtenerURLVincularTwitter() {
        TwitterAppKeys clavesTwitter = new UtilGestorRedes().obtenerClavesTwitter();
        TwitterUtil tu = new TwitterUtil(clavesTwitter);
        UrlKey obtenerUrlParaKeyUsuario = tu.obtenerUrlParaKeyUsuario();
        HashMap valores = new HashMap();
        valores.put("URL", obtenerUrlParaKeyUsuario.getUrl());
        valores.put("TOKEN", obtenerUrlParaKeyUsuario.getToken());
        valores.put("TOKENSECRET", obtenerUrlParaKeyUsuario.getTokenSecret());
        return valores;
    }

//    @WebMethod(operationName = "obtenerTokensFinalesTwitter")
//    public Map obtenerTokensFinalesTwitter(@WebParam(name = "clavesTemporales") UrlKeyUsuario clavesTemporales) {
//        TwitterAppKeys clavesTwitter = new UtilGestorRedes().obtenerClavesTwitter();
//        TwitterUtil tu = new TwitterUtil(clavesTwitter);
//        TwitterUserTokens tokens = tu.obtenerTokensUsuario(clavesTemporales);
//        Map valores = new HashMap();
//        valores.put("", tokens.getToken());
//        valores.put("", tokens.getToken());
//        return valores;
//    }

//    @WebMethod(operationName = "publicarMensajes")
//    public boolean publicarMensajes(@WebParam(name = "mensaje") String mensaje,
//            @WebParam(name = "publicarTwitter") boolean publicarTwitter,
//            @WebParam(name = "clavesTwitter") TwitterUserTokens clavesUsuarioTwitter,
//            @WebParam(name = "publicarFacebook") boolean publicarFacebook,
//            @WebParam(name = "publicarGooglePlus") boolean publicarGooglePlus) {
//        if (publicarTwitter) {
//            TwitterAppKeys clavesTwitter = new UtilGestorRedes().obtenerClavesTwitter();
//            TwitterUtil tu = new TwitterUtil(clavesTwitter);
//            tu.publicarTwitter(mensaje, clavesUsuarioTwitter);
//        }
//        if (publicarFacebook) {
//        }
//        if (publicarGooglePlus) {
//        }
//        return true;
//    }
    /**
     * This is a sample web service operation
     */
//    @WebMethod(operationName = "obtenerClavesTwitter")
//    public TwitterAppKeys obtenerClavesTwitter(@WebParam(name = "aplicacion") String aplicacion) {
//        TwitterAppKeys obtenerClavesTwitter = new UtilGestorRedes().obtenerClavesTwitter();
//        return obtenerClavesTwitter;
//    }
}
