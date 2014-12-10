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

    public ResponseDashBoardBean obtenerDashBoard(int idUsuario) {
        long timestamp = System.currentTimeMillis();
        String hash = new GeneradorHash().generarHash("obtenerDashBoard", timestamp, idUsuario);
        return webService.obtenerDashBoard(idUsuario, timestamp, hash);
    }

    public ResponseHistoricoMensajesBean obtenerHistoricoMensajes(int idUsuario) {
        long timestamp = System.currentTimeMillis();
        String hash = new GeneradorHash().generarHash("obtenerHistoricoMensajes", timestamp, idUsuario);
        return webService.obtenerHistoricoMensajes(idUsuario, timestamp, hash);
    }

    public boolean publicarMensaje(int idUsuario, String mensaje, boolean toTwitter, boolean toFacebook, boolean toGoogle) {
        long timestamp = System.currentTimeMillis();
        String hash = new GeneradorHash().generarHash("publicarMensaje", timestamp, idUsuario, mensaje, toTwitter, toFacebook, toGoogle);
        return webService.publicarMensaje(idUsuario, mensaje, toTwitter, toFacebook, toGoogle, timestamp, hash);
    }

}
