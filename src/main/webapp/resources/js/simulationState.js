var chartIdArray = [];
var runningStateArray = [];
var chartArray = [];
var xArrayContainer = [];
var yArrayContainer = [];
var yDataPointLength = 30;

// in case the Rest URL changes, it needs to be changed within this variable as well.
var restUrlApi = "getSimulatedDataFor";

function callRestFor(id) {



    //small hack to get url context - a solution using regex or something like that is probably better.
    var path = window.location.href;
    var api_url = path.replace("simulation_state", restUrlApi);

    var key = id.substring(6);
    $.ajax({
        url: api_url + "?unitNameKey=" + key + "&q=" + $(this).text(),
        contentType: "application/json",
        dataType: 'json',
        success: function (simulation) {
            processDisplayFor(id, simulation.value);
        }
    });
}

function callRests() {
    for (var i = 0; i < chartIdArray.length; i++) {
        if (runningStateArray[i] == "true") {
            callRestFor(chartIdArray[i]);
        }

    }
}

function setChartOptions(index) {
// specify chart configuration item and data
    option = {
        xAxis: {
            type: 'category',
            data: xArrayContainer[index]
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            data: yArrayContainer[index],
            type: 'line',
            smooth: true
        }]
    };

// use configuration item and data specified to show chart
    chartArray[index].setOption(option);
}

function fillArrayContainers(index) {
    var xDataArray = [];
    var yDataArray = [];
    xArrayContainer[index] = xDataArray;
    yArrayContainer[index] = yDataArray;
}

function initChart(index) {
    console.log("init chart at index " + index);
    chartArray[index] = echarts.init(document.getElementById(chartIdArray[index]));
    fillArrayContainers(index);
    setChartOptions(index);
}

function fillIdArrayAndInitCharts() {
    var graphArray = $("div[name^=simulation-graph]");
    for (var i = 0; i < graphArray.length; i++) {
        chartIdArray.push(graphArray[i].id);
        initChart(i);
    }
}

function findIndexFor(id) {
    for (var i = 0; i < chartIdArray.length; i++) {
        if (chartIdArray[i] == id) {
            console.log("foudIndex "+i+" For " + id);
            return i;
        }
    }
}

function evaluateRunningStateFor(id) {
    var path = window.location.href;
    var api_url = path.replace("simulation_state", restUrlApi);
    var key = id.substring(6);

    $.ajax({
        url: api_url + "?unitNameKey=" + key + "&q=" + $(this).text(),
        contentType: "application/json",
        dataType: 'json',
        async: false,
        success: function (simulation) {
            runningStateArray[findIndexFor(id)] = simulation.running;
            console.log("runningState for "+id+" is: "+runningStateArray[findIndexFor(id)]);
        }
    });
}

function processDisplayFor(id, value) {
    console.log("ProcessDisplayFor " + id);
    var date = new Date();
    var timestamp = date.getDate() + "." + date.getMonth() + "." + date.getFullYear() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();

    var index = findIndexFor(id);
    console.log("foundIndexForID: "+index);
    xArrayContainer[index].push(timestamp);
    yArrayContainer[index].push(value);

    if (xArrayContainer[index].length >= yDataPointLength) {
        xArrayContainer[index].shift();
        yArrayContainer[index].shift();
    }
    setChartOptions(index);
    console.log("setOpetionDone!")
}

function evaluateRunningStates() {
    for (var i = 0; i < chartIdArray.length; i++) {
        evaluateRunningStateFor(chartIdArray[i]);
    }
}

fillIdArrayAndInitCharts();
evaluateRunningStates();
setInterval(callRests, 500);

//callable from the browser, in case the chart needs to be extended. Might as well be uses to do some fancy dynamic stuff.
function setYDataPointLength(length){
    yDataPointLength = length;
}

