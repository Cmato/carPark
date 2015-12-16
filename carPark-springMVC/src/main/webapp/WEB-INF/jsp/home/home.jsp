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
    
    
    <section class="content-header">
        <h1>
            <fmt:message key="example.appTitle"/>
            <small><fmt:message key="example.appTitle"/></small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
            <li class="active"><fmt:message key="example.appTitle"/></li>
        </ol>
    </section>

    <!-- Main content -->
    <section class="content">

        <h1>Hello World!</h1>
        <p><fmt:message key="example.appTitle"/></p>
        <p><a class="btn btn-lg btn-primary" target="_blank" href="${pageContext.request.contextPath}/car/list" role="button">View all cars</a></p>
    

    </section><!-- /.content -->
    

</jsp:attribute>
</own:masterpage>
