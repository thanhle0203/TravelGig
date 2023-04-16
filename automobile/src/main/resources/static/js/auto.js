$(document).ready(function() {
	$("#fetch").click(function() {
		$.get("http://localhost:8282/tra/" + $("#stdName").val(), {
		}, function(responseText) {
			$("#stdId").val(responseText.id);
			$("#stdName").val(responseText.name);
			$("#stdAge").val(responseText.age);
		});

	});

	$("#save").click(function() {
		var id = $("#stdId").val();
		var name = $("#stdName").val();
		var age = $("#stdAge").val();
		var stud = { "id": id, "name": name, "age": age }
		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: "http://localhost:8282/saveStudent",
			data: JSON.stringify(stud),
			dataType: 'json',
			success: function(result) {
				alert(result);
			},
			error: function(e) {

			}
		});
	});


});