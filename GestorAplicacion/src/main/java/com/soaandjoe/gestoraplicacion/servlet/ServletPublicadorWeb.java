/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soaandjoe.gestoraplicacion.servlet;

import com.soaandjoe.gestoraplicacion.gestoras.UserGestor;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author andres
 */
public class ServletPublicadorWeb extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-store");
        String url = request.getRequestURI();
        String accio = "";
        if (url.indexOf(".publicador") != -1) {
            accio = url.substring(url.lastIndexOf("/") + 1, url.indexOf(".publicador"));
        }
        HttpSession sesio = request.getSession();
        String idUsuarioConectado  = (String) sesio.getAttribute("usuario");

        
        
        RequestDispatcher dispatch = request.getRequestDispatcher("index.jsp");

        if (accio.equals("registrar")) {
            //1 recuperar parametros
            //2 validacion sintactica i de tipos
            //EJEMPLO, password == passwordRep, mail es un correo
            //3 enciar a controladora con los datos"validados"
            //4 retornar a la JSP lo que toque
            request.getParameter("usuario");
            request.getParameter("password");
            request.getParameter("passwordRep");
            request.getParameter("mail");
            UserGestor userGestor = new UserGestor();
//            userGestor.registrar();
            request.setAttribute("parametro1", "valor1");
            dispatch = request.getRequestDispatcher("index.jsp");
        } else if (accio.equals("identificarse")) {
            
            sesio.setAttribute("usuario", null/*idUsuarioRecuperado de base de datos*/);
            dispatch = request.getRequestDispatcher("index.jsp");
        }

        dispatch.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
