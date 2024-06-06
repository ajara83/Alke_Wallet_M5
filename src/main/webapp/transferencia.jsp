<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Tu Cuenta - Alke Wallet</title>
        <link rel="stylesheet" href="assets/css/style.css">
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">    
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        
        <script src="assets/js/deposito.js"></script>
        
    </head>
    
<body>


    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <a class="navbar-brand" href=".index.html"><img src="assets/imgs/logo2.png" class="alke_logo_menu"></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item ">
              <a class="nav-link" href="inicio">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="transacciones">Mis Transacciones</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="transferencia">Transferir</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="deposito">Deposito</a>
              </li> 
          </ul>
          <div class="form-inline mt-2 mt-md-0">            
            <a href="login.html" class="btn btn-outline-success my-2 my-sm-0">SALIR</a>
          </div>
        </div>
      </nav>
      <main role="main" class="container">
        <div class="jumbotron">
          <h1>Transferencias</h1>
          <p class="lead">Selecciona uno de tus contactos para enviar dinero</p>
          <div class="container">
            <h2>Enviar Dinero</h2>
            <form name="formulario" method="post" action="transferencia">
              <div class="form-group">
                <div class="container">
                  <div class="row">                     
                    <div class="col-sm">
                      <input type="number" class="form-control" id="monto" name="monto"  placeholder="Ingresa el Monto a transferir" required>
                    </div>                
                  </div>
                </div>
              </div>
             
        <h3>Contactos</h3>
          <ul id="contactList" class="list-group">
          <c:forEach var="cliente" items="${clientesTransf}">
          <li class="list-group-item">
          	<div class="contact-info">
                <input type="radio" value="${cliente.cuenta.idCuenta }" name="idCuentaFinal" id="idCuentaFinal" required>
                <span class="contact-name" id="name_1">${cliente.nombre}</span>
                <span class="contact-details" id="detalle_1">Cuenta : ${cliente.cuenta.numeroCuenta }</span>                
              </div>
          </c:forEach>      
            
            </ul>
            <input type="hidden" name="idCuenta" value="${cuenta.idCuenta }">
              <button type="submit" class="btn btn-primary btn-lg btn-block" onclick="confirm('¿Estás seguro de realizar la transferencia?')">Enviar dinero</button>
            </form>
            <a href="inicio" class="btn btn-secondary btn-lg btn-block">Volver al Menú Principal</a>
          </div>
        </div>
</main> 
  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>