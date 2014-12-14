/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soaandjoe.clienterest;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author andres
 */
public class WSRestClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        URL url = new URL("http://localhost:8080/GestorAplicacion/ReportRestWS");
        HttpURLConnection con
                = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.connect();

        InputStream in = con.getInputStream();
        byte[] b = new byte[1024];
        int result = in.read(b);
        while (result != -1) {
            System.out.write(b, 0, result);
            result = in.read(b);
        }
        in.close();
        con.disconnect();
    }

}
