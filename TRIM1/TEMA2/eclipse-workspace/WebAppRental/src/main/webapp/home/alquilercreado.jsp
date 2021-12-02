<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Film Rental</title>
  <!-- plugins:css -->
  <link rel="stylesheet" href="template/vendors/mdi/css/materialdesignicons.min.css">
  <link rel="stylesheet" href="template/vendors/base/vendor.bundle.base.css">
  <!-- endinject -->
  <!-- plugin css for this page -->
  <!-- End plugin css for this page -->
  <!-- inject:css -->
  <link rel="stylesheet" href="template/css/style.css">
  <!-- endinject -->
  <link rel="shortcut icon" href="icons/film-2-16.ico">
</head>

    
<body>
  <div class="container-scroller">
    <div class="container-fluid page-body-wrapper full-page-wrapper">
      <div class="content-wrapper d-flex align-items-center auth px-0" style="background-color: #edf7f0;">
        <div class="row w-100 mx-0">
          <div class="col-lg-4 mx-auto">
            <div class="auth-form-light text-left py-5 px-4 px-sm-5" style="border: 1px solid green;">
             <h3 style="color: green;">✔ Alquiler creado</h3>
             <h5 style="margin-top: 30px;"><b>El alquiler se ha creado con éxito</b></h5>
             <ul>
               <li>Cliente: <b>${selectedclient.firstName}&nbsp;${selectedclient.lastName}</b></li>
               <li>Tramitado por: <b>${user.firstName}&nbsp;${user.lastName}</b></li>
               <li>Película alquilada: <b>${selectedclient.title}</b></li>
               <li>Precio a pagar: <b>${selectedfilm.rentalRate}&nbsp;€</b></li>
             </ul>
             <a href="cliente?id=${selectedclient.customerId}" class="btn btn-secondary" style="margin-top: 20px;">← Volver al cliente</a>
            </div>
          </div>
        </div>
      </div>
      <!-- content-wrapper ends -->
    </div>
    <!-- page-body-wrapper ends -->
  </div>
  <!-- container-scroller -->
  <!-- plugins:js -->
  <script src="template/vendors/base/vendor.bundle.base.js"></script>
  <!-- endinject -->
  <!-- inject:js -->
  <script src="template/js/off-canvas.js"></script>
  <script src="template/js/hoverable-collapse.js"></script>
  <script src="template/js/template.js"></script>
  <!-- endinject -->
</body>

</html>

