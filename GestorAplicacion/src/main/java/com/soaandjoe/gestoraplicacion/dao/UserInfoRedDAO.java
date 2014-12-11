package com.soaandjoe.gestoraplicacion.dao;

import com.soaandjoe.gestoraplicacion.clienteRedes.CodigosTwitterBean;
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
public class UserInfoRedDAO {
    
     public CodigosTwitterBean obtenerTokensTwitter(int idUsuario) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        CodigosTwitterBean codigos = null;
        try {
            c = Conexion.getConnexio();

            ps = c.prepareStatement("select id_token, id_token_privado from user_info_red where id_usuario = ? and id_red = ?");
            ps.setInt(1, idUsuario);
            ps.setInt(2, RedSocial.TWITTER.getIdRedSocial());

            rs = ps.executeQuery();

            while (rs.next()) {
                codigos = new CodigosTwitterBean();
                codigos.setToken(rs.getString("id_token"));
                codigos.setTokenSecret(rs.getString("id_token_privado"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(MensajeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.finalizarRecursos(rs, ps, c);
        }
        return codigos;
    }

    public boolean estaVinculadoTwitter(int idUsuario) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean vinculado = false;
        try {
            c = Conexion.getConnexio();

            ps = c.prepareStatement("select id_token from user_info_red where id_usuario = ? and id_red = ?");
            ps.setInt(1, idUsuario);
            ps.setInt(2, RedSocial.TWITTER.getIdRedSocial());

            rs = ps.executeQuery();

            while (rs.next()) {
                vinculado = rs.getString("id_token") != null && !rs.getString("id_token").trim().equals("");
            }

        } catch (SQLException ex) {
            Logger.getLogger(MensajeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.finalizarRecursos(rs, ps, c);
        }
        return vinculado;
    }

    public boolean estaVinculadoFacebook(int idUsuario) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean vinculado = false;
        try {
            c = Conexion.getConnexio();

            ps = c.prepareStatement("select id_token from user_info_red where id_usuario = ? and id_red = ?");
            ps.setInt(1, idUsuario);
            ps.setInt(2, RedSocial.FACEBOOK.getIdRedSocial());

            rs = ps.executeQuery();

            while (rs.next()) {
                vinculado = rs.getString("id_token") != null && !rs.getString("id_token").trim().equals("");
            }

        } catch (SQLException ex) {
            Logger.getLogger(MensajeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.finalizarRecursos(rs, ps, c);
        }
        return vinculado;
    }

    public boolean estaVinculadoGoogle(int idUsuario) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean vinculado = false;
        try {
            c = Conexion.getConnexio();

            ps = c.prepareStatement("select id_token from user_info_red where id_usuario = ? and id_red = ?");
            ps.setInt(1, idUsuario);
            ps.setInt(2, RedSocial.GOOGLE.getIdRedSocial());

            rs = ps.executeQuery();

            while (rs.next()) {
                vinculado = rs.getString("id_token") != null && !rs.getString("id_token").trim().equals("");
            }

        } catch (SQLException ex) {
            Logger.getLogger(MensajeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.finalizarRecursos(rs, ps, c);
        }
        return vinculado;
    }
}
