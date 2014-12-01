package com.soaandjoe.twitter;

/**
 *
 * @author Joel
 */
public class UrlKey {

    private String url;
    private String token;
    private String tokenSecret;

    public UrlKey(String url, String token, String tokenSecret) {
        this.url = url;
        this.token = token;
        this.tokenSecret = tokenSecret;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
}
