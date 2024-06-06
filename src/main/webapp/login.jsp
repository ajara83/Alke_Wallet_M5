<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login - Alke Wallet</title>
    <link rel="stylesheet" href="assets/css/style.css">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <script>
      function validaForm()
      {
        var email=document.getElementById('email');
        var password=document.getElementById('password');

        // VALIDAR EMAIL
        emailRegex = /^[-\w.%+]{1,64}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$/i;        
        if (emailRegex.test(email.value) && email.value!="") {
            console.log('Todo OK');
            //alert("todo ok")
            document.getElementById('theform').submit();
          }
          else
          {
            alert("Datos incompletos o erroneos");
          }
    }
    </script>
    
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
            Iniciar Sesión
          </div>
          <div class="card-body">
            <form action="login" method="POST">
              <div class="form-group">
                <label for="username">Usuario</label>
                <input type="text" class="form-control" id="rut" name="rut" placeholder="RUT Chileno">
              </div>
              <div class="form-group">
                <label for="password">Contraseña</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Contraseña">
              </div>
              <button type="submit" class="btn btn-primary btn-block">Iniciar Sesión</button>
            </form>
            <a href="nuevaCuenta" class="btn btn-secondary btn-lg btn-block">¿Necesitas una cuenta?</a>
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
