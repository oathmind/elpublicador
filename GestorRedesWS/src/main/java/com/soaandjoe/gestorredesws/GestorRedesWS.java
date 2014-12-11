package com.soaandjoe.gestorredesws;

import com.soaandjoe.gestoredes.CodigosFacebookBean;
import com.soaandjoe.gestoredes.CodigosGoogleBean;
import com.soaandjoe.gestoredes.CodigosTwitterBean;
import com.soaandjoe.gestoredes.ConfiguracionesRedesBean;
import com.soaandjoe.gestoredes.ResponseMensajeBean;
import com.soaandjoe.gestoredes.ResponseTokenFinalTwitterBean;
import com.soaandjoe.gestoredes.ResponseURLVinculacionTwitterBean;
import com.soaandjoe.gestoredes.TokenFinalTwitterBean;
import com.soaandjoe.twitter.TwitterAppKeys;
import com.soaandjoe.twitter.TwitterUserTokens;
import com.soaandjoe.twitter.TwitterUtil;
import com.soaandjoe.twitter.UrlKey;
import com.soaandjoe.twitter.UrlKeyUsuario;
import javax.jws.WebService;

/**
 *
 * @author jmol
 */
@WebService(serviceName = "gestorRedes", portName = "gestorRedesPort", endpointInterface = "com.soaandjoe.gestoredes.GestorRedesWSCV", targetNamespace = "http://gestoredes.soaandjoe.com/", wsdlLocation = "WEB-INF/wsdl/gestorRedes.wsdl")
public class GestorRedesWS {

    public ResponseURLVinculacionTwitterBean obtenerURLVinculacionTwitter() {
        //Obtenemos las claves para twitter
        TwitterAppKeys clavesTwitter = new UtilGestorRedes().obtenerClavesTwitter();
        //hacemos la llamada a la libreria
        TwitterUtil tu = new TwitterUtil(clavesTwitter);
        UrlKey clavesParaURL = tu.obtenerUrlParaKeyUsuario();
        //Formatamos la respuesta
        ResponseURLVinculacionTwitterBean valores = new ResponseURLVinculacionTwitterBean();
        valores.setUrl(clavesParaURL.getUrl());
        valores.setToken(clavesParaURL.getToken());
        valores.setTokenSecret(clavesParaURL.getTokenSecret());
        return valores;
    }

    public ResponseTokenFinalTwitterBean obtenerTokenFinalTwitter(TokenFinalTwitterBean tokenTemporal) {
        //Obtenemos las claves para twitter
        TwitterAppKeys clavesTwitter = new UtilGestorRedes().obtenerClavesTwitter();
        //hacemos la llamada a la libreria
        TwitterUtil tu = new TwitterUtil(clavesTwitter);
        UrlKeyUsuario urlKeyUsu = new UrlKeyUsuario(tokenTemporal.getToken(), tokenTemporal.getTokenSecret(), tokenTemporal.getKey());
        TwitterUserTokens clavesParaURL = tu.obtenerTokensUsuario(urlKeyUsu);
        //Formatamos la respuesta
        ResponseTokenFinalTwitterBean valores = new ResponseTokenFinalTwitterBean();
        valores.setToken(clavesParaURL.getToken());
        valores.setTokenSecret(clavesParaURL.getTokenSecret());
        return valores;

    }

    public ResponseMensajeBean publicarMensaje(String mensaje, ConfiguracionesRedesBean configuracionesRedes) {
        ResponseMensajeBean respuesta = new ResponseMensajeBean();
        CodigosTwitterBean codigosTwitter = configuracionesRedes.getCodigosTwitter();
        CodigosFacebookBean codigosFacebook = configuracionesRedes.getCodigosFacebook();
        CodigosGoogleBean codigosGoogle = configuracionesRedes.getCodigosGoogle();
        if (codigosTwitter != null && codigosTwitter.getToken() != null) {
            //Obtenemos las claves para twitter
            TwitterAppKeys clavesTwitter = new UtilGestorRedes().obtenerClavesTwitter();
            //hacemos la llamada a la libreria
            TwitterUtil tu = new TwitterUtil(clavesTwitter);
            TwitterUserTokens tokenUsuario = new TwitterUserTokens(codigosTwitter.getToken(), codigosTwitter.getTokenSecret());
            boolean resultadoTwitter = tu.publicarTwitter(mensaje, tokenUsuario);
            respuesta.setTwitterOk(resultadoTwitter);
        }
        if (codigosFacebook != null && codigosFacebook.getToken() != null) {
            //TODO FACEBOOK
        }
        if (codigosGoogle != null && codigosGoogle.getToken() != null) {
            //TODO GOOGLE
        }
        return respuesta;
    }

}
