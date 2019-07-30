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
            <h2 class="text-center">Simulation Setup</h2>

            <hr/>
            <form action="<%= request.getContextPath()%>/simulation_set_up/set_up" method="post">

                <div class="row">
                    <div class="col-md-3 text-center">
                        <h3>New Unit</h3>
                    </div>
                    <div class="col-md-3">
                        <label class="justify-content-center text-uppercase pb-1">Kind</label>
                        <input class="form-control" name="unitName" placeholder="eg. temp_machine_no_1">
                    </div>
                    <div class="col-md-3 text-center">
                        <h3>Function</h3>
                    </div>
                    <div class="col-md-3">
                        <label class="justify-content-center text-uppercase pb-1">Simulation Function</label>
                        <select class="form-control" required name="simulationFunction">
                            <option selected hidden>Choose here</option>
                            <c:forEach items="${classNames}" var="className">
                                <option class="form-control">${className}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <hr/>


                <div class="row">
                    <h3 class="col-md-3 text-center">Interval</h3>
                    <div class="col-md-3">
                        <label class="justify-content-md-start text-uppercase pb-1">Starting Bound</label>
                        <input required class="form-control" name="lowerIntervalBound" placeholder="eg. 20">
                    </div>
                    <div class="col-md-3">
                        <label class="justify-content-md-start text-uppercase pb-1">Target Bound</label>
                        <input required class="form-control" name="upperIntervalBound" placeholder="eg. 30">
                    </div>
                    <div class="col-md-3">
                        <label class="justify-content-md-start text-uppercase pb-1">Period</label>
                        <input required class="form-control" name="period" placeholder="eg. 10">
                    </div>
                </div>

                <hr/>
                <div class="row">
                    <h3 class="col-md-3 text-center">Noise</h3>
                    <div class="col-md-3">
                        <label class="justify-content-md-start text-uppercase pb-1">Lower Bound</label>
                        <input required class="form-control" name="lowerNoiseBound" placeholder="eg. 0.5">
                    </div>
                    <div class="col-md-3">
                        <label class="justify-content-md-start text-uppercase pb-1">Upper Bound</label>
                        <input required class="form-control" name="upperNoiseBound" placeholder="eg. 0.5">
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div class="col-md-4"></div>
                    <button class="col-md-4  btn btn-secondary btn-lg btn-block" type="submit" id="submit-input-params">
                        Submit
                    </button>
                    <div class="col-md-4">
                        <div class="text-center">
                            <label class="justify-content-md-start text-uppercase pb-1">Save Setup to database</label>
                            <input type="radio" name="saveToDatabase" class="form-control " value="true">
                            <input type="radio" name="saveToDatabase" hidden checked="checked" value="false">
                        </div>
                    </div>

                </div>

            </form>


            <hr/>

            <h2 class="text-center">Additional Interval</h2>
            <hr/>

            <form class="" action="<%= request.getContextPath()%>/simulation_set_up/interval" method="post">
                <div class="row">
                    <div class="col-md-3">
                        <label class="justify-content-center text-uppercase pb-1">Kind</label>
                        <select class="form-control" required name="unitName">
                            <option selected hidden>Choose here</option>
                            <c:forEach items="${availableSimulations}" var="simulationId">
                                <option class="form-control">${simulationId}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-3">
                        <label class="justify-content-md-start text-uppercase pb-1">Starting Bound</label>
                        <input required class="form-control" name="lowerIntervalBound" placeholder="eg. 20">
                    </div>
                    <div class="col-md-3">
                        <label class="justify-content-md-start text-uppercase pb-1">Target Bound</label>
                        <input required class="form-control" name="upperIntervalBound" placeholder="eg. 30">
                    </div>
                    <div class="col-md-3">
                        <label class="justify-content-md-start text-uppercase pb-1">Period</label>
                        <input required class="form-control" name="period" placeholder="eg. 10">
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div class="col-md-4"></div>
                    <button class="col-md-4  btn btn-secondary btn-lg btn-block" type="submit"
                            id="submit-additional-interval">Submit
                    </button>
                    <div class="col-md-4">
                        <div class="text-center">
                            <label class="justify-content-md-start text-uppercase pb-1">Save Setup to database</label>
                            <input type="radio" name="saveToDatabase" class="form-control " value="true">
                            <input type="radio" name="saveToDatabase" hidden checked="checked" value="false">
                        </div>
                    </div>
                </div>

            </form>

            <hr/>
        </div>
    </div>
    <c:import url="include/footer.jsp"/>
</div>
<%--<script type="text/javascript" src="resources/js/scriptConfiguration.js"></script>--%>
</body>
</html>