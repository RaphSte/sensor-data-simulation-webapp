// Called after form input is processed
function startConnect() {
    // Generate a random client ID
    clientID = "clientID-" + parseInt(Math.random() * 100);

    // Fetch the hostname/IP address and port number from the form
    host = "broker.hivemq.com";// = document.getElementById("host").value;
    port = "8000";//document.getElementById("port").value;

    // Initialize new Paho client connection
    client = new Paho.MQTT.Client(host, Number(port), clientID);

    // Set callback handlers
    client.onConnectionLost = onConnectionLost;
    client.onMessageArrived = onMessageArrived;

    // Connect the client, if successful, call onConnect function
    client.connect({
        onSuccess: onConnect,
    });
}


// Called when the client connects
function onConnect() {
    // Fetch the MQTT topic from the form
    topic = "testUnitMachine1";//document.getElementById("topic").value;

    // Subscribe to the requested topic
    client.subscribe(topic);
}

// Called when the client loses its connection
function onConnectionLost(responseObject) {
    console.log("onConnectionLost: Connection Lost");
    if (responseObject.errorCode !== 0) {
        console.log("onConnectionLost: " + responseObject.errorMessage);
    }
}

// Called when a message arrives
function onMessageArrived(message) {
    console.log("onMessageArrived: " + message.payloadString);
    var jsonMessage = JSON.parse(message.payloadString);
    processDisplay(jsonMessage);

    //document.getElementById("messages").innerHTML += '<span>Topic: ' + message.destinationName + '  | ' + message.payloadString + '</span><br/>';
    //updateScroll(); // Scroll to bottom of window
}


// Called when the disconnection button is pressed
function startDisconnect() {
    client.disconnect();
    console.log("disconnected");
}
