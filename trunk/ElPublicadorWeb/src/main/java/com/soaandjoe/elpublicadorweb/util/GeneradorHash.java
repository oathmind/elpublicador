package com.soaandjoe.elpublicadorweb.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joel
 */
public class GeneradorHash {

    private static final String SECRETO = "b5a3c87949f4cd44ceef2bd4ef057538";

    public String generarHash(String nombreMetodo, Long timestamp, Object... parametros) throws IllegalStateException {
        StringBuilder preparadoHash = new StringBuilder(nombreMetodo);
        for (Object object : parametros) {
            preparadoHash.append("/").append(object.toString());
        }
        preparadoHash.append("/").append(timestamp.toString());
        preparadoHash.append("/").append(SECRETO);
        return toMd5(preparadoHash.toString());
    }

    private String toMd5(String elemento) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(elemento.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String hashtext = bigInt.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(GeneradorHash.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
