package com.soaandjoe.gestoraplicacion.entity;

/**
 *
 * @author andres
 */
public class Mensaje {

    private Integer idMensaje;
    private Integer idUsuario;
    private String mensaje;
    private boolean esTwitter;
    private boolean esFacebook;
    private boolean esGoogle;

    public Integer getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(Integer idMensaje) {
        this.idMensaje = idMensaje;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isEsTwitter() {
        return esTwitter;
    }

    public void setEsTwitter(boolean esTwitter) {
        this.esTwitter = esTwitter;
    }

    public boolean isEsFacebook() {
        return esFacebook;
    }

    public void setEsFacebook(boolean esFacebook) {
        this.esFacebook = esFacebook;
    }

    public boolean isEsGoogle() {
        return esGoogle;
    }

    public void setEsGoogle(boolean esGoogle) {
        this.esGoogle = esGoogle;
    }

    public void marcarRedSocial(RedSocial red) {
        if (red.equals(RedSocial.TWITTER)) {
            esTwitter = true;
        }
        if (red.equals(RedSocial.FACEBOOK)) {
            esFacebook = true;
        }
        if (red.equals(RedSocial.GOOGLE)) {
            esGoogle = true;
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + (this.idMensaje != null ? this.idMensaje.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mensaje other = (Mensaje) obj;
        if (this.idMensaje != other.idMensaje && (this.idMensaje == null || !this.idMensaje.equals(other.idMensaje))) {
            return false;
        }
        return true;
    }

}
