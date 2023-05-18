$(document).ready(function() {
    $("#detailsForm").submit(function(e) {
        e.preventDefault(); // prevent form from submitting the default way

        var formData = {
            name: $("#name").val(),
            email: $("#email").val(),
            dob: $("#dob").val(),
            phone: $("#phone").val(),
            address: {
                street: $("#street").val(),
                city: $("#city").val(),
                state: $("#state").val(),
                zip: $("#zip").val()
            }
        };

        $.ajax({
            type: "POST",
            //url: "http://localhost:8383/api/insurer", 
            url: "http://localhost:8282/saveInsurer", 
            data: JSON.stringify(formData),
            contentType: "application/json",
            dataType: 'json',
            success: function() {
                window.location.href = 'http://localhost:8282/autoInsurancePlans'; // redirect to the next page
            },
            error: function() {
                alert("There was an error submitting the form. Please try again.");
            }
        });
    });
});
