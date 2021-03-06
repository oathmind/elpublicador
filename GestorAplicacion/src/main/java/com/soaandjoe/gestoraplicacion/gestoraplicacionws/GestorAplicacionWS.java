package com.soaandjoe.gestoraplicacion.gestoraplicacionws;

import com.soaandjoe.gestoraplicacion.MensajeBean;
import com.soaandjoe.gestoraplicacion.ResponseDashBoardBean;
import com.soaandjoe.gestoraplicacion.ResponseHistoricoMensajesBean;
import com.soaandjoe.gestoraplicacion.ResponseVincularTwitterStep1Bean;
import com.soaandjoe.gestoraplicacion.clienteRedes.ClienteGestorRedesWS;
import com.soaandjoe.gestoraplicacion.clienteRedes.CodigosTwitterBean;
import com.soaandjoe.gestoraplicacion.clienteRedes.ConfiguracionesRedesBean;
import com.soaandjoe.gestoraplicacion.clienteRedes.ResponseMensajeBean;
import com.soaandjoe.gestoraplicacion.clienteRedes.ResponseTokenFinalTwitterBean;
import com.soaandjoe.gestoraplicacion.clienteRedes.ResponseURLVinculacionTwitterBean;
import com.soaandjoe.gestoraplicacion.clienteRedes.TokenFinalTwitterBean;
import com.soaandjoe.gestoraplicacion.dao.EnvioMensARedDAO;
import com.soaandjoe.gestoraplicacion.dao.MensajeDAO;
import com.soaandjoe.gestoraplicacion.dao.UserDAO;
import com.soaandjoe.gestoraplicacion.dao.UserInfoRedDAO;
import com.soaandjoe.gestoraplicacion.entity.Mensaje;
import com.soaandjoe.gestoraplicacion.entity.User;
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
            bean.setFecha(mensaje.getFecha());
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

        UserDAO usudao = new UserDAO();
        User usuario = usudao.obtenerUsuarioPorId(idUsuario);
        respuesta.setNombreUsuario(usuario.getNombre());

        //TODO Obtener informacion de vinculaciones
        UserInfoRedDAO daovinculos = new UserInfoRedDAO();
        respuesta.setVinculadoTwitter(daovinculos.estaVinculadoTwitter(idUsuario));
        respuesta.setVinculadoFacebook(daovinculos.estaVinculadoFacebook(idUsuario));
        respuesta.setVinculadoGoogle(daovinculos.estaVinculadoGoogle(idUsuario));

        List<MensajeBean> ultimosMensajes = respuesta.getUltimosMensajes();

        MensajeDAO mensdao = new MensajeDAO();

        List<Mensaje> mensajesBase = mensdao.obtenerMensajesUsuario(idUsuario);

        for (int i = 0; i < mensajesBase.size() && i < 3; i++) {
            Mensaje mensaje = mensajesBase.get(i);

            MensajeBean bean = new MensajeBean();

            bean.setIdMensaje(mensaje.getIdMensaje());
            bean.setIdUsuario(mensaje.getIdUsuario());
            bean.setMensaje(mensaje.getMensaje());
            bean.setFecha(mensaje.getFecha());
            bean.setTwitter(mensaje.isEsTwitter());
            bean.setFacebook(mensaje.isEsFacebook());
            bean.setGoogle(mensaje.isEsGoogle());

            ultimosMensajes.add(bean);
        }

        return respuesta;
    }

    public boolean publicarMensaje(int idUsuario, String mensaje, boolean toTwitter, boolean toFacebook, boolean toGoogle, long timestamp, String hash) {

        new ValidadorLlamadas().validarLlamada(timestamp, hash, idUsuario, mensaje, toTwitter, toFacebook, toGoogle);
        
        ConfiguracionesRedesBean configuraciones = new ConfiguracionesRedesBean();

        UserInfoRedDAO daoRedes = new UserInfoRedDAO();

        if (toTwitter) {
            configuraciones.setCodigosTwitter(daoRedes.obtenerTokensTwitter(idUsuario));
        }
        if (toFacebook) {
            //TODO
        }
        if (toGoogle) {
            //TODO
        }

        ClienteGestorRedesWS ws = new ClienteGestorRedesWS();
        ResponseMensajeBean respuesta = ws.publicarMensaje(mensaje, configuraciones);
        
        boolean enviadoTwitter = respuesta.isTwitterOk();
        boolean enviadoFacebook = respuesta.isFacebookOk();
        boolean enviadoGoogle = respuesta.isGoogleOk();
        
        MensajeDAO mensajeDAO = new MensajeDAO();
        
        int idMensaje = mensajeDAO.insertarMensaje(idUsuario, mensaje);
        
        EnvioMensARedDAO redesDAO = new EnvioMensARedDAO();
        
        redesDAO.setearRedesAMensaje(idMensaje, enviadoTwitter, enviadoFacebook, enviadoGoogle);

        return enviadoTwitter || enviadoFacebook || enviadoGoogle;
    }

    public ResponseVincularTwitterStep1Bean vincularTwitterStep1(int idUsuario, long timestamp, String hash) {

        new ValidadorLlamadas().validarLlamada(timestamp, hash, idUsuario);
        
        ClienteGestorRedesWS ws = new ClienteGestorRedesWS();
        
        ResponseURLVinculacionTwitterBean datosTemporalesTwitter = ws.obtenerURLVinculacionTwitter();
        
        UserInfoRedDAO userInfoDAO = new UserInfoRedDAO();
        
        userInfoDAO.insertarActualizarTokensTemporalesTwitter(idUsuario, datosTemporalesTwitter.getToken(), datosTemporalesTwitter.getTokenSecret());
        
        ResponseVincularTwitterStep1Bean respuesta = new ResponseVincularTwitterStep1Bean();
        
        respuesta.setUrl(datosTemporalesTwitter.getUrl());
        
        return respuesta;
    }

    public boolean vincularTwitterStep2(int idUsuario, String clave, long timestamp, String hash) {

        new ValidadorLlamadas().validarLlamada(timestamp, hash, idUsuario, clave);
        
        UserInfoRedDAO userInfoDAO = new UserInfoRedDAO();
        
        CodigosTwitterBean configuracionTwitterTemporal = userInfoDAO.obtenerTokensTemporalesTwitter(idUsuario);
        
        ClienteGestorRedesWS ws = new ClienteGestorRedesWS();
        
        TokenFinalTwitterBean informacionTemporal = new TokenFinalTwitterBean();
        
        informacionTemporal.setToken(configuracionTwitterTemporal.getToken());
        informacionTemporal.setTokenSecret(configuracionTwitterTemporal.getTokenSecret());
        informacionTemporal.setKey(clave);
        
        ResponseTokenFinalTwitterBean tokensFinales = ws.obtenerTokenFinalTwitter(informacionTemporal);
        
        userInfoDAO.insertarActualizarTokensFinalesTwitter(idUsuario, tokensFinales.getToken(), tokensFinales.getTokenSecret());
        
        return true;
    }
}
