package com.soaandjoe.gestoraplicacion.persistencia;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author jmol
 */
public class Conexion {

    public static Connection getConnexio() throws NamingException, SQLException {
        Connection con = null;
        Context ic = new InitialContext();
        DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/elPublicador");
        con = ds.getConnection();
        return con;
    }
}
