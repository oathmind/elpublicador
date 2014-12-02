package com.soaandjoe.twitter;

/**
 *
 * @author Joel
 */
public class UrlKeyUsuario {

    private String token;
    private String tokenSecret;
    private String key;

    public UrlKeyUsuario(String token, String tokenSecret, String key) {
        this.token = token;
        this.tokenSecret = tokenSecret;
        this.key = key;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenSecret() {
        return tokenSecret;
    }

    public void setTokenSecret(String tokenSecret) {
        this.tokenSecret = tokenSecret;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
