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
            <fmt:message key="employee.employees"/>
            <small><fmt:message key="employee.admin"/></small>
          </h1>
        </section>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/plugins/colorpicker/bootstrap-colorpicker.min.css">

        <!-- Main content -->
        <section class="content">
          <div class="row">
            <div class="col-md-8">
              <div class="box box-primary">
                <div class="box-header with-border">
                  <h3 class="box-title"><fmt:message key="employee.edit"/></h3>
                </div>
                <!-- /.box-header -->
                <!-- form start -->
                <form:form method="post" action="${pageContext.request.contextPath}/employee/create" modelAttribute="employee">
                  <div class="box-body">
                    <div class="form-group">
                      <form:hidden path="id" cssClass="form-control" />
                    </div>
                    <div class="form-group">
                      <form:label path="name"><fmt:message key="example.name"/></form:label>
                      <fmt:message key="employee.enterName" var="enterName"/>
                      <form:input path="name" id="name" name="name" cssClass="form-control" placeholder="${enterName}" />
                      <form:errors path="name" cssClass="help-block"/>
                    </div>
                    <div class="form-group">
                      <form:label path="email">E-mail</form:label>
                      <fmt:message key="employee.enterEmail" var="enterEmail"/>
                      <form:input path="email"  id="email" name="email" cssClass="form-control" placeholder="${enterEmail}"/>
                      <form:errors path="email" cssClass="help-block"/>
                    </div>
                    <div class="form-group">
                      <form:label path="password"><fmt:message key="employee.password"/></form:label>
                      <fmt:message key="employee.enterPassword" var="enterPassword"/>
                      <form:password path="password" id="password" name="password" cssClass="form-control" placeholder="${enterPassword}"/>
                      <form:errors path="password" cssClass="help-block"/>
                    </div>
                    <div class="form-group">
                        <form:label path="birth"><fmt:message key="employee.birthDate"/></form:label>
                        <div class="input-group">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                            
                            <fmt:formatDate value="${employee.birth}"  
				                type="date" 
				                pattern="dd-MM-yyyy"
				                var="theFormattedDate" />
                        
                            <form:input path="birth" id="birth" name="birth" value="${theFormattedDate}" data-inputmask="'alias': 'dd-mm-yyyy'" type="text" class="form-control my-little-date-mask"/>
                        </div>
                    </div>
                    <div class="form-group">
                      <form:label path="idCardNumber"><fmt:message key="employee.idCard"/></form:label>
                      <fmt:message key="employee.enterIdCard"  var="enterIdCard"/>
                      <form:input path="idCardNumber" id="idCardNumber" name="idCardNumber" cssClass="form-control" placeholder="${enterIdCard}" />
                      <form:errors path="idCardNumber" cssClass="help-block"/>
                    </div>
                  </div>
                  <!-- /.box-body -->

                  <div class="box-footer">
                    <button type="submit" class="btn btn-primary"><fmt:message key="rental.submit"/></button>
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
          //color picker with addon
          $(".my-colorpicker2").colorpicker();
          $('.my-little-date-mask').inputmask("dd-mm-yyyy", {"placeholder": "dd-mm-yyyy"});
        </script>

</jsp:attribute>
</own:masterpage>