package com.soaandjoe.elpublicadorweb.servlet;

import com.soaandjoe.elpublicadorweb.clienteWS.ClienteGestorAplicacionWS;
import com.soaandjoe.elpublicadorweb.clienteWS.MensajeBean;
import com.soaandjoe.elpublicadorweb.clienteWS.ResponseDashBoardBean;
import com.soaandjoe.elpublicadorweb.clienteWS.ResponseHistoricoMensajesBean;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
        if (url.contains(".publicador")) {
            String accio = url.substring(url.lastIndexOf("/") + 1, url.indexOf(".publicador"));

            HttpSession sesio = request.getSession();
            Integer idUsuarioConectado = (Integer) sesio.getAttribute("usuario");
            request.setAttribute("idUsuario", idUsuarioConectado);

            boolean redireccionActiva = false;
            RequestDispatcher dispatch = request.getRequestDispatcher("template.jsp");
            HashMap mapeo = new HashMap(2);

            ClienteGestorAplicacionWS ws = new ClienteGestorAplicacionWS();

            if (accio.equals("inicio")) {
                if (idUsuarioConectado != null) {
                    //TODO obtener datos dashboarg
                    ResponseDashBoardBean datosDashBoard = ws.obtenerDashBoard(idUsuarioConectado);

                    String nombreUsuario = datosDashBoard.getNombreUsuario();
                    List<MensajeBean> ultimosMensajes = datosDashBoard.getUltimosMensajes();
                    boolean vinculadoTwitter = datosDashBoard.isVinculadoTwitter();
                    boolean vinculadoFacebook = datosDashBoard.isVinculadoFacebook();
                    boolean vinculadoGoogle = datosDashBoard.isVinculadoGoogle();

                    request.setAttribute("nombreUsuario", nombreUsuario);
                    request.setAttribute("ultimosMensajes", ultimosMensajes);
                    request.setAttribute("vinculadoTwitter", vinculadoTwitter);
                    request.setAttribute("vinculadoFacebook", vinculadoFacebook);
                    request.setAttribute("vinculadoGoogle", vinculadoGoogle);

                    mapeo.put("titulo", "Mi panel personal");
                    mapeo.put("href", "dashboard.jsp");
                } else {
                    mapeo.put("titulo", "El Publicador Web");
                    mapeo.put("href", "inicio.jsp");
                }
            } else if (accio.equals("login")) {
                String email = request.getParameter("email");
                String password = request.getParameter("password");

                int idUsuario = -1;
                if (email != null && password != null) {
                    idUsuario = ws.identificarUsuario(email, password);
                }

                if (idUsuario != -1) {
                    //OK, redrigimos a inicio
                    sesio.setAttribute("usuario", idUsuario);
                    response.sendRedirect("inicio.publicador");
                    redireccionActiva = true;
                } else {
                    //Error volvemos a inicio
                    mapeo.put("titulo", "El Publicador Web");
                    mapeo.put("href", "inicio.jsp");
                    request.setAttribute("error", "Identificacion Incorrecta");
                }
            } else if (accio.equals("registrarse")) {
                String email = request.getParameter("email");
                String nombre = request.getParameter("nombre");
                String password = request.getParameter("password");
                String repassword = request.getParameter("repassword");

                if (email != null && nombre != null && password != null && repassword != null) {

                    if (password.equalsIgnoreCase(repassword)) {
                        int idUsuario = ws.registrarUsuario(email, password, nombre);
                        if (idUsuario != -1) {
                            //OK, redrigimos a inicio
                            sesio.setAttribute("usuario", idUsuario);
                            response.sendRedirect("inicio.publicador");
                            redireccionActiva = true;
                        } else {
                            //Error volvemos a inicio
                            mapeo.put("titulo", "El Publicador Web");
                            mapeo.put("href", "inicio.jsp");
                            request.setAttribute("error", "Registro Incorrecto");
                        }
                    } else {
                        mapeo.put("titulo", "El Publicador Web");
                        mapeo.put("href", "inicio.jsp");
                        request.setAttribute("error", "Los passwords no coinciden");
                    }
                } else {
                    mapeo.put("titulo", "El Publicador Web");
                    mapeo.put("href", "inicio.jsp");
                    request.setAttribute("error", "Debe informar todos los campos");
                }
            } else if (idUsuarioConectado == null) {
                //En este punto no dejamos pasat usuarios sin identificar
                response.sendRedirect("inicio.publicador");
                redireccionActiva = true;
            } else if (accio.equals("verMensajes")) {
                //TODO pues la lista sin mas
                ResponseHistoricoMensajesBean obtenerHistoricoMensajes = ws.obtenerHistoricoMensajes(idUsuarioConectado);
                List<MensajeBean> mensajes = obtenerHistoricoMensajes.getMensajes();
                request.setAttribute("mensajes", mensajes);
                mapeo.put("titulo", "Mensajes del usuario");
                mapeo.put("href", "mensajes.jsp");
            } else if (accio.equals("enviarMensaje")) {
                //TODO siempre sendredirect para evitar F5 con mensaje ok o ko
                String mensaje = request.getParameter("mensaje");
                boolean twitter = request.getParameter("twitter") != null;
                boolean facebook = request.getParameter("facebook") != null;
                boolean google = request.getParameter("google") != null;

                if (twitter || facebook || google) {
                    if (mensaje != null) {
                        boolean respuesta = ws.publicarMensaje(idUsuarioConectado, mensaje, twitter, facebook, google);
                        response.sendRedirect("inicio.publicador");
                        redireccionActiva = true;
                        if (!respuesta) {
                            response.sendRedirect("inicio.publicador?error=No se ha podido publicar el mensaje");
                            redireccionActiva = true;
                        }
                    }else{
                    response.sendRedirect("inicio.publicador?error=Debe escribir un mensaje");
                    redireccionActiva = true;
                    }
                } else {
                    response.sendRedirect("inicio.publicador?error=Debe seleccionar una red social");
                    redireccionActiva = true;
                }
            } else if (accio.equals("vincularTwitter")) {
                //TODO
            } else if (accio.equals("vincularFacebook")) {
                //TODO
            } else if (accio.equals("vincularGooglr")) {
                //TODO
            } else if (accio.equals("salir")) {
                sesio.invalidate();
                response.sendRedirect("inicio.publicador");
                redireccionActiva = true;
            }

            if (request.getParameter("error") != null) {
                request.setAttribute("error", request.getParameter("error"));
            }
            if (!redireccionActiva) {
                request.setAttribute("mapeo", mapeo);
                dispatch.forward(request, response);
            }
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
