package com.soaandjoe.gestoraplicacion.gestoraplicacionws;

import com.soaandjoe.gestoraplicacion.GestorAplicacionWSCV;
import com.soaandjoe.gestoraplicacion.MensajeBean;
import com.soaandjoe.gestoraplicacion.ResponseDashBoardBean;
import com.soaandjoe.gestoraplicacion.ResponseHistoricoMensajesBean;
import com.soaandjoe.gestoraplicacion.ResponseVincularTwitterStep1Bean;
import com.soaandjoe.gestoraplicacion.dao.MensajeDAO;
import com.soaandjoe.gestoraplicacion.dao.UserDAO;
import com.soaandjoe.gestoraplicacion.entity.Mensaje;
import java.util.List;
import javax.jws.WebService;

/**
 *
 * @author Joel
 */
@WebService(serviceName = "gestorAplicacion", portName = "gestorAplicacionPort", endpointInterface = "com.soaandjoe.gestoraplicacion.GestorAplicacionWSCV", targetNamespace = "http://gestoraplicacion.soaandjoe.com/", wsdlLocation = "WEB-INF/wsdl/gestorAplicacion.wsdl")
public class GestorAplicacionWS implements GestorAplicacionWSCV {

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

    public int obtenerIdUsuario(String email, String password, long timestamp, String hash) {

        new ValidadorLlamadas().validarLlamada(timestamp, hash, email, password);
        UserDAO dao = new UserDAO();

        int idUsuario = dao.obtenerIdUsuario(email, password);
        return idUsuario;
    }

    public int registrarUsuario(String email, String password, String nombre, long timestamp, String hash) {

        new ValidadorLlamadas().validarLlamada(timestamp, hash, email, password, nombre);

        UserDAO dao = new UserDAO();
        int idUsuario = -1;
        boolean insetado = dao.insertarUsuario(email, nombre, password);

        if (insetado) {
            idUsuario = dao.obtenerIdUsuario(email, password);
        }
        return idUsuario;
    }

    public ResponseDashBoardBean obtenerDashBoard(int idUsuario, long timestamp, String hash) {

        new ValidadorLlamadas().validarLlamada(timestamp, hash, idUsuario);

        ResponseDashBoardBean respuesta = new ResponseDashBoardBean();

        //TODO Obtener informacion del usuario
        respuesta.setNombreUsuario("Joel Molins");
        respuesta.setVinculadoTwitter(true);
        respuesta.setVinculadoFacebook(false);
        respuesta.setVinculadoGoogle(false);

        List<MensajeBean> ultimosMensajes = respuesta.getUltimosMensajes();

        MensajeDAO dao = new MensajeDAO();

        List<Mensaje> mensajesBase = dao.obtenerMensajesUsuario(idUsuario);

        for (int i = 0; i < mensajesBase.size() && i < 5; i++) {
            Mensaje mensaje = mensajesBase.get(i);

            MensajeBean bean = new MensajeBean();

            bean.setIdMensaje(mensaje.getIdMensaje());
            bean.setIdUsuario(mensaje.getIdUsuario());
            bean.setMensaje(mensaje.getMensaje());
            bean.setTwitter(mensaje.isEsTwitter());
            bean.setFacebook(mensaje.isEsFacebook());
            bean.setGoogle(mensaje.isEsGoogle());

            ultimosMensajes.add(bean);
        }

        return respuesta;
    }

    public boolean publicarMensaje(String mensaje, boolean toTwitter, boolean toFacebook, boolean toGoogle, long timestamp, String hash) {

        new ValidadorLlamadas().validarLlamada(timestamp, hash, mensaje, toTwitter, toFacebook, toGoogle);
        return true;
    }

    public ResponseVincularTwitterStep1Bean vincularTwitterStep1(int idUsuario, long timestamp, String hash) {

        new ValidadorLlamadas().validarLlamada(timestamp, hash, idUsuario);
        return null;
    }

    public boolean vincularTwitterStep2(int idUsuario, String clave, long timestamp, String hash) {

        new ValidadorLlamadas().validarLlamada(timestamp, hash, idUsuario);
        return true;
    }
}
