package com.soaandjoe.elpublicadorweb.servlet;

import java.io.IOException;
import java.util.HashMap;
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
public class ServletPrincipal extends HttpServlet {

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
        if (url.contains(".publicador")) {
            accio = url.substring(url.lastIndexOf("/") + 1, url.indexOf(".publicador"));

            HttpSession sesio = request.getSession();
            String idUsuarioConectado = (String) sesio.getAttribute("usuario");

            RequestDispatcher dispatch = request.getRequestDispatcher("template.jsp");
            HashMap mapeo = new HashMap(2);

            if (accio.equals("inicio")) {
            //1 recuperar parametros
                //2 validacion sintactica i de tipos
                //EJEMPLO, password == passwordRep, mail es un correo
                //3 enciar a controladora con los datos"validados"
                //4 retornar a la JSP lo que toque
//            userGestor.registrar();
                mapeo.put("titulo", "El Publicador Web");
                mapeo.put("href", "inicio.jsp");
            } else if (accio.equals("login")) {

                mapeo.put("titulo", "El Publicador Web");
                mapeo.put("href", "inicio.jsp");
            }

            request.setAttribute("mapeo", mapeo);
            dispatch.forward(request, response);
        } else {
            response.sendRedirect("inicio.publicador");
        }
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
