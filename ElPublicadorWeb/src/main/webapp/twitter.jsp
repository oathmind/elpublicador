<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Main jumbotron for a primary marketing message or call to action -->
<div class="jumbotron">
    <div class="container">
        <h1>Vinculando con TWITTER</h1>
        <p>Para vincular la cuenta, siga el siguiente enlace, e introduzca el codigo que obtendra en el siguiente campo.</p>
        <p><a class="btn btn-success" href="${URL}" target="_blank" role="button">VINCULAR</a></p>
        <div class="form-group">
            <form class="form-signin" method="POST" role="form" action="vincularTwitter2.publicador">
                <label for="inputClave">Clave de retorno de TWITTER (puedes obtenerla mediante el link que hay mas arriba, NO es tu contraseña de Twitter)</label>
                <input type="text" id="inputClave" name="clave" class="form-control" placeholder="CLAVE DE RETORNO" required="">
                <button style="margin-top: 10px;" class="btn btn-lg btn-primary btn-block" type="submit">VINCULAR CUENTA</button>
            </form>
            <a class="btn btn-warning" href="inicio.publicador" role="button">Cancelar</a>
        </div>
    </div>
</div>