package com.soaandjoe.gestoredes;

import com.soaandjoe.twitter.TwitterAppKeys;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Joel
 */
public class UtilGestorRedes {

    public TwitterAppKeys obtenerClavesTwitter() {
        File fitxerClausTwitter;
        try {
            //La ruta tiene que estar en el classpath
            fitxerClausTwitter = new File(Thread.currentThread().getContextClassLoader().getResource("twitter.xml").toURI());
        } catch (URISyntaxException ex) {
            Logger.getLogger(UtilGestorRedes.class.getName()).log(Level.SEVERE, null, ex);
            return new TwitterAppKeys("V1IDcXJiN5vqYmebrHc802qj1", "GrqKRyI8hVZMVESPqWxYfEyxCQJLK89McRVkS1FZXkqFYcFmiS");
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document documento = null;
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            documento = builder.parse(fitxerClausTwitter);
        } catch (SAXException ex){
            Logger.getLogger(UtilGestorRedes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex){
            Logger.getLogger(UtilGestorRedes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex){
            Logger.getLogger(UtilGestorRedes.class.getName()).log(Level.SEVERE, null, ex);
        }

        String clavePublica = null;
        String clavePrivada = null;
        if (documento != null) {

            NodeList credendialesNodeList = documento.getElementsByTagName("credenciales");
            Node credencialesNode = credendialesNodeList.item(0);
            NodeList clavesNodeList = credencialesNode.getChildNodes();

            for (int i = 0; i < clavesNodeList.getLength(); i++) {
                Node element = clavesNodeList.item(i);
                if (element.getNodeType() != Element.TEXT_NODE) {
                    String nombre = element.getNodeName();
                    if(nombre.equalsIgnoreCase("clavePublica")){
                        clavePublica = element.getFirstChild().getNodeValue();
                    }else if(nombre.equalsIgnoreCase("clavePrivada")){
                        clavePrivada = element.getFirstChild().getNodeValue();
                    }
                }
            }

        }
        return new TwitterAppKeys(clavePublica, clavePrivada);
    }
}
