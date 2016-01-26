<%-- 
    Document   : detail
    Created on : 24.1.2016, 13:48:31
    Author     : ErikHasprunár
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="own" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<own:masterpage title="Employee">
<jsp:attribute name="body">
    
    <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            Employees
            <small>Administration of employees</small>
          </h1>
        </section>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/plugins/colorpicker/bootstrap-colorpicker.min.css">

        <!-- Main content -->
        <section class="content">
          <div class="row">
            <div class="col-md-8">
              <div class="box box-primary">
                <div class="box-header with-border">
                  <h3 class="box-title">Employee edit</h3>
                </div>
                <!-- /.box-header -->
                <!-- form start -->
                <form:form method="post" action="${pageContext.request.contextPath}/employee/create" modelAttribute="employee">
                  <div class="box-body">
                    <div class="form-group">
                      <form:hidden path="id" cssClass="form-control" />
                    </div>
                    <div class="form-group">
                      <form:label path="name">Name</form:label>
                      <form:input path="name" cssClass="form-control" placeholder="Enter employee name" />
                      <form:errors path="name" cssClass="help-block"/>
                    </div>
                    <div class="form-group">
                      <form:label path="email">E-mail</form:label>
                      <form:select path="email" cssClass="form-control" placeholder="Enter the employee's e-mail"/>
                    </div>
                    <div class="form-group">
                      <form:label path="email">Password</form:label>
                      <form:select path="email" cssClass="form-control" placeholder="Enter the employee's password"/>
                    </div>
                    <div class="form-group">
                        <form:label path="birth">Birth Date</form:label>
                        <div class="input-group">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                            <fmt:formatDate value="${birth}" pattern="yyyy-MM-dd"/>
                            
                            <fmt:formatDate value="${employee.birth}"  
				                type="date" 
				                pattern="dd-MM-yyyy"
				                var="theFormattedDate" />
                        
                            <form:input path="birth" value="${theFormattedDate}" data-inputmask="'alias': 'dd-mm-yyyy'" type="text" class="form-control my-little-date-mask"/>
                        </div>
                    </div>
                    <div class="form-group">
                      <form:label path="idCardNumber">Card ID</form:label>
                      <form:input path="idCardNumber" cssClass="form-control" placeholder="Enter card ID" />
                      <form:errors path="idCardNumber" cssClass="help-block"/>
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

        <script>
          //color picker with addon
          $(".my-colorpicker2").colorpicker();
        </script>

</jsp:attribute>
</own:masterpage>