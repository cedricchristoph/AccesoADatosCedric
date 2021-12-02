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
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
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
                    <h2><i class="mdi mdi-account"></i>&nbsp;${selectedclient.firstName}&nbsp;${selectedclient.lastName}</h2>
                    <p class="mb-md-0">#${selectedclient.customerId}</p>
                  </div>
                  <div class="d-flex">
                    <i class="mdi mdi-home text-muted hover-cursor"></i>
                    <p class="text-muted mb-0 hover-cursor">&nbsp;/&nbsp;Clientes/#${selectedclient.customerId}</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        
          <div class="row">
            <div class="col-md-12 stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title"><i class="mdi mdi-arrow-down"></i>&nbsp;Accesos directos</h4>
                  <div>
                      <a href="#datos" class="col-lg-12 btn btn-secondary btn-fw" type="submit" style="width: 150px; margin: 3px; color: white;">Ver Datos</a>
                      <a href="#alquileres" class="col-lg-12 btn btn-secondary btn-fw" type="submit" style="width: 150px; margin: 3px; color: white;">Ver Alquileres</a>
                      <a href="#pagos_realizados" class="col-lg-12 btn btn-secondary btn-fw" type="submit" style="width: 150px; margin: 3px; color: white;">Ver Pagos</a>
                      <a class="col-lg-12 btn btn-inverse-success btn-fw" type="submit" style="width: 150px; margin: 3px;">Nuevo Alquiler</a>
                      <a class="col-lg-12 btn btn-inverse-success btn-fw" type="submit" style="width: 150px; margin: 3px;">Nuevo Pago</a>
                  </div>
                </div>
              </div>
            </div>
            <div class="row" style="margin-left: 1px;">
            <div id="datos" class="col-md-8 stretch-card" style="margin-top: 30px;">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title"><i class="mdi mdi-database"></i>&nbsp;Datos</h4>
                  <div>
                    <p><b>Creado:&nbsp;</b>${selectedclient.createDate}</p>
                    <p><b>Última mod.:&nbsp;</b>${selectedclient.lastUpdate}</p>
                    <p><b>Tienda:&nbsp;</b>${selectedclient.store.address.getFullAddress()}</p><br/>
                    <p><b>Datos de contacto del cliente:</b></p>
                    <p><b>E-Mail:&nbsp;</b><a href="mailto:${selectedclient.email}">${selectedclient.email}</a></p><br/>
                    <div>
                      <a class="col-lg-12 btn btn-light btn-fw" type="submit" style="width: 150px;">Editar</a>
                      <a class="col-lg-12 btn btn-danger btn-fw" type="submit" style="width: 150px; color: white;">Eliminar</a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div id="pagospendientes" class="col-md stretch-card" style="margin-top: 30px;">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title"><i class="mdi mdi-cash-multiple"></i>&nbsp;Pago total pendiente</h4>
                  <div>
                    <h2 style="padding: 5px;">${pendiente}&nbsp;€</h2><br/>
                    <button type="button" class="col-lg-12 btn btn-secondary btn-fw" style="margin: 3px; color: white;" data-toggle="modal" data-target="#alquilerespendientes">
                        Ver pagos pendientes
                    </button>
                      <a href="pay" class="col-lg-12 btn btn-secondary btn-fw" type="submit" style="margin: 3px; color: white;">Realizar pago</a>
                  </div>
                </div>
              </div>
            </div>
            </div>
            <div id="alquileres" class="col-md-12 stretch-card" style="margin-top: 30px;">
              <div class="card">
                <div class="card-body">
                  <p class="card-title"><i class="mdi mdi-filmstrip"></i>&nbsp;Alquileres</p>
                    <div class="d-flex justify-content-between align-items-end flex-wrap" style="margin-bottom: 10px;">
                        <a href="newrental" type="button" class="btn btn-inverse-success">
                            +&nbsp;Nuevo
                        </a>
                    </div>
                    <div class="table-responsive">
                    <table id="recent-purchases-listing" class="table">
                      <thead>
                        <tr>
                            <th>Fecha</th>
                            <th>Fecha Devolución</th>
                            <th>Realizado por</th>
                            <th>Acciones</th>
                        </tr>
                      </thead>
                      <tbody>
                            <c:forEach var="rental" items="${selectedclient.rentals}">
                                <tr>
                                    <td>
                                        ${rental.getRentalDateString()}
                                    </td>
                                    <td>${rental.getReturnDateString()}</td>
                                    <td>
                                        <a href="staff?id=${rental.staff.staffId}">
                                            ${rental.staff.firstName}&nbsp;${rental.staff.lastName}
                                        </a>
                                    </td>
                                    <td>
                                        <a class="btn btn-warning" type="button" href="alquiler?id=${rental.rentalId}">Ver detalles</a>
                                    </td>
                                </tr>
                            </c:forEach>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div><br/>
          </div>
        </div>
          
          
          <!-- Modal -->
            <div class="modal fade" id="alquilerespendientes" tabindex="-1" role="dialog" aria-labelledby="labelAlquileresPendientes" aria-hidden="true">
              <div class="modal-dialog" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title" id="labelAlquileresPendientes">Alquileres pendientes de pago</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                      <span aria-hidden="true">&times;</span>
                    </button>
                  </div>
                  <div class="modal-body">
                    <div class="table-responsive">
                    <table id="recent-purchases-listing" class="table">
                      <thead>
                        <tr>
                            <th>Alquiler ID</th>
                            <th>Realizado por</th>
                            <th>Pendiente</th>
                            <th>Acciones</th>
                        </tr>
                      </thead>
                      <tbody>
                            <c:forEach var="rental" items="${rentalspendientes}">
                                <tr>
                                    <td>
                                      ${rental.rentalId}
                                    </td>
                                    <td>
                                        <a href="staff?id=${rental.staff.staffId}">
                                            ${rental.staff.firstName}&nbsp;${rental.staff.lastName}
                                        </a>
                                    </td>
                                    <td>
                                        ${rental.getPagoPendiente()}&nbsp;€
                                    </td>
                                    <td>
                                        <a class="btn btn-warning" type="button" href="alquiler?id=${rental.rentalId}">Ver detalles</a>
                                        <a class="btn btn-success" type="button" href="pay?rental=${rental.rentalId}">Pagar</a>
                                     </td>
                                </tr>
                            </c:forEach>
                      </tbody>
                    </table>
                  </div>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
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

