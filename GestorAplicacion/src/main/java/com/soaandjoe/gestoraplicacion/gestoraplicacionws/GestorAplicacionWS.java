/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soaandjoe.gestoraplicacion.gestoraplicacionws;

import com.soaandjoe.gestoraplicacion.MensajeBean;
import com.soaandjoe.gestoraplicacion.ResponseHistoricoMensajesBean;
import java.util.List;
import javax.jws.WebService;

/**
 *
 * @author Joel
 */
@WebService(serviceName = "gestorAplicacion", portName = "gestorAplicacionPort", endpointInterface = "com.soaandjoe.gestoraplicacion.GestorAplicacionWSCV", targetNamespace = "http://gestoraplicacion.soaandjoe.com/", wsdlLocation = "WEB-INF/wsdl/gestorAplicacion.wsdl")
public class GestorAplicacionWS {

    public ResponseHistoricoMensajesBean obtenerHistoricoMensajes(int idUsuario, long timestamp, String hash) {

        ResponseHistoricoMensajesBean respuestaMensajes = new ResponseHistoricoMensajesBean();

        List<MensajeBean> mensajes = respuestaMensajes.getMensajes();
        
        MensajeBean mensaje = new MensajeBean();
        mensaje.setIdUsuario(2);
        mensaje.setIdMensaje(1);
        mensaje.setMensaje("este es un mensaje de prueba");
        mensaje.setTwitter(true);
        
        mensajes.add(mensaje);
        mensajes.add(mensaje);

        System.out.println("lista" + mensajes);

        return respuestaMensajes;
    }

}
