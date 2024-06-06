<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Tu Cuenta - Alke Wallet</title>
        <link rel="stylesheet" href="assets/css/style.css">
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">    
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">   
        
        <style>
        .fadein {
		  opacity: 0;
		  transition: opacity 0.5s ease-in-out;
		}
		.fadein.show {
		  opacity: 1;
		}
		.fadeout {
		  opacity: 1;
		  transition: opacity 0.5s ease-in-out;
		}
		.fadeout.hide {
		  opacity: 0;
		}
			        
        </style>
        
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
        <div id="alertFadein" class="alert alert-primary fadein fadeout" role="alert" style="display:none;">  Deposito Realizado!</div>
       <h1>Bienvenid@, ${sessionScope.cliente.nombre}</h1>        
        </div>
    <div class="jumbotron">
    <h2>Depositar</h2>
    <form action="deposito" method="post" id="formDeposito">
      
      <div class="form-group">
        <label for="ctaDestino">Cuenta Destino:</label>
        <h1>${cuenta.numeroCuenta}</h1>
      </div>
       <div class="form-group">
        <label for="ctaDestino">Saldo Actual:</label>
        <h1>${cuenta.saldo}</h1>
      </div>
      <div class="form-group">
        <label for="depositAmount">Monto a depositar:</label>
        <input type="number" class="form-control" id="montoDeposito" name="montoDeposito" placeholder="Ingrese un valor" required>
      </div>
      <input type="hidden" name="idCuenta" value="${cuenta.idCuenta}">
      <input type="hidden" name="showAlert" value="${showAlert}">
      <button type="submit" class="btn btn-primary btn-lg btn-block">Realizar depósito</button>
    </form>
    <a href="inicio" class="btn btn-secondary btn-lg btn-block">Volver al Menú Principal</a>
  </div>
  </main>

  



  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

  <script>
  function getParameterByName(name) {
	  const urlParams = new URLSearchParams(window.location.search);
	  return urlParams.get(name);
	}

	$(document).ready(function() {
	  const showAlert = getParameterByName('showAlert');
	  if (showAlert=='OK') {
	    $('#alertFadein').css('display', 'block');
	    setTimeout(function() {
	      $('#alertFadein').addClass('show');
	    }, 100);
	  }

	  $('#alertFadein').on('click', function() {
	    $(this).addClass('hide').removeClass('show');
	    setTimeout(() => $(this).remove(), 500);
	  });
	});  
  
  </script>
</body>
</html>
