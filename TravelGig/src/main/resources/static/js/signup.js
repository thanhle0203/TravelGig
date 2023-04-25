$(document).ready(function() {
    $("#sign-up-btn").click(function(event) {
        event.preventDefault();
        var userName = $("#username").val();
        var userPassword = $("#password").val();
        var email =  $("#email").val();
        
        var user = {"userName": userName, "userPassword": userPassword, "email": email};
        
        $.ajax({
            type: "POST",
            url: "http://localhost:8282/signup",
            headers: {
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(user),
            dataType: 'json',
            success: function(result) {
                alert(result)
            }
        })
    })
})