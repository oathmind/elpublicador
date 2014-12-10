<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <html xmlns="http://www.w3.org/1999/xhtml">
        <head>
            <meta charset="utf-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
                    <title>${mapeo.titulo}</title>
                    <meta name="description" content="">
                        <meta name="viewport" content="width=device-width, initial-scale=1">

                            <link rel="stylesheet" href="css/bootstrap.min.css">
                                <style>
                                    body {
                                        padding-top: 50px;
                                        padding-bottom: 20px;
                                    }
                                </style>
                                <link rel="stylesheet" href="css/bootstrap-theme.min.css">
                                    <link rel="stylesheet" href="css/main.css">

                                        <script src="js/vendor/modernizr-2.6.2-respond-1.1.0.min.js"></script>
                                        </head>
                                        <body>
                                            <!--[if lt IE 7]>
                                                <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
                                            <![endif]-->
                                            <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
                                                <div class="container">
                                                    <div class="navbar-header">
                                                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                                                            <span class="sr-only">Toggle navigation</span>
                                                            <span class="icon-bar"></span>
                                                            <span class="icon-bar"></span>
                                                            <span class="icon-bar"></span>
                                                        </button>
                                                        <a class="navbar-brand" href="inicio.publicador">El Publicador</a>
                                                    </div>
                                                    <c:if test="${not empty idUsuario}">
                                                        <div id="navbar" class="navbar-collapse collapse">
                                                            <form class="navbar-form navbar-right" method="GET" action="salir.publicador" role="form">
                                                                <button type="submit" class="btn btn-danger">Salir</button>
                                                            </form>
                                                        </div>
                                                    </c:if><!--/.navbar-collapse -->
                                                </div>
                                            </nav>
                                            <c:if test="${not empty error}">
                                                <div class="alert alert-danger" role="alert">
                                                    <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                                                    <span class="sr-only">Error:</span>
                                                    ${error}
                                                </div>
                                            </c:if>
                                            <jsp:include flush="true" page="${mapeo.href}"></jsp:include>

                                            <hr>

                                                <footer>
                                                    <p>&copy; El Publicador 2014</p>
                                                </footer>
                                                </div> <!-- /container -->        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
                                                <script>window.jQuery || document.write('<script src="js/vendor/jquery-1.11.1.min.js"><\/script>')</script>

                                                <script src="js/vendor/bootstrap.min.js"></script>

                                                <script src="js/main.js"></script>
                                        </body>
                                        </html>
