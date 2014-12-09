package com.soaandjoe.elpublicadorweb.clienteWS;

import com.soaandjoe.elpublicadorweb.util.GeneradorHash;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joel
 */
public class ClienteGestorAplicacionWS {

    GestorAplicacionWSCV webService;

    public ClienteGestorAplicacionWS() {
        try {
            webService = new GestorAplicacion(new URL("http://localhost:8080/GestorAplicacion/gestorAplicacion?wsdl")).getGestorAplicacionPort();
        } catch (MalformedURLException ex) {
            Logger.getLogger(ClienteGestorAplicacionWS.class.getName()).log(Level.SEVERE, null, ex);
            webService = new GestorAplicacion().getGestorAplicacionPort();
        }
    }

    public int identificarUsuario(String email, String password) {
        long timestamp = System.currentTimeMillis();
        String hash = new GeneradorHash().generarHash("identificarUsuario", timestamp, email, password);
        return webService.obtenerIdUsuario(email, password, timestamp, hash);
    }

    public int registrarUsuario(String email, String password, String nombre) {
        long timestamp = System.currentTimeMillis();
        String hash = new GeneradorHash().generarHash("registrarUsuario", timestamp, email, password, nombre);
        return webService.registrarUsuario(email, password, nombre, timestamp, hash);
    }

}
