<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="own" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<own:masterpage title="Rentals">
<jsp:attribute name="body">

    <div class="jumbotron">
        <h1><fmt:message key="rental.rentals"/></h1>
    </div>
    
    <div class="padd row">
        <table class="table">
        <thead>
        <tr>
            <th><fmt:message key="rental.id"/></th>
            <th><fmt:message key="rental.employee"/></th>
            <th><fmt:message key="rental.car"/></th>
            <th><fmt:message key="rental.from"/></th>
            <th><fmt:message key="rental.to"/></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${rentals}" var="rental">
            <tr>
                <td><c:out value="${rental.id}"/></td>
                <td><c:out value="${rental.employee}"/></td>
                <td><c:out value="${rental.car}"/></td>
                <td><fmt:formatDate value="${rental.from}" pattern="yyyy-MM-dd"/></td>
                <td><fmt:formatDate value="${rental.to}" pattern="yyyy-MM-dd"/></td>
            </tr>
        </c:forEach>
           
        </tbody>
    </table>
    </div>

</jsp:attribute>
</own:masterpage>   
    