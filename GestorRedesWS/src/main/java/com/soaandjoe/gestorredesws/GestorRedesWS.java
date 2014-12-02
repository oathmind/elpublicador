package com.soaandjoe.gestorredesws;

import com.soaandjoe.gestoredes.ObtenerURLVinculacionTwitterResponse;
import com.soaandjoe.twitter.TwitterAppKeys;
import com.soaandjoe.twitter.TwitterUtil;
import com.soaandjoe.twitter.UrlKey;
import javax.jws.WebService;

/**
 *
 * @author jmol
 */
@WebService(serviceName = "gestorRedes", portName = "gestorRedesPort", endpointInterface = "com.soaandjoe.gestoredes.GestorRedesWSCV", targetNamespace = "http://gestoredes.soaandjoe.com/", wsdlLocation = "WEB-INF/wsdl/gestorRedes.wsdl")
public class GestorRedesWS {

    public ObtenerURLVinculacionTwitterResponse obtenerURLVinculacionTwitter() {
        TwitterAppKeys clavesTwitter = new UtilGestorRedes().obtenerClavesTwitter();
        TwitterUtil tu = new TwitterUtil(clavesTwitter);
        UrlKey clavesParaURL = tu.obtenerUrlParaKeyUsuario();
        ObtenerURLVinculacionTwitterResponse valores = new ObtenerURLVinculacionTwitterResponse();
        valores.setUrl(clavesParaURL.getUrl());
        valores.setToken(clavesParaURL.getToken());
        valores.setTokenSecret(clavesParaURL.getTokenSecret());
        return valores;
    }
}
