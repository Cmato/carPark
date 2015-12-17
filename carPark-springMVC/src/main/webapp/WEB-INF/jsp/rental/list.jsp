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
        Rentals
        <small>Administration of rentals</small>
      </h1>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">All rentals in system</h3>

              <div class="box-tools">
                <div class="input-group input-group-sm right" style="width: 48px;">
                  <button type="button" class="btn btn-info btn-flat" title="Add Rental"><span class="glyphicon glyphicon-plus"></span></button>
                </div>
              </div>
            </div>
            <!-- /.box-header -->
            <div class="box-body table-responsive no-padding">
              <table class="table table-hover">
                <tbody><tr>
                  <th>ID</th>
                  <th>Starting Date</th>
                  <th>Estimated return Date</th>
                  <th>Return Date</th>
                  <th>Employee</th>
                  <th>Car</th>
                  <th>State</th>
                  <th>Actions</th>
                </tr>
                <c:forEach items="${rentals}" var="rental">
                    <tr>
                        <td><c:out value="${rental.id}"/>.</td>
                        <td><c:out value="${rental.startingDate}"/></td>
                        <td><fmt:formatDate value="${rental.estimatedReturnDate}"/></td>
                        <td><fmt:formatDate value="${rental.returnDate}" pattern="yyyy-MM-dd"/></td>
                        <td><a href="#"><c:out value="${rental.employee.getName()}"/></a></td>
                        <td><a href="#"><c:out value="${rental.car.getName()}"/></a></td>
                        <td><c:out value="${rental.rentalState}"/></td>
                        <td class="actions">
                          <div class="center">
                            <a href="#" title="edit"><span class="glyphicon glyphicon-edit"></span></a>
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
    