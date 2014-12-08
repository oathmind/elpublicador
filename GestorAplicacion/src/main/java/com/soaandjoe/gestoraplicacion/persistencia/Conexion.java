package com.soaandjoe.gestoraplicacion.persistencia;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author jmol
 */
public class Conexion {

    public static synchronized Connection getConnexio() {
        try {
            Context ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/elPublicador");
            Connection con = ds.getConnection();
            return con;
        } catch (NamingException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void finalizarRecursos(AutoCloseable... recurso) {
        for (AutoCloseable autoCloseable : recurso) {
            if (autoCloseable != null) {
                try {
                    autoCloseable.close();
                } catch (Exception ex) {
                }
            }
        }
    }
}
