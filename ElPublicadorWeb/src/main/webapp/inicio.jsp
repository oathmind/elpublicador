<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Main jumbotron for a primary marketing message or call to action -->
<div class="jumbotron">
    <div class="container">
        <h1>El Publicador</h1>
        <p>Con el publicador podras publicar simultaneamente en varias redes soaciales, aun no tienes cuenta? create una!</p>

        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <form class="form-signin" role="form">
                    <h2 class="form-signin-heading">Entrar en el sitio</h2>
                    <label for="email" class="sr-only">Email</label>
                    <input type="email" id="inputEmail" class="form-control" placeholder="Email" required="" autofocus="">
                    <label for="inputPassword" class="sr-only">Password</label>
                    <input type="password" id="inputPassword" class="form-control" placeholder="Password" required="">
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" value="remember-me"> Remember me
                        </label>
                    </div>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Registrarse</button>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <!-- Example row of columns -->
    <div class="row">
        <div class="col-md-4">
            <h2>Facebook</h2>
            <p>Facebook es la mayor red social!</p>
            <p><a class="btn btn-default" href="https://www.facebook.com/" target="_blank" role="button">Pagina oficial</a></p>
        </div>
        <div class="col-md-4">
            <h2>Twitter</h2>
            <p>Pocos caracteres pero mucho que decir.</p>
            <p><a class="btn btn-default" href="https://twitter.com/" target="_blank" role="button">Pagina oficial</a></p>
        </div>
        <div class="col-md-4">
            <h2>Google +</h2>
            <p>La red social del gigante de internet.</p>
            <p><a class="btn btn-default" href="https://plus.google.com/" target="_blank" role="button">Pagina oficial</a></p>
        </div>
    </div>