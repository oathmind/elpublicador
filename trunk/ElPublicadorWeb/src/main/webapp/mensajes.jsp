<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Main jumbotron for a primary marketing message or call to action -->
<div class="jumbotron">
    <div class="container">
        <h1>Mis Mensajes</h1>
        <div class="form-group">
            <div class="panel panel-default">
                <div class="panel-heading">Lista de mensajes enviados <a class="btn btn-success" href="inicio.publicador" role="button">Volver</a></div>
                <table class="table">
                    <thead>
                        <tr>
                            <th>Mensaje</th>
                            <th>Facebook</th>
                            <th>Twitter</th>
                            <th>Google</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${mensajes}" var="mensaje">
                        <tr>
                            <td>${mensaje.mensaje}</td>
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
</div>