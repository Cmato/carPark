<%-- 
    Document   : detail
    Author     : xruzic16
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="own" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<own:masterpage title="Reservation">
<jsp:attribute name="body">
    
    <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            Reservation
            <small>Administration of reservations</small>
          </h1>
        </section>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/plugins/colorpicker/bootstrap-colorpicker.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/plugins/daterangepicker/daterangepicker-bs3.css">

        <!-- Main content -->
        <section class="content">
          <div class="row">
            <div class="col-md-8">
              <div class="box box-primary">
                <div class="box-header with-border">
                  <h3 class="box-title">Reservation edit</h3>
                  <c:choose>
                      <c:when test="${reservation.reservationState=='ACTIVE'}">
                          <form method="post" action="${pageContext.request.contextPath}/reservation/cancel/${reservation.id}">
                              <button type="submit" class="btn btn-primary">Cancel</button>
                          </form>
                      </c:when>
                  </c:choose>
                </div>
                <!-- /.box-header -->
                <!-- form start -->
                <form:form method="post" action="${pageContext.request.contextPath}/reservation/create" modelAttribute="reservation">
                  <div class="box-body">
                    <div class="form-group">
                      <form:hidden path="id" cssClass="form-control" />
                    </div>
                    <div class="form-group">
                      <form:label path="employee.id">Employee</form:label>
                        <form:select path="employee.id" cssClass="form-control">
                            <c:forEach items="${employees}" var="employee">
                                <form:option value="${employee.id}">${employee.name}</form:option>
                            </c:forEach>
                        </form:select>
                    </div>
                    <div class="form-group">
                      <form:label path="car.id"><fmt:message key="rental.car"/></form:label>
                        <form:select path="car.id" cssClass="form-control">
                            <c:forEach items="${cars}" var="car">
                                <form:option value="${car.id}">${car.name}</form:option>
                            </c:forEach>
                        </form:select>
                    </div>
                    
                    <div class="form-group">
                        <form:label path="startingDate">Start Date</form:label>
                        <div class="input-group">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                            
                            <fmt:formatDate value="${reservation.startingDate}"  
				                type="date" 
				                pattern="dd-MM-yyyy"
				                var="theFormattedDate" />
                        
                            <form:input path="startingDate" id="startingDate" value="${theFormattedDate}" data-inputmask="'alias': 'dd-mm-yyyy'" type="text" cssClass="form-control my-little-date-mask" />
                        </div>
                    </div>
                    
                    <div class="form-group">
                      <form:label path="endingDate">End Date</form:label>
                        <div class="input-group">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                            <fmt:formatDate value="${reservation.endingDate}"  
                                type="date" 
                                pattern="dd-MM-yyyy"
                                var="theFormattedDate2" />
                            
                            <form:input path="endingDate" value="${theFormattedDate2}" data-inputmask="'alias': 'dd-mm-yyyy'" type="text" class="form-control my-little-date-mask"/>
                        </div>
                      <!-- /.input group -->
                    </div>
                  </div>
                  <!-- /.box-body -->

                  <div class="box-footer">
                    <button type="submit" class="btn btn-primary">Submit</button>
                  </div>
                </form:form>
              </div>
             
            </div>
          </div>
        </section><!-- /.content -->

        <!-- bootstrap color picker -->
        <script src="${pageContext.request.contextPath}/static/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
        <script src="${pageContext.request.contextPath}/static/plugins/input-mask/jquery.inputmask.js"></script>
        <script src="${pageContext.request.contextPath}/static/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
        <script src="${pageContext.request.contextPath}/static/plugins/input-mask/jquery.inputmask.extensions.js"></script>

        <script>
          //<!-- color picker with addon -->
          $(".my-colorpicker2").colorpicker();
          $('.my-little-date-mask').inputmask("dd-mm-yyyy", {"placeholder": "dd-mm-yyyy"});
        </script>

</jsp:attribute>
</own:masterpage>