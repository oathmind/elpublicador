package com.soaandjoe.twitter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

/**
 *
 * @author Joel
 */
public class TwitterUtil {

    public TwitterAppKeys clavesApp;

    /**
     * Crea un objeto Para la gestion de twitter
     * @param clavesApp las claves de nuestra aplicacion
     */
    public TwitterUtil(TwitterAppKeys clavesApp) {
        this.clavesApp = clavesApp;
    }

    /**
     * Obtiene una nueva URL para vincular una cuenta
     *
     * @return Devuelve la URL que tendra que introducir el usuario, mas dos
     * tokens temporales para hacer la vinculación mas adelante.
     */
    public UrlKey obtenerUrlParaKeyUsuario() {
        try {
            //Instantiate a re-usable and thread-safe factory
            TwitterFactory twitterFactory = new TwitterFactory();

            //Instantiate a new Twitter instance
            Twitter twitter = twitterFactory.getInstance();

            //setup OAuth Consumer Credentials
            twitter.setOAuthConsumer(clavesApp.getConsumerKey(), clavesApp.getConsumerSecret());
            RequestToken requestToken = twitter.getOAuthRequestToken();

            return new UrlKey(requestToken.getAuthorizationURL(), requestToken.getToken(), requestToken.getTokenSecret());
        } catch (TwitterException ex) {
            Logger.getLogger(TwitterUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Obtiene los tokens definitivos de un usuario despues de que este
     * introduzca la clave de autorizacion de la aplicacion
     *
     * @param claves Los tokens temporales del usuario mas la clave de
     * vinculación
     * @return Los tokens permanentes de este usuario
     */
    public TwitterUserTokens obtenerTokensUsuario(UrlKeyUsuario claves) {
        try {
            //Instantiate a re-usable and thread-safe factory
            TwitterFactory twitterFactory = new TwitterFactory();

            //Instantiate a new Twitter instance
            Twitter twitter = twitterFactory.getInstance();

            //setup OAuth Consumer Credentials
            twitter.setOAuthConsumer(clavesApp.getConsumerKey(), clavesApp.getConsumerSecret());

            RequestToken requestToken = new RequestToken(claves.getToken(), claves.getTokenSecret());
            AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, claves.getKey());
            return new TwitterUserTokens(accessToken.getToken(), accessToken.getTokenSecret());
        } catch (TwitterException ex) {
            Logger.getLogger(TwitterUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Publica un mensaje en twitter
     *
     * @param mensaje El mensaje a publicar
     * @param tokens Los tokens del usuario a donde vamos a publicar
     * @return si se ha publicado el mensaje o no
     */
    public boolean publicarTwitter(String mensaje, TwitterUserTokens tokens) {
        try {
            //Instantiate a re-usable and thread-safe factory
            TwitterFactory twitterFactory = new TwitterFactory();

            //Instantiate a new Twitter instance
            Twitter twitter = twitterFactory.getInstance();

            //setup OAuth Consumer Credentials
            twitter.setOAuthConsumer(clavesApp.getConsumerKey(), clavesApp.getConsumerSecret());

            twitter.setOAuthAccessToken(new AccessToken(tokens.getToken(), tokens.getTokenSecret()));

            //tweet or update status
            Status status = twitter.updateStatus(mensaje);
            return status != null;
        } catch (TwitterException ex) {
            Logger.getLogger(TwitterUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Main para debug, hay un poco de poti poti pero esta todo mas o menos
     *
     * @param args
     * @throws IOException
     * @throws TwitterException
     */
    public static void main(String[] args) throws IOException, TwitterException {
        TwitterAppKeys claves = new TwitterAppKeys("V1IDcXJiN5vqYmebrHc802qj1", "GrqKRyI8hVZMVESPqWxYfEyxCQJLK89McRVkS1FZXkqFYcFmiS");
        TwitterUtil tt = new TwitterUtil(claves);



        //1
        UrlKey obtenerUrlParaKeyUsuario = tt.obtenerUrlParaKeyUsuario();

        System.out.println(obtenerUrlParaKeyUsuario.getUrl());
        System.out.println(obtenerUrlParaKeyUsuario.getToken());
        System.out.println(obtenerUrlParaKeyUsuario.getTokenSecret());
        //2
//        UrlKeyUsuario key = new UrlKeyUsuario("DZi41f01aiWagr96t6wIBOBPSAXqzzDn", "fwLEVo2JC1H0RddsIp2LQOAAutAXwyAn", "7205940");
//        
//        TwitterUserTokens obtenerTokensUsuario = tt.obtenerTokensUsuario(key);
//        
//        System.out.println(obtenerTokensUsuario.getToken());
//        System.out.println(obtenerTokensUsuario.getTokenSecret());
        //3
//        TwitterUserTokens usuario = new TwitterUserTokens("435020388-IlOQfVlNz8PiAp5TwnX5vyA1CTN0YkR3Jpvr9yiQ", "SX6ukaAHpvWLUQqMDqIpBO42EH3mPyWybRBk1NhSIblY9");
//        
//        tt.publicarTwitter("alo?", usuario);
//        https://api.twitter.com/oauth/authorize?oauth_token=tIlKyegqWojKduv7kTYnGI85j81zmyhz
//tIlKyegqWojKduv7kTYnGI85j81zmyhz
//2cISaTLwIO84sMkOiTbTDNzVVOgpuX3i
//         //Your Twitter App's Consumer Key
//         String consumerKey = "V1IDcXJiN5vqYmebrHc802qj1";
//
//         //Your Twitter App's Consumer Secret
//         String consumerSecret = "GrqKRyI8hVZMVESPqWxYfEyxCQJLK89McRVkS1FZXkqFYcFmiS";
//
//         /* JOEL        
//         //Your Twitter Access Token
//         String Token = "utsWTDSFvQoryfqFBRnuFMb9niXSWvDT";
//
//         //Your Twitter Access Token Secret
//         String TokenSecret = "sM5dy8AactjBJCGIjraEadr8G0UHbRHm";
//
//         String key = "0391225";
//         */
//        //Anna
////Your Twitter Access Token
//        String Token = "DZi41f01aiWagr96t6wIBOBPSAXqzzDn";
//
//        //Your Twitter Access Token Secret
//        String TokenSecret = "fwLEVo2JC1H0RddsIp2LQOAAutAXwyAn";
//
//        String key = "7205940";
//
//        //Instantiate a re-usable and thread-safe factory
//        TwitterFactory twitterFactory = new TwitterFactory();
//
//        //Instantiate a new Twitter instance
//        Twitter twitter = twitterFactory.getInstance();
//
//        //setup OAuth Consumer Credentials
//        twitter.setOAuthConsumer(consumerKey, consumerSecret);
//
//        //PRIMERO
////        RequestToken requestToken = twitter.getOAuthRequestToken();
////        AccessToken accessToken = null;
////
////        System.out.println("Open the following URL and grant access to your account:");
////        System.out.println(requestToken.getAuthorizationURL());
//        //DESPUES
//                RequestToken requestToken = new RequestToken(Token, TokenSecret);
//        AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, key);
//        //FINAL
////        String TokenReal = "435020388-ZrJ8AqR0P7qOJGkAiONbW5qKJXJrT3ID17EDfddt";
////
////        //Your Twitter Access Token Secret
////        String TokenSecretReal = "xwUr6DdqJyqNidN5Jzo43krTUov5PsyPZmUnzTEDaTd0h";
////
////        //setup OAuth Access Token
//        twitter.setOAuthAccessToken(new AccessToken(accessToken.getToken(), accessToken.getTokenSecret()));
////        twitter.setOAuthAccessToken(new AccessToken(TokenReal, TokenSecretReal));
////
////        //tweet or update status
//        Status status2 = twitter.updateStatus("Testeando aplicaciones para twitter ola k ase ? viciando al tera o k ase?(NI CASO A ESTO!)");
    }
}
