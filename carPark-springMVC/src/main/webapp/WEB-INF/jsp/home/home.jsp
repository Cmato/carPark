<%-- 
    Document   : home
    Created on : Dec 16, 2015, 18:21:32 PM
    Author     : xcmarko
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="true"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="own" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<own:masterpage title="Main page">
<jsp:attribute name="body">
    
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        <fmt:message key="menu.administration"/>
        <small><fmt:message key="menu.mainmenu"/></small>
      </h1>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-lg-3 col-xs-6">
          <!-- small box -->
          <div class="small-box bg-aqua">
            <div class="inner">
              <h3><c:if test="${not empty authenticatedUser}">${carsNumber}</c:if>
              <c:if test="${empty authenticatedUser}">N/A</c:if></h3>
              <p><fmt:message key="menu.cars"/></p>
            </div>
            <div class="icon">
              <i class="fa fa-automobile"></i>
            </div>
            <a href="${pageContext.request.contextPath}/car/list" class="small-box-footer">
              <fmt:message key="menu.moreinfo"/> <i class="fa fa-arrow-circle-right"></i>
            </a>
          </div>
        </div>
        <!-- ./col -->
        <c:if test="${authenticatedUser.isAdmin==true}">
        <div class="col-lg-3 col-xs-6">
          <!-- small box -->
          <div class="small-box bg-green">
            <div class="inner">
              <h3><c:if test="${not empty authenticatedUser}">${employeeNumber}</c:if>
              <c:if test="${empty authenticatedUser}">N/A</c:if></h3>
            
              <p><fmt:message key="menu.employees"/></p>
            </div>
            <div class="icon">
              <i class="fa fa-user"></i>
            </div>
            <a href="${pageContext.request.contextPath}/employee/list" class="small-box-footer">
              <fmt:message key="menu.moreinfo"/> <i class="fa fa-arrow-circle-right"></i>
            </a>
          </div>
        </div>
        </c:if>
        <!-- ./col -->
      </div>

        <!-- Main content -->
      <div class="row">
        <div class="col-lg-3 col-xs-6">
          <!-- small box -->
          <div class="small-box bg-yellow">
            <div class="inner">
              <h3><c:if test="${not empty authenticatedUser}">${reservationNumber}</c:if>
              <c:if test="${empty authenticatedUser}">N/A</c:if></h3>
            
            
              <p><fmt:message key="menu.reservations"/></p>
            </div>
            <div class="icon">
              <i class="fa fa-calendar-o"></i>
            </div>
            <a href="${pageContext.request.contextPath}/reservation/list" class="small-box-footer">
              <fmt:message key="menu.moreinfo"/> <i class="fa fa-arrow-circle-right"></i>
            </a>
          </div>
        </div>
        <!-- ./col -->
        <div class="col-lg-3 col-xs-6">
          <!-- small box -->
          <div class="small-box bg-red">
            <div class="inner">
            
              <h3><c:if test="${not empty authenticatedUser}">${rentalNumber}</c:if>
              <c:if test="${empty authenticatedUser}">N/A</c:if></h3>
            
              <p><fmt:message key="menu.rentals"/></p>
            </div>
            <div class="icon">
              <i class="fa fa-calendar-check-o"></i>
            </div>
            <a href="${pageContext.request.contextPath}/rental/list" class="small-box-footer">
              <fmt:message key="menu.moreinfo"/> <i class="fa fa-arrow-circle-right"></i>
            </a>
          </div>
        </div>
        <!-- ./col -->
      </div>
    </section><!-- /.content -->

</jsp:attribute>
</own:masterpage>
