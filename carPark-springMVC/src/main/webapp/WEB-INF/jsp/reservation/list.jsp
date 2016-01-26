<%-- 
    Document   : list
    Created on : 17.12.2015, 18:28:37
    Author     : 
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="own" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<own:masterpage title="Reservations">
<jsp:attribute name="body">
    
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Reservations
        <small>Administration of reservations</small>
      </h1>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">All reservations in system</h3>

              <div class="box-tools">
                <div class="input-group input-group-sm right" style="width: 48px;">
                <a href="${pageContext.request.contextPath}/reservation/detail/">
                  <button type="button" class="btn btn-info btn-flat" title="Add Reservation"><span class="glyphicon glyphicon-plus"></span></button>
                </a>
                </div>
              </div>
            </div>
            <!-- /.box-header -->
            <div class="box-body table-responsive no-padding">
              <table class="table table-hover">
                <tbody><tr>
                  <th>ID</th>
                  <th>Starting Date</th>
                  <th>Ending Date</th>
                  <th>Employee</th>
                  <th>Car</th>
                  <th>State</th>
                  <th>Actions</th>
                </tr>
                <c:forEach items="${reservations}" var="reservation">
                    <tr>
                        <td><c:out value="${reservation.id}"/>.</td>
                        <td><fmt:formatDate value="${reservation.startingDate}" pattern="dd-MM-yyyy"/></td>
                        <td><fmt:formatDate value="${reservation.endingDate}" pattern="dd-MM-yyyy"/></td>
                        <td><c:out value="${reservation.employee.getName()}"/></td>
                        <td><c:out value="${reservation.car.getName()}"/></td>
                        <td><c:out value="${reservation.reservationState}"/></td>
                        <td class="actions">
                          <div class="center">
                            <a href="${pageContext.request.contextPath}/reservation/detail/${reservation.id}" title="Edit"><span class="glyphicon glyphicon-edit"></span></a>
                            <a href="#" title="Remove" class="removeItem">
                                <span class="glyphicon glyphicon-trash"></span>
                                <form method="post" action="${pageContext.request.contextPath}/reservation/delete/${reservation.id}" style="display: none;">
                                </form>
                            </a>
                            <a href="#" title="Rent" class="rentItem">
                                <span class="glyphicon glyphicon-ok"></span>
                                <form method="post" action="${pageContext.request.contextPath}/reservation/rent/${reservation.id}" style="display: none;"></form>
                            </a>
                          </div>
                        </td>
                    </tr>
                </c:forEach>
              </tbody></table>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
      </div>
    </section><!-- /.content -->

</jsp:attribute>
</own:masterpage>   
    
