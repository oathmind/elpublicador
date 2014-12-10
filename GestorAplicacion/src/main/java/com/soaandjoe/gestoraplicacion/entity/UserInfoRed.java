package com.soaandjoe.gestoraplicacion.entity;

/**
 *
 * @author andres
 */
public class UserInfoRed {

    private int idUsuario;
    private RedSocial redSocial;
    private String token;
    private String tokenTemporal;
    private String tokenPrivado;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public RedSocial getRedSocial() {
        return redSocial;
    }

    public void setRedSocial(RedSocial redSocial) {
        this.redSocial = redSocial;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenTemporal() {
        return tokenTemporal;
    }

    public void setTokenTemporal(String tokenTemporal) {
        this.tokenTemporal = tokenTemporal;
    }

    public String getTokenPrivado() {
        return tokenPrivado;
    }

    public void setTokenPrivado(String tokenPrivado) {
        this.tokenPrivado = tokenPrivado;
    }

}
