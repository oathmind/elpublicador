package com.soaandjoe.gestoraplicacion.gestoraplicacionws;

import com.soaandjoe.gestoraplicacion.MensajeBean;
import com.soaandjoe.gestoraplicacion.ResponseDashBoardBean;
import com.soaandjoe.gestoraplicacion.ResponseHistoricoMensajesBean;
import com.soaandjoe.gestoraplicacion.dao.MensajeDAO;
import com.soaandjoe.gestoraplicacion.entity.Mensaje;
import java.util.List;
import javax.jws.WebService;

/**
 *
 * @author Joel
 */
@WebService(serviceName = "gestorAplicacion", portName = "gestorAplicacionPort", endpointInterface = "com.soaandjoe.gestoraplicacion.GestorAplicacionWSCV", targetNamespace = "http://gestoraplicacion.soaandjoe.com/", wsdlLocation = "WEB-INF/wsdl/gestorAplicacion.wsdl")
public class GestorAplicacionWS {

    public ResponseHistoricoMensajesBean obtenerHistoricoMensajes(int idUsuario, long timestamp, String hash) {

        new ValidadorLlamadas().validarLlamada(timestamp, hash, idUsuario);
        ResponseHistoricoMensajesBean respuestaMensajes = new ResponseHistoricoMensajesBean();

        List<MensajeBean> mensajes = respuestaMensajes.getMensajes();

        MensajeDAO dao = new MensajeDAO();
        
        List<Mensaje> mensajesBase = dao.obtenerMensajesUsuario(idUsuario);
        
        for (Mensaje mensaje : mensajesBase) {
            MensajeBean bean = new MensajeBean();
            
            bean.setIdMensaje(mensaje.getIdMensaje());
            bean.setIdUsuario(mensaje.getIdUsuario());
            bean.setMensaje(mensaje.getMensaje());
            bean.setTwitter(mensaje.isEsTwitter());
            bean.setFacebook(mensaje.isEsFacebook());
            bean.setGoogle(mensaje.isEsGoogle());
            
            mensajes.add(bean);
        }

        return respuestaMensajes;
    }

    public int obtenerIdUsuario(String usuario, String password, long timestamp, String hash) {

        new ValidadorLlamadas().validarLlamada(timestamp, hash, usuario, password);
        return 1;
    }

    public int registrarUsuario(String email, String password, String nombre, long timestamp, String hash) {

        new ValidadorLlamadas().validarLlamada(timestamp, hash, email, password, nombre);
        return 1;
    }

    public ResponseDashBoardBean obtenerDashBoard(int idUsuario, long timestamp, String hash) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
