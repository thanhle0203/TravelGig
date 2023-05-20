$(document).ready(function() {
// Handle button click
     $("#driverForm").submit(function(e) {
        e.preventDefault(); // prevent form from submitting the default way
       window.location.href = '/autoInsurancePlans';
        //window.location.href = '/autoInsurancePlan';
     
     });
});

/*
$(document).ready(function() {
    $("#driverForm").submit(function(e) {
        e.preventDefault(); // prevent form from submitting the default way

        var driverData = {
            zipCode: $("#zipCode").val(),
            license: $("#license").val(),
            maritalStatus: $("#maritalStatus").val()
        };

        $.ajax({
            type: "POST",
            url: "http://localhost:8383/api/driver", // replace with your actual API endpoint
            data: JSON.stringify(driverData),
            contentType: "application/json",
            dataType: 'json',
            success: function() {
                alert("Driver information submitted successfully.");
                window.location.href = 'http://localhost:8282/nextPage'; // redirect to the next page
            },
            error: function() {
                alert("There was an error submitting the form. Please try again.");
            }
        });
    });
});

*/