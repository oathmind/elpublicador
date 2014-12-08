package com.soaandjoe.gestoraplicacion.dao;

import com.soaandjoe.gestoraplicacion.entity.Mensaje;
import com.soaandjoe.gestoraplicacion.entity.RedSocial;
import com.soaandjoe.gestoraplicacion.persistencia.Conexion;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joel
 */
public class MensajeDAO {

    public List<Mensaje> obtenerMensajesUsuario(Integer idUsuario) {
        ArrayList<Mensaje> mensajes = new ArrayList<Mensaje>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = Conexion.getConnexio();

            ps = c.prepareStatement("select id_mensaje, mensaje from hist_mensaje where id_usuario = ?");
            ps.setInt(1, idUsuario);

            rs = ps.executeQuery();

            while (rs.next()) {
                Mensaje tmpMensaje = new Mensaje();

                tmpMensaje.setIdUsuario(idUsuario);
                tmpMensaje.setIdMensaje(rs.getInt("id_mensaje"));
                tmpMensaje.setMensaje(rs.getString("mensaje"));
                rellenarMensajeConRedes(tmpMensaje);
                mensajes.add(tmpMensaje);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MensajeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.finalizarRecursos(rs, ps, c);
        }
        return mensajes;
    }

    public Mensaje rellenarMensajeConRedes(Mensaje mensaje) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = Conexion.getConnexio();

            ps = c.prepareStatement("select id_red from envio_mens_a_red where id_mensaje = ?");
            ps.setInt(1, mensaje.getIdMensaje());

            rs = ps.executeQuery();

            while (rs.next()) {
                RedSocial red = RedSocial.getRedSocialById(rs.getInt("id_red"));
                mensaje.marcarRedSocial(red);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MensajeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.finalizarRecursos(rs, ps, c);
        }
        return mensaje;
    }
}
