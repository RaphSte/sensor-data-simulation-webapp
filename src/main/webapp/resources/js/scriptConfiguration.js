	$(document).ready(function(){
	    $.get("configurations", function(configurations){
	           createTable(configurations);
	    });
	});
	
	function createTable(configurations) {
		var txt="<table class='table table-bordered table-condensed table-striped table-hover'>" +
				"<thead><tr><th>Wheel Rim Configuration</th><th>Creation Date</th><th>Modification Date</th></tr></thead><tbody>"
		var line="";
		$.each( configurations, function( key, configuration ) {
		    line="<tr class=\"table-row\" data-href=\"configuration?id="+configuration.id+"\"><td>";
		    line+=configuration.filename+"</td><td>"+configuration.created+"</td><td>"+configuration.updated+"</td></tr>";
		    txt+=line;
		});
		txt+="</tbody></table>"
		$("#configurations").html(txt);
		
	    $(".table-row").click(function() {
	        window.document.location = $(this).data("href");
	    });
	}
	
	function showConfiguration(id) {
		$.get("configurations/" + id, function(configuration){
			$("#configuration").html("<h2>Details of " + configuration.filename + "</h2><p>" + configuration.created +"</p><p>" + configuration.updated + "</p>");
    	});
	}
	