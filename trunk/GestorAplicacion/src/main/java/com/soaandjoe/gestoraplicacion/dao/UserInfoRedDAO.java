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

    public boolean insertarActualizarTokensTemporalesTwitter(int idUsuario, String token, String tokenPrivado) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int filasAfectadas = 0;
        try {
            boolean modoInsert = true;
            if (obtenerTokensTemporalesTwitter(idUsuario) != null) {
                modoInsert = false;
            }

            c = Conexion.getConnexio();

            if (modoInsert) {
                ps = c.prepareStatement("insert into user_info_red (id_usuario, id_red, id_token, id_token_temporal, id_token_privado) values (?, ?, NULL, ?, ?)");
                ps.setInt(1, idUsuario);
                ps.setInt(2, RedSocial.TWITTER.getIdRedSocial());
                ps.setString(3, token);
                ps.setString(4, tokenPrivado);
            } else {
                ps = c.prepareStatement("update user_info_red set id_token = NULL, id_token_temporal = ?, id_token_privado= ? where id_usuario = ? and id_red = ?");
                ps.setString(1, token);
                ps.setString(2, tokenPrivado);
                ps.setInt(3, idUsuario);
                ps.setInt(4, RedSocial.TWITTER.getIdRedSocial());
            }

            filasAfectadas = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.finalizarRecursos(rs, ps, c);
        }
        return filasAfectadas != 0;
    }

    public boolean insertarActualizarTokensFinalesTwitter(int idUsuario, String token, String tokenPrivado) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int filasAfectadas = 0;
        try {
            boolean modoInsert = true;
            if (obtenerTokensTwitter(idUsuario) != null) {
                modoInsert = false;
            }
            c = Conexion.getConnexio();

            if (modoInsert) {
                ps = c.prepareStatement("insert into  user_info_red (id_usuario, id_red, id_token, id_token_temporal, id_token_privado) values (?, ?, ?, NULL, ?)");
                ps.setInt(1, idUsuario);
                ps.setInt(2, RedSocial.TWITTER.getIdRedSocial());
                ps.setString(3, token);
                ps.setString(4, tokenPrivado);
            } else {
                ps = c.prepareStatement("update user_info_red set id_token = ?, id_token_temporal = NULL, id_token_privado= ? where id_usuario = ? and id_red = ?");
                ps.setString(1, token);
                ps.setString(2, tokenPrivado);
                ps.setInt(3, idUsuario);
                ps.setInt(4, RedSocial.TWITTER.getIdRedSocial());
            }

            filasAfectadas = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.finalizarRecursos(rs, ps, c);
        }
        return filasAfectadas != 0;
    }

    public CodigosTwitterBean obtenerTokensTemporalesTwitter(int idUsuario) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        CodigosTwitterBean codigos = null;
        try {
            c = Conexion.getConnexio();

            ps = c.prepareStatement("select id_token_temporal, id_token_privado from user_info_red where id_usuario = ? and id_red = ?");
            ps.setInt(1, idUsuario);
            ps.setInt(2, RedSocial.TWITTER.getIdRedSocial());

            rs = ps.executeQuery();

            if (rs.next()) {
                codigos = new CodigosTwitterBean();
                codigos.setToken(rs.getString("id_token_temporal"));
                codigos.setTokenSecret(rs.getString("id_token_privado"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(MensajeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.finalizarRecursos(rs, ps, c);
        }
        return codigos;
    }

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

            if (rs.next()) {
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
