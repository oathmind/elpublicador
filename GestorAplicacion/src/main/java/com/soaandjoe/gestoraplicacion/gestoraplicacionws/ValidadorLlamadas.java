package com.soaandjoe.gestoraplicacion.gestoraplicacionws;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joel
 */
public class ValidadorLlamadas {

    private static final String SECRETO = "b5a3c87949f4cd44ceef2bd4ef057538";
    private static final Integer VENTANA_TIEMPO = 300000;
    
    private static final Boolean DEBUG_MODE = true;

    /**
     * Este metodo debe llamarse solo entrar en el metodo del WS
     *
     * @param timestamp
     * @param hash
     * @param parametros
     * @return
     * @throws IllegalStateException
     */
    public boolean validarLlamada(Long timestamp, String hash, Object... parametros) throws IllegalStateException {
        if(DEBUG_MODE){
            return true;
        }
        if (!validarTiempoLlamada(timestamp)) {
            throw new IllegalStateException("Ventana de tiempo incorrecta");
        }
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        String nombreMetodoWS = stackTraceElements[2].getMethodName();
        StringBuilder preparadoHash = new StringBuilder(nombreMetodoWS);
        for (Object object : parametros) {
            preparadoHash.append("/").append(object.toString());
        }
        preparadoHash.append("/").append(timestamp.toString());
        preparadoHash.append("/").append(SECRETO);
        return hash.equals(toMd5(preparadoHash.toString()));
    }

    private boolean validarTiempoLlamada(Long timestamp) {
        return timestamp + VENTANA_TIEMPO > System.currentTimeMillis();
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
            Logger.getLogger(ValidadorLlamadas.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
