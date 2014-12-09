package com.soaandjoe.gestoraplicacion.clienteRedes;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joel
 */
public class ClienteGestorRedesWS {

    GestorRedesWSCV webService;

    public ClienteGestorRedesWS() {
        try {
            webService = new GestorRedes(new URL("http://localhost:8080/GestorRedesWS/gestorRedes?wsdl")).getGestorRedesPort();
        } catch (MalformedURLException ex) {
            Logger.getLogger(ClienteGestorRedesWS.class.getName()).log(Level.SEVERE, null, ex);
            webService = new GestorRedes().getGestorRedesPort();
        }
    }

    public ResponseURLVinculacionTwitterBean obtenerURLVinculacionTwitter() {
        return webService.obtenerURLVinculacionTwitter();
    }


    public ResponseTokenFinalTwitterBean obtenerTokenFinalTwitter(TokenFinalTwitterBean tokenTemporal) {
        return webService.obtenerTokenFinalTwitter(tokenTemporal);
    }


    public ResponseMensajeBean publicarMensaje(String mensaje, ConfiguracionesRedesBean configuracionesRedes) {
        return webService.publicarMensaje(mensaje, configuracionesRedes);
    }


}
