<%-- 
    Document   : list.jsp
    Created on : 17.12.2015, 17:50:12
    Author     : xhasprun
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="own" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<own:masterpage title="Employees">
<jsp:attribute name="body">
    
    
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        <fmt:message key="employee.employees"/>
        <small><fmt:message key="employee.admin"/></small>
      </h1>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title"><fmt:message key="employee.allEmployees"/></h3>

              <div class="box-tools">
                <div class="input-group input-group-sm right" style="width: 48px;">
                  <c:if test="${authenticatedUser.isAdmin==true}">
                  <button type="button" class="btn btn-info btn-flat" title='<fmt:message key="employee.add"/>'><span class="glyphicon glyphicon-plus"></span></button>
                  </c:if>
                </div>
              </div>
            </div>
            <!-- /.box-header -->
            <div class="box-body table-responsive no-padding">
              <table class="table table-hover">
                <tbody><tr>
                  <th>ID</th>
                  <th><fmt:message key="example.name"/></th>
                  <th><fmt:message key="employee.idCard"/></th>
                  <th><fmt:message key="employee.birthDate"/></th>
                  <c:if test="${authenticatedUser.isAdmin==true}"><th><fmt:message key="example.actions"/></th></c:if>
                </tr>
                <c:forEach items="${allEmployees}" var="employee">
                    <tr>
                        <td><c:out value="${employee.id}"/>.</td>
                        <td><c:out value="${employee.name}"/></td>
                        <td><c:out value="${employee.idCardNumber}"/></td>
                        <td><fmt:formatDate value="${employee.birth}" pattern="yyyy-MM-dd"/></td>
                        <c:if test="${authenticatedUser.isAdmin==true}"><td class="actions">
                          <div class="center">
                            <a href="${pageContext.request.contextPath}/employee/detail/${employee.id}" title='<fmt:message key="employee.edit"/>'><span class="glyphicon glyphicon-edit"></span></a>
                            <a href="#" title='<fmt:message key="employee.remove"/>' class="removeItem">
                                <span class="glyphicon glyphicon-trash"></span>
                                <form method="post" action="${pageContext.request.contextPath}/employee/delete/${employee.id}" style="display: none;">
                                </form>
                            </a>
                          </div>
                        </td></c:if>
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
