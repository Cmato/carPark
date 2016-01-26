<<<<<<< HEAD
<%-- 
    Document   : login
    Author     : xruzic16
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="own" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<own:masterpage title="Login Page">
<jsp:attribute name="body">
    
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Login page
        <small>Employee login page</small>
      </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <h4>Test version - login as admin</h4>
        <form:form class="form-horizontal" role="form" method="post" modelAttribute="authenticateUser" 
            action="${pageContext.request.contextPath}/login">
            <div class="form-group">
                <label class="control-label col-sm-2" for="email">Employee email:</label>
                <div class="col-sm-10">
                    <form:input type="email" class="form-control" path="email" id="email" value="admin@mail.com" placeholder="Enter email" />
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="password">Password:</label>
                <div class="col-sm-10">
                    <form:input type="password" path="password" class="form-control" value="password" id="password" />
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Login</button>
                </div>
            </div>
        </form:form>
        
        <h4>Test version - login as user</h4>
        <form:form class="form-horizontal" role="form" method="post" modelAttribute="authenticateUser" 
            action="${pageContext.request.contextPath}/login">
            <div class="form-group">
                <label class="control-label col-sm-2" for="email">Employee email:</label>
                <div class="col-sm-10">
                    <form:input type="email" class="form-control" path="email" id="email" value="user@mail.com" placeholder="Enter email" />
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="password">Password:</label>
                <div class="col-sm-10">
                    <form:input type="password" path="password" class="form-control" value="password" id="password" />
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Login</button>
                </div>
            </div>
        </form:form>
    </section><!-- /.content -->

</jsp:attribute>
=======
<%-- 
    Document   : login
    Author     : xruzic16
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="own" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<own:masterpage>
<jsp:attribute name="body">
    
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        <fmt:message key="login.loginpage"/>
        <small><fmt:message key="login.emplloginpage"/></small>
      </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <h4><fmt:message key="login.testAdmin"/></h4>
        <form:form class="form-horizontal" role="form" method="post" modelAttribute="authenticateUser" 
            action="${pageContext.request.contextPath}/login">
            <div class="form-group">
                <label class="control-label col-sm-2" for="email"><fmt:message key="login.emplMail"/></label>
                <div class="col-sm-10">
                    <form:input type="email" class="form-control" path="email" id="email" value="admin@mail.com" placeholder="Enter email" />
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="password"><fmt:message key="login.pass"/></label>
                <div class="col-sm-10">
                    <form:input type="password" path="password" class="form-control" value="password" id="password" />
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default"><fmt:message key="login.login"/></button>
                </div>
            </div>
        </form:form>
        
        <h4><fmt:message key="login.testUser"/></h4>
        <form:form class="form-horizontal" role="form" method="post" modelAttribute="authenticateUser" 
            action="${pageContext.request.contextPath}/login">
            <div class="form-group">
                <label class="control-label col-sm-2" for="email"><fmt:message key="login.emplMail"/></label>
                <div class="col-sm-10">
                    <form:input type="email" class="form-control" path="email" id="email" value="user@mail.com" placeholder="Enter email" />
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="password"><fmt:message key="login.pass"/></label>
                <div class="col-sm-10">
                    <form:input type="password" path="password" class="form-control" value="password" id="password" />
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default"><fmt:message key="login.login"/></button>
                </div>
            </div>
        </form:form>
    </section><!-- /.content -->

</jsp:attribute>
>>>>>>> origin/master
</own:masterpage>