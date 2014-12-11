<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Main jumbotron for a primary marketing message or call to action -->
<div class="jumbotron">
    <div class="container">
        <h1>${nombreUsuario}</h1>
        <div class="form-group">
            <label for="mensaje">Nuevo Mensaje</label>
            <form id="envioRedesForm" class="form-signin" method="POST" role="form" action="enviarMensaje.publicador">
                <textarea class="form-control" rows="5" name="mensaje" id="mensaje" required=""></textarea>
                <div><label class="control-label">Redes Sociales Destino</label></div>
                <label>
                    <input type="checkbox" <c:if test="${not vinculadoFacebook}">disabled="disabled"</c:if> name="facebook" value="facebook"/> Facebook
                    </label>
                    <label>
                        <input type="checkbox" <c:if test="${not vinculadoTwitter}">disabled="disabled"</c:if> name="twitter" value="twitter"/> Twitter
                    </label>
                    <label>
                        <input type="checkbox" <c:if test="${not vinculadoGoogle}">disabled="disabled"</c:if> name="google" value="google"/> Google+
                    </label>
                    <button style="margin-top: 10px;" class="btn btn-lg btn-primary btn-block" type="submit">Enviar Mensaje</button>
                </form>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">Ultimos Mensajes enviados <a class="btn btn-success" href="verMensajes.publicador" role="button">Ver Todos</a></div>
                <table class="table">
                    <thead>
                        <tr>
                            <th>Mensaje</th>
                            <th>Fecha</th>
                            <th>Facebook</th>
                            <th>Twitter</th>
                            <th>Google</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${ultimosMensajes}" var="mensaje">
                        <tr>
                            <td>${mensaje.mensaje}</td>
                            <td>${mensaje.fecha}</td>
                            <td>${mensaje.facebook}</td>
                            <td>${mensaje.twitter}</td>
                            <td>${mensaje.google}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<div class="container">
    <!-- Example row of columns -->
    <div class="row">
        <div class="col-md-4">
            <h2>Facebook</h2>
            <c:choose>
                <c:when test="${vinculadoFacebook}">
                    <p>Vinculado!</p>
                    <p><a class="btn btn-default" href="#" role="button">Volver a vincular</a></p>
                </c:when>
                <c:otherwise>
                    <p><a class="btn btn-default" href="#" role="button">Vincular</a></p>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="col-md-4">
            <h2>Twitter</h2>
            <c:choose>
                <c:when test="${vinculadoTwitter}">
                    <p>Vinculado!</p>
                    <p><a class="btn btn-default" href="vincularTwitter.publicador" role="button">Volver a vincular</a></p>
                </c:when>
                <c:otherwise>
                    <p><a class="btn btn-default" href="vincularTwitter.publicador" role="button">Vincular</a></p>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="col-md-4">
            <h2>Google +</h2>
            <c:choose>
                <c:when test="${vinculadoGoogle}">
                    <p>Vinculado!</p>
                    <p><a class="btn btn-default" href="#" role="button">Volver a vincular</a></p>
                </c:when>
                <c:otherwise>
                    <p><a class="btn btn-default" href="#" role="button">Vincular</a></p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>