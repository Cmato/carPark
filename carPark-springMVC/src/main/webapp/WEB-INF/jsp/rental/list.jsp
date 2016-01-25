<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="own" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<own:masterpage title="Rentals">
<jsp:attribute name="body">
    
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        <fmt:message key="rental.rentals"/>
        <small><fmt:message key="rental.admin"/></small>
      </h1>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title"><fmt:message key="rental.allRentals"/></h3>

              <div class="box-tools">
                <div class="input-group input-group-sm right" style="width: 48px;">
                    <a href="${pageContext.request.contextPath}/rental/detail/">
                        <button type="button" class="btn btn-info btn-flat" title="Add Rental"><span class="glyphicon glyphicon-plus"></span></button>
                    </a>
                </div>
              </div>
            </div>
            <!-- /.box-header -->
            <div class="box-body table-responsive no-padding">
              <table class="table table-hover">
                <tbody><tr>
                  <th><fmt:message key="rental.id"/></th>
                  <th><fmt:message key="rental.from"/></th>
                  <th><fmt:message key="rental.to"/></th>
                  <th><fmt:message key="rental.returnDate"/></th>
                  <th><fmt:message key="rental.employee"/></th>
                  <th><fmt:message key="rental.car"/></th>
                  <th><fmt:message key="rental.state"/></th>
                  <th>Actions</th>
                </tr>
                <c:forEach items="${rentals}" var="rental">
                    <tr>
                        <td><c:out value="${rental.id}"/>.</td>
                        <td><fmt:formatDate value="${rental.startingDate}" pattern="dd-MM-YYYY"/></td>
                        <td><fmt:formatDate value="${rental.estimatedReturnDate}" pattern="dd-MM-YYYY"/></td>
                        <td><fmt:formatDate value="${rental.returnDate}" pattern="dd-MM-YYYY"/></td>
                        <td><c:out value="${rental.employee.getName()}"/></td>
                        <td><c:out value="${rental.car.getName()}"/></td>
                        <td><c:out value="${rental.rentalState}"/></td>
                        <td class="actions">
                          <div class="center">
                            <a href="${pageContext.request.contextPath}/rental/detail/${rental.id}" title="Edit">
                                <span class="glyphicon glyphicon-edit"></span>
                            </a>
                            <a href="#" title="Remove" class="removeItem">
                                <span class="glyphicon glyphicon-trash"></span>
                                <form method="post" action="${pageContext.request.contextPath}/rental/delete/${rental.id}" style="display: none;">
                                </form>
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
    