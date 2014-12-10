package com.soaandjoe.gestoraplicacion.dao;

import com.soaandjoe.gestoraplicacion.entity.User;
import com.soaandjoe.gestoraplicacion.gestoraplicacionws.ValidadorLlamadas;
import com.soaandjoe.gestoraplicacion.persistencia.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andres
 */
public class UserDAO {

    public int obtenerIdUsuario(String email, String password) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int idUsuario = -1;
        try {
            c = Conexion.getConnexio();

            String passwordEncriptado = new ValidadorLlamadas().toMd5(password);

            ps = c.prepareStatement("select id_usuario from user where email = ? and password = ?");
            ps.setString(1, email);
            ps.setString(2, passwordEncriptado);

            rs = ps.executeQuery();

            if (rs.next()) {
                idUsuario = rs.getInt("id_usuario");
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.finalizarRecursos(rs, ps, c);
        }
        return idUsuario;
    }

    public boolean insertarUsuario(String email, String nombre, String password) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int filasAfectadas = 0;
        try {
            c = Conexion.getConnexio();

            String passwordEncriptado = new ValidadorLlamadas().toMd5(password);

            ps = c.prepareStatement("insert into user (id_usuario, email, nombre, password) values ((select max(us.id_usuario) + 1 from user us), ?, ?, ?)");
            ps.setString(1, email);
            ps.setString(2, nombre);
            ps.setString(3, passwordEncriptado);

            filasAfectadas = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.finalizarRecursos(rs, ps, c);
        }
        return filasAfectadas != 0;
    }

    public User obtenerUsuarioPorId(int idUsuario) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User usuario = null;
        try {
            c = Conexion.getConnexio();

            ps = c.prepareStatement("select email, nombre from user where id_usuario = ?");
            ps.setInt(1, idUsuario);

            rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new User();
                usuario.setEmail(rs.getString("email"));
                usuario.setNombre(rs.getString("nombre"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.finalizarRecursos(rs, ps, c);
        }
        return usuario;
    }
}
