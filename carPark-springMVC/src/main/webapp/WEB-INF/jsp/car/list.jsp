<%-- 
    Document   : list
    Created on : Dec 16, 2015, 18:21:32 PM
    Author     : xcmarko
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="own" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<own:masterpage title="Cars">
<jsp:attribute name="body">
    
    
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        <fmt:message key="car.cars"/>
        <small><fmt:message key="car.admin"/></small>
      </h1>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title"><fmt:message key="car.allCars"/></h3>

              <div class="box-tools">
                <div class="input-group input-group-sm right" style="width: 48px;">
                    <c:if test="${authenticatedUser.isAdmin==true}">
                  <a href="${pageContext.request.contextPath}/car/detail/">
                    <button type="button" class="btn btn-info btn-flat" title='<fmt:message key="car.add"/>'><span class="glyphicon glyphicon-plus"></span></button>
                  </a>
                  </c:if>
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
                  <c:if test="${authenticatedUser.isAdmin==true}"><th><fmt:message key="example.actions"/></th></c:if>
                </tr>
                <c:forEach items="${allCars}" var="car">
                    <tr>
                        <td><c:out value="${car.id}"/>.</td>
                        <td><c:out value="${car.name}"/></td>
                        <td><c:out value="${car.transmission}"/></td>
                        <td><c:out value="${car.fuel}"/></td>
                        <td><c:out value="${car.color}"/></td>
                        <c:if test="${authenticatedUser.isAdmin==true}"><td class="actions">
                          <div class="center">
                            <a href="${pageContext.request.contextPath}/car/detail/${car.id}" title='<fmt:message key="employee.edit"/>'><span class="glyphicon glyphicon-edit"></span></a>
                            <a href="#" title='<fmt:message key="employee.remove"/>' class="removeItem">
                                <span class="glyphicon glyphicon-trash"></span>
                                <form method="post" action="${pageContext.request.contextPath}/car/delete/${car.id}" style="display: none;">
                                </form>
                            </a>
                          </div>
                        </td>
                        </c:if>
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