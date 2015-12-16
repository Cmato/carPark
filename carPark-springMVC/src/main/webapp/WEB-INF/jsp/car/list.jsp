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

    <div class="jumbotron">
        <h1><fmt:message key="example.allCars"/></h1>
    </div>
    
    <div class="padd row">
        <table class="table">
        <thead>
        <tr>
            <th><fmt:message key="example.name"/></th>
            <th><fmt:message key="example.color"/></th>
            <th><fmt:message key="example.transmission"/></th>
            <th><fmt:message key="example.fuel"/></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${allCars}" var="car">
            <tr>
                <td><c:out value="${car.name}"/></td>
                <td><c:out value="${car.color}"/></td>
                <td><c:out value="${car.transmission}"/></td>
                <td><c:out value="${car.fuel}"/></td>
                <td>
                    <form method="get" action="${pageContext.request.contextPath}/car/detail/${car.id}">
                    <button type="submit" class="btn btn-primary"><fmt:message key="example.view"/></button>
                    </form>
                </td>
            </tr>
        </c:forEach>
           
        </tbody>
    </table>
    </div>

</jsp:attribute>
</own:masterpage>