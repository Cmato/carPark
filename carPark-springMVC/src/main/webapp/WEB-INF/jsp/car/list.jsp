<%-- 
    Document   : list
    Created on : Dec 16, 2015, 18:21:32 PM
    Author     : xcmarko
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="own" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<own:masterpage>
<jsp:attribute name="body">
    
    
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Cars
        <small>Administration of cars</small>
      </h1>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">All cars in system</h3>

              <div class="box-tools">
                <div class="input-group input-group-sm right" style="width: 48px;">
                  <button type="button" class="btn btn-info btn-flat" title="Add Car"><span class="glyphicon glyphicon-plus"></span></button>
                </div>
              </div>
            </div>
            <!-- /.box-header -->
            <div class="box-body table-responsive no-padding">
              <table class="table table-hover">
                <tbody><tr>
                  <th>ID</th>
                  <th><fmt:message key="example.name"/></th>
                  <th><fmt:message key="example.transmission"/></th>
                  <th><fmt:message key="example.fuel"/></th>
                  <th><fmt:message key="example.color"/></th>
                  <th>Actions</th>
                </tr>
                <c:forEach items="${allCars}" var="car">
                    <tr>
                        <td><c:out value="${car.id}"/>.</td>
                        <td><c:out value="${car.name}"/></td>
                        <td><c:out value="${car.transmission}"/></td>
                        <td><c:out value="${car.fuel}"/></td>
                        <td><c:out value="${car.color}"/></td>
                        <td class="actions">
                          <div class="center">
                            <a href="${pageContext.request.contextPath}/car/detail/${car.id}" title="edit"><span class="glyphicon glyphicon-edit"></span></a>
                            <a href="#" title="remove"><span class="glyphicon glyphicon-trash"></span></a>
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