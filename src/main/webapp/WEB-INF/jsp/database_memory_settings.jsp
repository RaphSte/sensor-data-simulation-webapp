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
            <h2 class="text-center">Load form Database</h2>

            <hr/>
            <form action="<%= request.getContextPath()%>/database_memory_settings" method="post">

                <div class="row pb-5">
                    <div class="col-md-4 text-center">
                        <h2>Available Setups:</h2>
                    </div>
                    <div class="col-md-4">
                        <select class="form-control" required name="unitNameKey">
                            <option selected hidden>Choose here</option>
                            <c:forEach items="${availableNameKeys}" var="simulation">
                                <option class="form-control">${simulation}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button class="col-md-4  btn btn-secondary btn-lg btn-block" type="submit" id="submit-input-params">
                        Submit
                    </button>
                </div>
            </form>
        </div>
        <div class="container jumbotron mt-3 pt-3 pb-2">
            <h2 class="text-center">Delete from Database</h2>

            <hr/>
            <form action="<%= request.getContextPath()%>/database_memory_settings/drop" method="post">

                <div class="row pb-5">
                    <div class="col-md-4 text-center">
                        <h2>Available Setups:</h2>
                    </div>
                    <div class="col-md-4">
                        <select class="form-control" required name="unitNameKey">
                            <option selected hidden>Choose here</option>
                            <c:forEach items="${availableNameKeys}" var="simulation">
                                <option class="form-control">${simulation}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button class="col-md-4 btn btn-secondary btn-lg btn-block" type="submit">
                        Submit
                    </button>
                </div>
            </form>
        </div>

        <div class="container jumbotron mt-3 pt-3 pb-2">
            <h2 class="text-center">Save to Database</h2>

            <hr/>
            <form action="<%= request.getContextPath()%>/database_memory_settings/save" method="post">

                <div class="row pb-5">
                    <div class="col-md-4 text-center">
                        <h2>Available Setups:</h2>
                    </div>
                    <div class="col-md-4">
                        <select class="form-control" required name="unitNameKey">
                            <option selected hidden>Choose here</option>
                            <c:forEach items="${availableSimulations}" var="simulation">
                                <option class="form-control">${simulation}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button class="col-md-4 btn btn-secondary btn-lg btn-block" type="submit">
                        Submit
                    </button>
                </div>
            </form>
        </div>


        <div class="container jumbotron mt-3 pt-3 pb-2">
            <h2 class="text-center">Delete from Memory</h2>

            <hr/>
            <form action="<%= request.getContextPath()%>/database_memory_settings/drop_memory" method="post">

                <div class="row pb-5">
                    <div class="col-md-4 text-center">
                        <h2>Available Setups:</h2>
                    </div>
                    <div class="col-md-4">
                        <select class="form-control" required name="unitNameKey">
                            <option selected hidden>Choose here</option>
                            <c:forEach items="${availableSimulations}" var="simulation">
                                <option class="form-control">${simulation}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button class="col-md-4 btn btn-secondary btn-lg btn-block" type="submit">
                        Submit
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