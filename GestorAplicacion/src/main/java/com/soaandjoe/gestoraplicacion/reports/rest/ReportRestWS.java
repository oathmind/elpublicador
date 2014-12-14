/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soaandjoe.gestoraplicacion.reports.rest;

import com.soaandjoe.gestoraplicacion.dao.EnvioMensARedDAO;
import com.soaandjoe.gestoraplicacion.dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author andres
 */
public class ReportRestWS extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        EnvioMensARedDAO mensDAO = new EnvioMensARedDAO();
        int numUser = userDAO.obtenerNumeroUsuariosRegistrados();
        int numMensajesEnviados = mensDAO.obtenerNumeroMensajesEnviados();
        Source src = crearXml(numUser,numMensajesEnviados);
        
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("text/xml");
        StreamResult strRes = new StreamResult(out);
        try {
            TransformerFactory.newInstance().newTransformer()
                    .transform(src, strRes);
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
        out.close();
    }
    
    public Source crearXml(int numUser,int numMensajesEnv) {
        ArrayList key = new ArrayList();
        ArrayList value = new ArrayList();

        key.add("numUser");
        value.add(numUser);

        key.add("numMensaEnviados");
        value.add(numMensajesEnv);

        try { 
            return generate( key, value);
        } catch (Exception e) {return null;}
        
    }

    public Source generate( ArrayList<String> key, ArrayList<String> value) throws Exception {

        if (key.isEmpty() || value.isEmpty() || key.size() != value.size()) {
            System.out.println("ERROR empty ArrayList");
            return null;
        } else {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, "reports", null);
            document.setXmlVersion("1.0");

            //Main Node
            Element raiz = document.getDocumentElement();
            //Por cada key creamos un item que contendrá la key y el value
            for (int i = 0; i < key.size(); i++) {
                //Item Node
                Element itemNode = document.createElement("ITEM");
                //Key Node
                Element keyNode = document.createElement("KEY");
                Text nodeKeyValue = document.createTextNode(key.get(i));
                keyNode.appendChild(nodeKeyValue);
                //Value Node
                Element valueNode = document.createElement("VALUE");
                Text nodeValueValue = document.createTextNode(String.valueOf(value.get(i)));
                valueNode.appendChild(nodeValueValue);
                //append keyNode and valueNode to itemNode
                itemNode.appendChild(keyNode);
                itemNode.appendChild(valueNode);
                //append itemNode to raiz
                raiz.appendChild(itemNode); //pegamos el elemento a la raiz "Documento"
            }
            //Generate XML
            Source source = new DOMSource(document);

            return source;
        }
        
    }
}
