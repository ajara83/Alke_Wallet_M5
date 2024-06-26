<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login - Alke Wallet</title>
    <link rel="stylesheet" href="assets/css/style.css">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <script src="assets/js/deposito.js">   </script>
    
</head>

<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
          <img src="assets/imgs/logo2.png" class="alke_logo">
        </div>
          <div class="row justify-content-center">
          
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <h3>Bienvenid@!</h3>
                        <h4>Para crear tu nueva cuenta ingresa los siguientes datos</h4>
                    </div>
                    <div class="card-body">
                        <form action="nuevaCuenta" method="post" id="theform">
                            <div class="form-group">
                                <div class="container">
                                  <div class="row">
                                    <div class="col-sm">
                                      <input type="text" class="form-control" id="nombre" name="nombre"  placeholder="Nombre" required>
                                    </div>
                                    <div class="col-sm">
                                      <input type="password" class="form-control" id="password" name="password"  placeholder="Password" required>
                                    </div>      
                                  </div>
                                  <br>
                                  <div class="row">
                                    <div class="col-sm">
                                        <input type="text" class="form-control" id="rut" name="rut"  placeholder="Rut" required onchange="validarRUT(this)">
                                      </div>  
                                      <div class="col-sm">
                                        <input type="email" class="form-control" id="email" name="email"  placeholder="Email" required>
                                      </div>  
                                  </div>
                                </div>
                              </div>
                            <button class="btn btn-primary btn-lg btn-block">Ingresar</button>
                        </form>
                        <a href="login" class="btn btn-secondary btn-lg btn-block">Volver</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
