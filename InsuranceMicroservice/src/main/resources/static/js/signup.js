$(document).ready(function() {
    $("#sign-up-btn").click(function(event) {
        event.preventDefault();
        var userName = $("#username").val();
        var userPassword = $("#password").val();
        var email =  $("#email").val();
        var mobile = $("#mobile").val();
        
        // Perform password validation
        if (userPassword === "") {
            alert("Password cannot be empty");
            return;
        }
        
        var user = {
			userName: userName, 
        	userPassword: userPassword, 
        	email: email, 
        	mobile: mobile
        };
        
        $.ajax({
            type: "POST",
            contentType: 'application/json',
            url: "http://localhost:8282/signup",
            data: JSON.stringify(user),
            contentType: "application/json",
            dataType: 'json',
            success: function(result) {
                alert("Sign up successful!");
                window.location.href = "/login";
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("Error signing up: " + jqXHR.responseText);
            }
        });
    });
});
