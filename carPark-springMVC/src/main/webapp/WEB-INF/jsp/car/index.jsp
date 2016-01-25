<%-- 
    Document   : index
    Created on : 16.12.2015, 18:30:10
    Author     : xcmarko
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="own" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<own:masterpage>
<jsp:attribute name="body">

    <div class="jumbotron">
        <h1>Cars section</h1>
    </div>
    
    <div class="row">
        <h1>Administration section</h1>
        <p><a class="btn btn-lg btn-primary" href="${pageContext.request.contextPath}/car/new" role="button">Create car</a></p>
        <p><a class="btn btn-lg btn-primary" href="${pageContext.request.contextPath}/car/listAsAdmin" role="button">Manage cars</a></p>
    </div>
    <div class="row">
        <h1>User section</h1>
        <p><a class="btn btn-lg btn-primary" href="${pageContext.request.contextPath}/car/list" role="button">View all cars</a></p>
        <p><a class="btn btn-lg btn-primary" href="${pageContext.request.contextPath}/car/find" role="button">Find cars</a></p>
    </div>

</jsp:attribute>
</own:masterpage>