$(document).ready(function() {
	$("#fetch").click(function() {
		var autoName = $("#autoName").val();
		$.get("http://localhost:8383/getAuto/" + autoName, {
		}, function(responseText) {
			$("#engCapacity").val(responseText.engineCapacity);
			
		});

	});
	
	$("#auto").click(function() {
		$("#modalTable tr:not(:first)").remove();
		$("#myModal").modal('toggle');
		$.get("http://localhost:8383/getAllAuto", {
		}, function(responseText) {
			$.each(responseText, function(key1, value1) {
				$("#modalTable").append("<tr><td>"+value1.id+"</td><td>"+value1.name+"</td><td>"+value1.engineCapacity+"</td></tr>")
			});			
			
		});

	});
	
	$("#save").click(function() {
		var name = $("#autoName").val();
		var engineCapacity = $("#engCapacity").val();
		var auto = { "name": name, "engineCapacity": engineCapacity}
		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: "http://localhost:8383/saveAutomobile",
			data: JSON.stringify(auto),
			dataType: 'json',
			success: function(result) {
				alert(result);
			},
			error: function(e) {

			}
		});
	});


});