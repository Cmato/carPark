<%-- 
    Document   : detail
    Created on : 24.1.2016, 13:48:31
    Author     : ErikHasprunár
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="own" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<own:masterpage>
<jsp:attribute name="body">

    <div class="jumbotron">
        <h1>Employees section</h1>
    </div>
    
    <div class="row">
        <h1>Administration section</h1>
        <p><a class="btn btn-lg btn-primary" href="${pageContext.request.contextPath}/employee/new" role="button">Create employee</a></p>
        <p><a class="btn btn-lg btn-primary" href="${pageContext.request.contextPath}/employee/listAsAdmin" role="button">Manage employees</a></p>
    </div>
    <div class="row">
        <h1>User section</h1>
        <p><a class="btn btn-lg btn-primary" href="${pageContext.request.contextPath}/employee/list" role="button">View all employees</a></p>
        <p><a class="btn btn-lg btn-primary" href="${pageContext.request.contextPath}/employee/find" role="button">Find employees</a></p>
    </div>

</jsp:attribute>
</own:masterpage>