<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">

<head>
  <!-- Required meta tags -->
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Film Rental</title>
  <!-- plugins:css -->
  <link rel="stylesheet" href="template/vendors/mdi/css/materialdesignicons.min.css">
  <link rel="stylesheet" href="template/vendors/base/vendor.bundle.base.css">
  <!-- endinject -->
  <!-- plugin css for this page -->
  <link rel="stylesheet" href="template/vendors/datatables.net-bs4/dataTables.bootstrap4.css">
  <!-- End plugin css for this page -->
  <!-- inject:css -->
  <link rel="stylesheet" href="template/css/style.css">
  <!-- endinject -->
  <link rel="shortcut icon" href="icons/film-2-16.ico">
</head>
<body>
  <div class="container-scroller">
    <!-- partial:partials/_navbar.html -->
    <nav class="navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
      <div class="navbar-brand-wrapper d-flex justify-content-center">
        <div class="navbar-brand-inner-wrapper d-flex justify-content-between align-items-center w-100">  
          <a class="navbar-brand brand-logo" href="index.html"><img src="" alt="Film Rental"/></a>
          <a class="navbar-brand brand-logo-mini" href="index.html"><img src="icons/film-2-16.ico" alt="logo"/></a>
          <button class="navbar-toggler navbar-toggler align-self-center" type="button" data-toggle="minimize">
            <span class="mdi mdi-sort-variant"></span>
          </button>
        </div>  
      </div>
      <div class="navbar-menu-wrapper d-flex align-items-center justify-content-end">
        <ul class="navbar-nav navbar-nav-right">
          <li class="nav-item nav-profile dropdown">
            <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" id="profileDropdown">
              <img src="images/faces/face5.jpg" alt="Empleado"/>
              <span class="nav-profile-name">${user.firstName}&nbsp;${user.lastName}</span>
            </a>
            <div class="dropdown-menu dropdown-menu-right navbar-dropdown" aria-labelledby="profileDropdown">
              <a class="dropdown-item" href="login">
                <i class="mdi mdi-logout text-primary"></i>
                Logout
              </a>
            </div>
          </li>
        </ul>
        <button class="navbar-toggler navbar-toggler-right d-lg-none align-self-center" type="button" data-toggle="offcanvas">
          <span class="mdi mdi-menu"></span>
        </button>
      </div>
    </nav>
    <!-- partial -->
    <div class="container-fluid page-body-wrapper">
      <!-- partial:partials/_sidebar.html -->
      <nav class="sidebar sidebar-offcanvas" id="sidebar">
        <ul class="nav">
          <li class="nav-item">
            <a class="nav-link" href="clientes">
              <i class="mdi mdi-account menu-icon"></i>
              <span class="menu-title">Clientes</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="index.html">
              <i class="mdi mdi-view-headline menu-icon"></i>
              <span class="menu-title">Categorías</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="pages/forms/basic_elements.html">
              <i class="mdi mdi-grid-large menu-icon"></i>
              <span class="menu-title">Películas</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="pages/charts/chartjs.html">
              <i class="mdi mdi-file-document-box-outline menu-icon"></i>
              <span class="menu-title">Actores</span>
            </a>
          </li>
        </ul>
      </nav>
      <!-- partial -->
      <div class="main-panel">
        <div class="content-wrapper">
          <div class="row">
            <div class="col-md-12 grid-margin">
              <div class="d-flex justify-content-between flex-wrap">
                <div class="d-flex align-items-end flex-wrap">
                  <div class="mr-md-3 mr-xl-5">
                    <h2><i class="mdi mdi-account"></i>&nbsp;Alquiler #${selectedrental.rentalId}</h2>
                      <p class="mb-md-0"><a href="cliente?id=${selectedclient.customerId}">${selectedclient.firstName}&nbsp;${selectedclient.lastName}</a></p>
                  </div>
                  <div class="d-flex">
                    <i class="mdi mdi-home text-muted hover-cursor"></i>
                    <p class="text-muted mb-0 hover-cursor">&nbsp;/&nbsp;Clientes/#${selectedclient.customerId}/Rentals/#${selectedrental.rentalId}</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        
          <div class="row">
            <div class="row" style="margin-left: 1px; width: 100%;">
            <div class="col-md-4 stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title"><i class="mdi mdi-cash-multiple"></i>&nbsp;Pago pendiente</h4>
                  <div>
                      <h2 style="padding: 5px;">0.00€</h2><br/>
                      <a href="#" class="col-lg-12 btn btn-secondary btn-fw" type="submit" style="width: 150px; margin: 3px; color: white;">Realizar pago</a>
                  </div>
                </div>
              </div>
            </div>
            <div id="datos" class="col-md stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title"><i class="mdi mdi-filmstrip"></i>&nbsp;Películas alquiladas</h4>
                  <div>
                      <ul>
                        <li>
                            <h4 style="padding: 2px;">Película</h4>
                        </li>
                          <li>
                            <h4 style="padding: 2px;">Película</h4>
                        </li>
                          <li>
                            <h4 style="padding: 2px;">Película</h4>
                        </li>
                          <li>
                            <h4 style="padding: 2px;">Película</h4>
                        </li>
                      </ul>
                  </div>
                </div>
              </div>
            </div>
            <div id="datos" class="col-md stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title"><i class="mdi mdi-database"></i>&nbsp;Datos</h4>
                  <div>
                        <p><b>Creado:&nbsp;</b>${selectedrental.getRentalDateString()}</p>
                        <p><b>Última mod.:&nbsp;</b>${selectedrental.lastUpdate}</p>
                        <p><b>Tienda:&nbsp;</b>${selectedrental.inventory.store.address.getFullAddress()}</p><br/><br/>
                        <h4 class="text-muted">Fecha Devolución</h4>
                        <h3>${selectedrental.getReturnDateString()}</h3>
                  </div>
                </div>
              </div>
            </div>
            </div>
            <div id="alquileres" class="col-md-12 stretch-card" style="margin-top: 30px;">
              <div class="card">
                <div class="card-body">
                  <p class="card-title"><i class="mdi mdi-cash-multiple"></i>&nbsp;Pagos</p>
                    <div class="d-flex justify-content-between align-items-end flex-wrap" style="margin-bottom: 10px;">
                        <a type="button" class="btn btn-inverse-success">
                            +&nbsp;Añadir
                        </a>
                    </div>
                    <div class="table-responsive">
                    <table id="recent-purchases-listing" class="table">
                      <thead>
                        <tr>
                            <th>Fecha de pago</th>
                            <th>Tramitado por</th>
                            <th>Cantidad (<b>€</b>)</th>
                            <th>Acciones</th>
                        </tr>
                      </thead>
                      <tbody>
                            <c:forEach var="pago" items="${selectedrental.payments}">
                                <tr>
                                    <td>
                                        ${pago.getPaymentDateString()}
                                    </td>
                                    <td>
                                        <a href="staff?id=${pago.staff.staffId}">
                                            ${pago.staff.firstName}&nbsp;${pago.staff.lastName}
                                        </a>
                                    </td>
                                    <td>
                                        <b>${pago.amount}&nbsp;€</b>
                                    </td>
                                    <td>
                                        <a class="btn btn-warning" type="button" href="pago?id=${pago.paymentId}">Ver detalles</a>
                                    </td>
                                </tr>
                            </c:forEach>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- content-wrapper ends -->
        <!-- partial:partials/_footer.html -->
        <footer class="footer">
          <div class="d-sm-flex justify-content-center justify-content-sm-between">
            <span class="float-none float-sm-right d-block mt-1 mt-sm-0 text-center"> Film Rental Application by Cedric Christoph</span>
          </div>
        </footer>
        <!-- partial -->
      </div>
      <!-- main-panel ends -->
    </div>
    <!-- page-body-wrapper ends -->
  </div>
  <!-- container-scroller -->

  <!-- plugins:js -->
  <script src="template/vendors/base/vendor.bundle.base.js"></script>
  <!-- endinject -->
  <!-- Plugin js for this page-->
  <script src="template/vendors/chart.js/Chart.min.js"></script>
  <script src="template/vendors/datatables.net/jquery.dataTables.js"></script>
  <script src="template/vendors/datatables.net-bs4/dataTables.bootstrap4.js"></script>
  <!-- End plugin js for this page-->
  <!-- inject:js -->
  <script src="template/js/off-canvas.js"></script>
  <script src="template/js/hoverable-collapse.js"></script>
  <script src="template/js/template.js"></script>
  <!-- endinject -->
  <!-- Custom js for this page-->
  <script src="template/js/dashboard.js"></script>
  <script src="template/js/data-table.js"></script>
  <script src="template/js/jquery.dataTables.js"></script>
  <script src="template/js/dataTables.bootstrap4.js"></script>
  <!-- End custom js for this page-->
  <script src="template/js/jquery.cookie.js" type="text/javascript"></script>
</body>

</html>

