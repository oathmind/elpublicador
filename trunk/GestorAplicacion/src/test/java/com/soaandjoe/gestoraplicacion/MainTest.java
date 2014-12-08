/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soaandjoe.gestoraplicacion;

import com.soaandjoe.gestoraplicacion.persistencia.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author andres
 */
public class MainTest {
    
    public static void main(String args[]){
        try {
            Connection con = Conexion.getConnexio();
            Statement s = con.createStatement();
 
                    ResultSet rs= s.executeQuery("select nombre from user;");
                    String nombre = (String)rs.getObject("nombre");
                    System.out.println(nombre);
                    s.close();
                    con.close();
        } catch (SQLException ex) {
            Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
