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
        
        <div class="col-md-12">
            
            <div class="box">
                
                <div class="box-header with-border">
                    <h3 class="box-title"><fmt:message key="example.allCars"/></h3>
                </div>
                
                <!-- /.box-header -->
                <div class="box-body">
                  
                    <table class="table table-bordered">
                        <tbody>
                            <tr>
                                <th><fmt:message key="example.name"/></th>
                                <th><fmt:message key="example.color"/></th>
                                <th><fmt:message key="example.transmission"/></th>
                                <th><fmt:message key="example.fuel"/></th>
                                <th></th>
                            </tr>
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
                <!-- /.box-body -->
                <div class="box-footer clearfix">
                    <ul class="pagination pagination-sm no-margin pull-right">
                        <li><a href="#">«</a></li>
                        <li><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">»</a></li>
                    </ul>
                </div>
            </div>
                                
        </div>
    </div>

</jsp:attribute>
</own:masterpage>