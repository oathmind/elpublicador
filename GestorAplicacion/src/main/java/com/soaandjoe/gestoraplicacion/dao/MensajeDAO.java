package com.soaandjoe.gestoraplicacion.dao;

import com.soaandjoe.gestoraplicacion.entity.Mensaje;
import com.soaandjoe.gestoraplicacion.persistencia.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joel
 */
public class MensajeDAO {

    public int insertarMensaje(Integer idUsuario, String mensaje) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int idMensaje = -1;
        try {
            c = Conexion.getConnexio();
            
            c.setAutoCommit(false);

            ps = c.prepareStatement("insert into hist_mensaje (id_usuario, id_mensaje, mensaje) values (?, (select IFNULL(max(us.id_mensaje),0) + 1 from  hist_mensaje us), ?)");
            ps.setInt(1, idUsuario);
            ps.setString(2, mensaje);

            ps.executeUpdate();
            
            Conexion.finalizarRecursos(ps);
            
            ps = c.prepareStatement("select max(id_mensaje) as valor from  hist_mensaje");

            rs = ps.executeQuery();

            if (rs.next()) {
                idMensaje = rs.getInt("valor");
            }
            c.commit();

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.finalizarRecursos(rs, ps, c);
        }
        return idMensaje;
    }

    public List<Mensaje> obtenerMensajesUsuario(Integer idUsuario) {
        ArrayList<Mensaje> mensajes = new ArrayList<Mensaje>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = Conexion.getConnexio();

            ps = c.prepareStatement("select id_mensaje, mensaje, fecha from hist_mensaje where id_usuario = ? order by fecha desc");
            ps.setInt(1, idUsuario);

            rs = ps.executeQuery();

            while (rs.next()) {
                Mensaje tmpMensaje = new Mensaje();

                tmpMensaje.setIdUsuario(idUsuario);
                tmpMensaje.setIdMensaje(rs.getInt("id_mensaje"));
                tmpMensaje.setMensaje(rs.getString("mensaje"));
                Timestamp timestamp = rs.getTimestamp("fecha");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                //CHAPU PARA SOLUCIONAR EL TEMA DE LOS TIEMPOS
                tmpMensaje.setFecha(sdf.format(new Date(timestamp.getTime() + 21600000)));
                new EnvioMensARedDAO().rellenarMensajeConRedes(tmpMensaje);
                mensajes.add(tmpMensaje);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MensajeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.finalizarRecursos(rs, ps, c);
        }
        return mensajes;
    }
}
