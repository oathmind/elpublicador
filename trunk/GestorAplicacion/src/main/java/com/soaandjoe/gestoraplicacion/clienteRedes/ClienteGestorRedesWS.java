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
    
    public static void main(String[] args) {
        ResponseURLVinculacionTwitterBean obtenerURLVinculacionTwitter = new ClienteGestorRedesWS().webService.obtenerURLVinculacionTwitter();
        System.out.println(obtenerURLVinculacionTwitter.getUrl());
        System.out.println(obtenerURLVinculacionTwitter.getToken());
        System.out.println(obtenerURLVinculacionTwitter.getTokenSecret());
    }
}
