<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
            <a href="logout" class="btn btn-outline-success my-2 my-sm-0">SALIR</a>
          </div>
        </div>
      </nav>
  
      <main role="main" class="container">
       
        <div class="jumbotron">
        
        <h1>ID, <c:out value="${cliente.idCliente}" /></h1>
        
        <h1>Bienvenido, <c:out value="${cliente.nombre}" /></h1>
        <h2>Rut: <c:out value="${cliente.rut}" /></h2>
        
        <h1>En tu cuenta numero <c:out value="${cuenta.numeroCuenta}" /> hay un saldo de , $<c:out value="${cuenta.saldo}" />.-</h1>
	    
		<c:if test="${empty sessionScope.cliente}">
		        <script>
		            window.location.href = 'login.jsp';
		        </script>
	    </c:if>
	    
         
         
        </div>
      </main>
      <section class="acciones">
        <div class="container">
          <div class="row text-center">
            <div class="col align-middle">
              <a href="deposito" class="MisAcciones"><i class="fas fa-wallet"></i> Depositar</a>
            </div>
            <div class="col align-middle">
              <a href="transferencia" class="MisAcciones"><i class="fas fa-exchange-alt"></i> Transferir</a>
            </div>
            <div class="col">
              <a href="transacciones" class="MisAcciones"><i class="fas fa-history"></i> Mis Transacciones</a>
            </div>
          </div>
        </div>
      </section>



    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
