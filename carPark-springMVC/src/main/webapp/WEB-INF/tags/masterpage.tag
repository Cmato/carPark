<%-- 
    Document   : masterpage
    Created on : 16.12.2015, 19:12:32
    Author     : xcmarko
--%>

<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" trimDirectiveWhitespaces="true"  %>
<%@ attribute name="title" required="false" %>
<%@ attribute name="head" fragment="true" %>
<%@ attribute name="body" fragment="true" required="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="onw" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title><c:out value="${title}"/></title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/dist/css/skins/skin-blue.min.css">

    <style>
      .center {
        margin: auto;
        width: 90%;
        padding: 1px;
      }

      .actions {
        width: 70px;
      }

      .actions a {
        margin-right: 5px;
      }
    </style>
    
    <jsp:invoke fragment="head"/>
  </head>
  <body class="hold-transition skin-blue sidebar-mini">
    <div class="wrapper">

      <!-- Main Header -->
      <header class="main-header">

        <!-- Logo -->
        <a href="index2.html" class="logo">
          <!-- mini logo for sidebar mini 50x50 pixels -->
          <span class="logo-mini"><b>A</b>LT</span>
          <!-- logo for regular state and mobile devices -->
          <span class="logo-lg"><b>CarPark</b></span>
        </a>

        <!-- Header Navbar -->
        <nav class="navbar navbar-static-top" role="navigation">
          <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
          </a>
          <!-- Navbar Right Menu -->
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
              <!-- User Account Menu -->
              <li class="dropdown user user-menu">
                <!-- Menu Toggle Button -->
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <!-- The user image in the navbar-->
                  <img src="${pageContext.request.contextPath}/static/dist/img/user2-128x128.png" class="user-image" alt="User Image">
                  <!-- hidden-xs hides the username on small devices so only the image appears. -->
                  <span class="hidden-xs">Alexander Pierce</span>
                </a>
                <ul class="dropdown-menu">
                  <!-- The user image in the menu -->
                  <li class="user-header">
                    <img src="${pageContext.request.contextPath}/static/dist/img/user2-128x128.png" class="img-circle" alt="User Image">
                    <p>
                      Alexander Pierce - Web Developer
                      <small>Member since Nov. 2012</small>
                    </p>
                  </li>
                  <!-- Menu Footer-->
                  <li class="user-footer">
                    <div class="pull-left">
                      <a href="#" class="btn btn-default btn-flat">Profile</a>
                    </div>
                    <div class="pull-right">
                      <a href="#" class="btn btn-default btn-flat">Sign out</a>
                    </div>
                  </li>
                </ul>
              </li>
            </ul>
          </div>
        </nav>
      </header>
      <!-- Left side column. contains the logo and sidebar -->
      <aside class="main-sidebar">

        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">

          <!-- Sidebar user panel (optional) -->
          <div class="user-panel">
            <div class="pull-left image">
              <img src="${pageContext.request.contextPath}/static/dist/img/user2-128x128.png" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
              <p>Alexander Pierce</p>
              <!-- Status -->
              <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
          </div>

          <!-- Sidebar Menu -->
          <ul class="sidebar-menu">
            <li class="header">Car Park administration</li>
            <!-- Optionally, you can add icons to the links -->
            <li id="dashboardMenuItem" class="active"><a href="${pageContext.request.contextPath}"><i class="fa fa-home"></i> <span>Dashboard</span></a></li>
            <li id="carMenuItem"><a href="${pageContext.request.contextPath}/car/list"><i class="fa fa-automobile"></i> <span>Cars</span></a></li>
            <li id="employeeMenuItem"><a href="${pageContext.request.contextPath}/employee/list"><i class="fa fa-user" style="margin-right: -1px; margin-left: 1px;"></i> <span>Employees</span></a></li>
            <li id="reservationMenuItem"><a href="#"><i class="fa fa-calendar-o"></i> <span>Reservations</span></a></li>
            <li id="rentalMenuItem"><a href="${pageContext.request.contextPath}/rental/list"><i class="fa fa-calendar-check-o"></i> <span>Rentals</span></a></li>
          </ul><!-- /.sidebar-menu -->
        </section>
        <!-- /.sidebar -->
      </aside>

      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <jsp:invoke fragment="body"/>
      </div><!-- /.content-wrapper -->

      <!-- Main Footer -->
      <footer class="main-footer">

        <!-- To the right -->
        <div class="pull-right hidden-xs">
          <a href="https://www.fi.muni.cz" target="_blank">Faculty of Informatics Masaryk University</a>
        </div>
        <!-- Default to the left -->
        <strong>Copyright &copy; 2015 <a href="https://github.com/Cmato/carPark">PA165 Dream Team No. 1</a>.</strong> All rights reserved.

      </footer>

    </div><!-- ./wrapper -->

    <!-- REQUIRED JS SCRIPTS -->

    <!-- jQuery 2.1.4 -->
    <script src="${pageContext.request.contextPath}/static/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
    <!-- AdminLTE App -->
    <script src="${pageContext.request.contextPath}/static/dist/js/app.min.js"></script>

    <script>
      //menu active class
      function setActiveMenu() {
        $(".sidebar-menu li").removeClass("active");
        var url = window.location.href;
        if(url.indexOf("/car/") > -1) {
          $("#carMenuItem").addClass("active");
          return;
        }
        if(url.indexOf("/employee/") > -1) {
          $("#employeeMenuItem").addClass("active");
          return;
        }
        if(url.indexOf("/reservation/") > -1) {
          $("#reservationMenuItem").addClass("active");
          return;
        }
        if(url.indexOf("/rental/") > -1) {
          $("#rentalMenuItem").addClass("active");
          return;
        }

        $("#dashboardMenuItem").addClass("active");
      }

      $(document).ready(function() {
        setActiveMenu();
      });
    </script>
  </body>
</html>