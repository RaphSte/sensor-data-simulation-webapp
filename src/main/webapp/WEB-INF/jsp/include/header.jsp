<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <div class="container-fluid">
        <div class="row">
            <a href="<%= request.getContextPath()%>/home" class="col-md-3">
                <img class="logo-image" src="resources/images/logo_pos.svg" alt="logo of htwg">
            </a>
            <h1 class="header-title col-md-6 text-center ">Sensor Data Simulator</h1>
            <div class="col-md-3">
                <img class="logo-image row overflow-hidden" src="resources/images/Sybit-Logo-RGB.svg"
                     alt="logo of Sybit">
            </div>
        </div>
    </div>
</header>
<link rel="stylesheet" href="resources/css/sybit.css"/>
<c:if test="${not empty infoMsg}">
    <div class="container-fluid position-absolute">
        <div class="alert text-center alert-success foreground" id="success-alert">${infoMsg}</div>
    </div>
</c:if>
<c:if test="${not empty errorMsg}">
    <div class="container-fluid position-absolute">
        <div class="alert text-center alert-danger foreground" id="danger-alert">${errorMsg}</div>
    </div>
</c:if>
