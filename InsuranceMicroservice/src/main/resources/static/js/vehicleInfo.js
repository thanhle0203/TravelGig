$(document).ready(function() {
    $("#vehicleForm").submit(function(e) {
        e.preventDefault(); // prevent form from submitting the default way

        var savedVehicleData = {
            make: $("#make").val(),
            model: $("#model").val(),
            year: $("#year").val(),
            vin: $("#vin").val()
        };

        $.ajax({
            type: "POST",
            url: "http://localhost:8383/api/vehicles",
            contentType: "application/json",
            data: JSON.stringify(savedVehicleData),
            success: function(response) {
                alert("Vehicle information submitted successfully.");

                // Make an AJAX GET request to retrieve the saved vehicle data
                $.ajax({
                    type: "GET",
                    url: "http://localhost:8383/api/vehicles/" + response.id,
                    success: function(vehicleData) {
                        // Store the saved vehicle data in local storage
                        localStorage.setItem("vehicleData", JSON.stringify(vehicleData));

                        // Redirect to the next page
                        window.location.href = "/autoInsurancePlan?vehicle_id=" + response.id;
                    },
                    error: function() {
                        console.error("Error retrieving saved vehicle data.");
                    }
                });
            },
            error: function() {
                alert("There was an error submitting the form. Please try again.");
            }
        });
    });
});
