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
        <div class="container">
            <br/>
            <div class="row">
                <div class="col-sm">
                    <h2 class="text-center">Home</h2>
                </div>
            </div>
            <hr/>


            <h4 class="text-center">Action</h4>

            <hr/>

            <div class="row">
                <div class="col-md-4">
                    <a class="link-btn navbar-brand btn btn-secondary btn-lg btn-block"
                       href="<%= request.getContextPath() %>/simulation_set_up">Set up
                        Simulation>></a>
                </div>
                <div class="col-md-4">
                    <a class="link-btn navbar-brand btn btn-secondary btn-lg btn-block"
                       href="<%= request.getContextPath()%>/transition_set_up">Set up
                        Transition>></a>
                </div>
                <div class="col-md-4">
                    <a class="link-btn navbar-brand btn btn-secondary btn-lg btn-block"
                       href="<%= request.getContextPath()%>/simulation_state">Check
                        Simulation State>></a>
                </div>
            </div>

            <hr/>
            <div class="row">
                <div class="col-md-4">
                    <p>
                        In this section, you can define your own simulation setup. You must provide a key for your setup
                        that is unique throughout the system.
                        <br>
                        You can choose your simulation style from a set of different simulation functions.*
                        <br>
                        For a valid setup you will need to provide certain values for an interval, such as lower bound
                        or upper bound. These numbers are float numbers and must
                        be formatted either without any float values (e.g. 20) or with a '.' as separator (e.g. 20.5).
                        <br>
                        The noise-specification need a lower and upper boundary as well. These boundaries will determine
                        the random offset, which is added to the Simulated value.
                        <br>
                        In order to finish your setup, you will need to submit your values. Once submitted, the setup
                        will be loaded into the memory of the server and is available for you to simulate.
                        <br>
                        *Different functions might require more than one interval. At the section "Additional Interval",
                        you have the possibility, to add more intervals to your simulation.
                    </p>
                </div>
                <div class="col-md-4">
                    <p> At this section you can set up a transition.
                        <br>
                        A transition basically consists of an interval, to which the simulation transforms to. You
                        can not define a new transition for any value - it has to be one, which is already loaded into
                        the
                        servers memory. You can choose the simulation at a field, out of the available simulations.
                        <br>
                    </p>
                </div>
                <div class="col-md-4">
                    <p>This Section is meant to give you control over all the available simulations. The state of the
                        simulation is displayed for users to see. Here you can start/stop the simulations, as well as
                        start a Transition. In order to start a transition or simulation, it is reqired, that those are
                        available in the servers memory.
                        <br>
                        Running simulations also will send their simulated data to a specified MQTT-broker, which
                        they can also be retrieved of for further processing.
                    </p>
                </div>
            </div>


            <hr/>

            <div class="row">
                <div class="col-md-4">
                    <a class="link-btn navbar-brand btn btn-secondary btn-lg btn-block"
                       href="<%= request.getContextPath()%>/mqtt_settings">MQTT
                        Settings >></a>
                </div>
                <div class="col-md-4">
                    <a class="link-btn navbar-brand btn btn-secondary btn-lg btn-block"
                       href="<%= request.getContextPath()%>/database_memory_settings">Database & Memory Control >></a>
                </div>
                <div class="link-btn col-md-4">
                    <form action="<%= request.getContextPath()%>/home/sampleSetup" method="get">
                        <button value="sampleSetup" type="submit"
                                class="navbar-brand btn btn-secondary btn-lg btn-block">Quick load Sampless
                        </button>
                    </form>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col-md-4">
                    <p>MQTT is the protocol, that is used to send off the simulated values. In order so specify the
                        Settings you need to choose one of the available Simulations. The selected simulation key will
                        also serve as the MQTT-topic, which clients can subscribe to.
                        <br>
                        A Server URI of the desired MQTT-broker needs to be given. This broker will be the one, where
                        the simulated values are send to. They can be retrieved from the broker, using another
                        client, which is subscribed to the according topic (simulation key).
                        <br>
                        It is also possible to define your desired connection timeout, as well as weather an automatic
                        reconnect is enabled or not. Same goes for the "clean session" flag.
                        <br>
                        If no setup is specified, the simulation will fall back to a default connection.
                    </p>
                </div>
                <div class="col-md-4">
                    <p>In ths section you can manage the database and internal server memory. It is possible, to drop
                        contained simulation setups from the database. Same goes for the servers internal memory.
                        <br>
                        It is also possible, to save available simulations to the Database(and Internal memory).
                        <br>
                        In order to do this, you will need to choose one of the given setups at the corresponding
                        section.
                    </p>
                </div>
                <div class="col-md-4">
                    <p>Here you can quick load some predefined sample-setups to tinker around with.
                        <br>
                        Those simulations are only available within the servers memory. You are able to save it to the
                        database. If it is deleted, you can retrieve it by quick loading those setups again.
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="position-sticky">
    <c:import url="include/footer.jsp"/>
</div>
<%--<script type="text/javascript" src="resources/js/scriptConfiguration.js"></script>--%>
</body>
</html>