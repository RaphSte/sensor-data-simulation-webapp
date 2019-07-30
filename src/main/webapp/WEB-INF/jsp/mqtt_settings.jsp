<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en-GB">
<c:import url="include/head.jsp"/>

<body>
<div class="page">
    <c:import url="include/header.jsp"/>
    <c:import url="include/menu.jsp"/>

    <div class="content">
        <div class="container jumbotron mt-3 pt-3 pb-2">
            <h2 class="text-center">MQTT Settings</h2>
            <hr/>

            <form action="<%= request.getContextPath()%>/mqtt_settings" method="post">


                <div class="row">
                    <div class="col-md-3 text-center">
                        <h4>SimulationID</h4>
                    </div>
                    <div class="col-md-3">
                        <select class="form-control" required name="simulationId">
                            <option selected hidden>Choose here</option>
                            <c:forEach items="${availableSimulations}" var="simulationId">
                                <option class="form-control">${simulationId}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-3 text-center">
                        <h4>Server Uri</h4>
                    </div>
                    <div class="col-md-3">
                        <div class="text-center">
                            <input class="form-control" name="serverUri" placeholder="tcp://ex.ample.com:1234">
                        </div>
                    </div>
                </div>

                <hr/>

                <div class="row">
                    <div class="col-md-3 text-center">
                        <h4>Connection Timeout</h4>
                    </div>
                    <div class="col-md-3">
                        <div class="text-center">
                            <input type="number" class="form-control" name="connectionTimeOut" value="10">
                        </div>
                    </div>
                    <div class="col-md-3 text-center">
                        <h4>Automatic reconnect</h4>
                    </div>
                    <div class="col-md-3">
                        <select class="form-control" required name="automaticReconnect">
                            <option selected>enabled</option>
                            <option>disabled</option>
                        </select>
                    </div>
                </div>

                <hr/>

                <div class="row">

                    <div class="col-md-3 text-center">
                        <h4>Clean Session</h4>
                    </div>
                    <div class="col-md-3">
                        <select class="form-control" required name="cleanSession">
                            <option value="true" selected>enabled</option>
                            <option value="false">disabled</option>
                        </select>
                    </div>


                </div>
                <hr/>
                <div class="row">
                    <div class="col-md-4"></div>
                    <button class="col-md-4 mb-4 btn btn-secondary btn-lg btn-block" type="submit">Submit
                    </button>
                </div>

            </form>

        </div>
    </div>
    <c:import url="include/footer.jsp"/>
</div>
<%--<script type="text/javascript" src="resources/js/scriptConfiguration.js"></script>--%>
</body>
</html>