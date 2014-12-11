/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soaandjoe.gestoraplicacion.dao;

import com.soaandjoe.gestoraplicacion.entity.Mensaje;
import com.soaandjoe.gestoraplicacion.entity.RedSocial;
import com.soaandjoe.gestoraplicacion.persistencia.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joel
 */
public class EnvioMensARedDAO {

    public void setearRedesAMensaje(int idMensaje, boolean toTwitter, boolean toFacebook, boolean toGoogle) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = Conexion.getConnexio();

            ps = c.prepareStatement("insert into envio_mens_a_red (id_mensaje, id_red) values (?, ?)");
            if (toTwitter) {
                ps.setInt(1, idMensaje);
                ps.setInt(2, RedSocial.TWITTER.getIdRedSocial());
                ps.addBatch();
            }
            if (toFacebook) {
                ps.setInt(1, idMensaje);
                ps.setInt(2, RedSocial.FACEBOOK.getIdRedSocial());
                ps.addBatch();
            }
            if (toGoogle) {
                ps.setInt(1, idMensaje);
                ps.setInt(2, RedSocial.GOOGLE.getIdRedSocial());
                ps.addBatch();
            }

            ps.executeBatch();

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.finalizarRecursos(rs, ps, c);
        }
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
