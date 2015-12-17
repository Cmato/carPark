<%-- 
    Document   : detail
    Created on : 16.12.2015, 18:00:36
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
    
    <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            Cars
            <small>Administration of cars</small>
          </h1>
        </section>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/plugins/colorpicker/bootstrap-colorpicker.min.css">

        <!-- Main content -->
        <section class="content">
          <div class="row">
            <div class="col-md-8">
              <div class="box box-primary">
                <div class="box-header with-border">
                  <h3 class="box-title">Car edit</h3>
                </div>
                <!-- /.box-header -->
                <!-- form start -->
                <form role="form">
                  <div class="box-body">
                    <div class="form-group">
                      <label for="carName">Name</label>
                      <input type="text" class="form-control" id="carName" value="${car.name}" placeholder="Enter car name">
                    </div>
                    <div class="form-group">
                      <label>Transmission</label>
                      <select class="form-control">
                        <option value="Automatic">Automatic</option>
                        <option value="Mnual">Manual</option>
                      </select>
                    </div>
                    <div class="form-group">
                      <label>Fuel</label>
                      <select class="form-control">
                        <option value="Petrol">Petrol</option>
                        <option value="Diesel">Diesel</option>
                      </select>
                    </div>
                    <div class="form-group">
                      <label>Color picker with addon:</label>

                      <div class="input-group my-colorpicker2 colorpicker-element">
                        <input type="text" id="carColor" value="${car.color}" class="form-control">

                        <div class="input-group-addon">
                          <i style="background-color: rgb(0, 0, 0);"></i>
                        </div>
                      </div>
                      <!-- /.input group -->
                    </div>
                  </div>
                  <!-- /.box-body -->

                  <div class="box-footer">
                    <button type="submit" class="btn btn-primary">Submit</button>
                  </div>
                </form>
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