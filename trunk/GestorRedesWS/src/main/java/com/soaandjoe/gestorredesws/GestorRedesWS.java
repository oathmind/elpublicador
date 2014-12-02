package com.soaandjoe.gestorredesws;

import com.soaandjoe.gestoredes.GestorRedesWSCV;
import com.soaandjoe.gestoredes.ResponseTokenFinalTwitterBean;
import com.soaandjoe.gestoredes.ResponseURLVinculacionTwitterBean;
import com.soaandjoe.gestoredes.TokenFinalTwitterBean;
import com.soaandjoe.twitter.TwitterAppKeys;
import com.soaandjoe.twitter.TwitterUtil;
import com.soaandjoe.twitter.UrlKey;
import javax.jws.WebService;

/**
 *
 * @author jmol
 */
@WebService(serviceName = "gestorRedes", portName = "gestorRedesPort", endpointInterface = "com.soaandjoe.gestoredes.GestorRedesWSCV", targetNamespace = "http://gestoredes.soaandjoe.com/", wsdlLocation = "WEB-INF/wsdl/gestorRedes.wsdl")
 public class GestorRedesWS implements GestorRedesWSCV {

    public ResponseURLVinculacionTwitterBean obtenerURLVinculacionTwitter() {
        TwitterAppKeys clavesTwitter = new UtilGestorRedes().obtenerClavesTwitter();
        TwitterUtil tu = new TwitterUtil(clavesTwitter);
        UrlKey clavesParaURL = tu.obtenerUrlParaKeyUsuario();
        ResponseURLVinculacionTwitterBean valores = new ResponseURLVinculacionTwitterBean();
        valores.setUrl(clavesParaURL.getUrl());
        valores.setToken(clavesParaURL.getToken());
        valores.setTokenSecret(clavesParaURL.getTokenSecret());
        return valores;
    }

    public ResponseTokenFinalTwitterBean obtenerTokenFinalTwitter(TokenFinalTwitterBean tokenTemporal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
