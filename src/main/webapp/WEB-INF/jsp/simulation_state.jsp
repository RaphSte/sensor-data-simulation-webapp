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

        <c:if test="${empty availableSimulations}">
            <c:import url="include/no_simulation.jsp"/>

        </c:if>


        <c:forEach items="${availableSimulations}" var="simulationId">
            <c:set var="stateId">state${simulationId}</c:set>
            <c:set var="stateId">${requestScope[stateId]}</c:set>
            <c:set var="simulationState">${stateId.concat("")}</c:set>

            <div class="container">
                <div class="row">

                    <div class="col-md-7 container jumbotron mb-0 pb-4 mt-5 pt-3">


                        <div class="row">
                            <div class="col-md">
                                <h3 class="text-center">State Simulation '${simulationId}'</h3>
                            </div>
                        </div>

                        <hr/>

                        <div class="row">

                            <div class="col-md-3">
                                <h4>State: ${stateId}</h4>
                            </div>

                            <div class="col-md-4">
                                <p class="alert font-weight-bold
                    <c:choose>
                        <c:when test='${stateId eq "running"}'>
                            alert-success
                        </c:when>
                        <c:when test='${stateId eq "transition"}'>
                            alert-warning
                        </c:when>
                        <c:when test='${stateId eq "stopped"}'>
                            alert-info
                        </c:when>
                        <c:otherwise>
                        <%--should never be here--%>
                            alert-danger">Unknown
                                </p>
                                <p class="
                        </c:otherwise>
                    </c:choose>
                      ">
                                        ${stateId}
                                </p>
                            </div>
                            <div class="col-md-4">
                                <form action="<%= request.getContextPath()%>/simulation_state" method="post">
                                    <input type="hidden" name="unitName" value="${simulationId}">
                                    <button class="btn btn-secondary btn-lg btn-block"
                                            <c:if test='${stateId eq "stopped"}'>disabled</c:if>
                                            type="submit"
                                            name="start" value="0">Stop Simulation
                                    </button>
                                </form>
                            </div>
                        </div>

                        <hr/>
                        <form class="row" action="<%= request.getContextPath()%>/simulation_state" method="post">
                            <div class="col-md-3">
                                <h4>Start new Simulation</h4>
                            </div>
                            <div class="col-md-4">
                                <input readonly class="form-control disabled" name="unitName" value="${simulationId}">
                            </div>
                            <div class="col-md-4">
                                <button class="btn btn-secondary btn-lg btn-block" type="submit"
                                        <c:if test='${stateId eq "running"}'>disabled</c:if>
                                        name="start" value="1">Start Simulation
                                </button>
                            </div>
                        </form>

                        <hr/>

                        <form class="row" action="<%= request.getContextPath()%>/simulation_state" method="post" autocomplete="off">
                            <div class="col-md-3">
                                <h4>Start new Transition</h4>
                            </div>
                            <div class="col-md-4">
                                <input readonly class="form-control disabled" name="unitName" value="${simulationId}">
                            </div>
                            <div class="col-md-4">
                                <button class="btn btn-secondary btn-lg btn-block"
                                        type="submit"
                                        name="start" value="2">Start Transition
                                </button>
                            </div>
                        </form>

                    </div>

                    <div class="container jumbotron mt-5 mb-0 pt-5 col-md-4">
                        <div id="graph-${simulationId}" name="simulation-graph"
                             style="width:100%; height:100%;"></div>
                    </div>

                </div>

            </div>

        </c:forEach>
    </div>
    <script type="text/javascript" src="resources/js/echarts-en.common.min.js"></script>
    <script type="text/javascript" src="resources/js/simulationState.js"></script>
    <c:import url="include/footer.jsp"/>
</div>
<%--<script type="text/javascript" src="resources/js/scriptConfiguration.js"></script>--%>
</body>
</html>