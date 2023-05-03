$(document).ready(function() {
    $("#sign-up-btn").click(function(event) {
        event.preventDefault();
        var userName = $("#username").val();
        var userPassword = $("#password").val();
        var email =  $("#email").val();
        var mobile = $("#mobile").val();
        
        var user = {"userName": userName, "userPassword": userPassword, "email": email, "mobile": mobile};
        
        $.ajax({
            type: "POST",
            contentType: 'application/json',
            url: "http://localhost:8282/signup",
            data: JSON.stringify(user),
            dataType: 'json',
            success: function(result) {
    			alert("Sign up successful!");
    			//alert(result);
    			window.location.href = "/login";
			}
        });
    });
});
