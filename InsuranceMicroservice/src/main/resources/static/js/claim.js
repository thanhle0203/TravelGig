$(document).ready(function() {
    // Load vehicle makes into the make select dropdown
    function loadVehicleMakes() {
        $.ajax({
            url: 'http://localhost:8383/api/vehicles/makes',
            type: 'GET',
            success: function(response) {
                var makes = response;
                var makeSelect = $('#vehicleMake');
                makeSelect.empty();
                makeSelect.append('<option value="">Select a vehicle make</option>');
                $.each(makes, function(index, make) {
                    makeSelect.append('<option value="' + make + '">' + make + '</option>');
                });
            },
            error: function(xhr, status, error) {
                console.error('Error loading vehicle makes: ' + error);
            }
        });
    }

    // Load vehicle makes on page load
    loadVehicleMakes();

    // Load vehicle models based on the selected make
    function loadVehicleModels(selectedMake) {
        $.ajax({
            url: 'http://localhost:8383/api/vehicles/models',
            type: 'GET',
            data: { make: selectedMake }, // Pass the selected make as a query parameter
            success: function(response) {
                var models = response;
                var modelSelect = $('#vehicleModel');
                modelSelect.empty();
                modelSelect.append('<option value="">Select a vehicle model</option>');
                $.each(models, function(index, model) {
                    modelSelect.append('<option value="' + model + '">' + model + '</option>');
                });
            },
            error: function(xhr, status, error) {
                console.error('Error loading vehicle models: ' + error);
            }
        });
    }

    // Load vehicle years into the year select dropdown
    function loadVehicleYears() {
        $.ajax({
            url: 'http://localhost:8383/api/vehicles/years',
            type: 'GET',
            success: function(response) {
                var years = response;
                var yearSelect = $('#vehicleYear');
                yearSelect.empty();
                yearSelect.append('<option value="">Select a vehicle year</option>');
                $.each(years, function(index, year) {
                    yearSelect.append('<option value="' + year + '">' + year + '</option>');
                });
            },
            error: function(xhr, status, error) {
                console.error('Error loading vehicle years: ' + error);
            }
        });
    }

    // Load vehicle years on page load
    loadVehicleYears();

    // Handle change event for the vehicle make select dropdown
    
    $('#vehicleMake').on('change', function() {
        var selectedMake = $(this).val();
        if (selectedMake) {
            loadVehicleModels(selectedMake);
        } else {
            $('#vehicleModel').empty();
        }
    });
    

    // Handle form submission
    $('#claimForm').submit(function(e) {
        e.preventDefault();

        var formData = new FormData(this);
	
        // Get the file inputs
        var files = $('#image')[0].files;

        // Append each file to the formData object
        for (var i = 0; i < files.length; i++) {
            formData.append('image', files[i]);
        }


        $.ajax({
            url: 'http://localhost:8383/api/claims',
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            success: function(response) {
                alert('Claim submitted successfully!');
                $('#claimForm')[0].reset();
                // Clear the vehicle make and model dropdowns
                $('#vehicleMake').empty();
                $('#vehicleModel').empty();
                $('#vehicleYear').empty();
 
                
                var claimId = response.id;
                $.post("http://localhost:8282/sendClaimDetails/" + claimId, {}, function(response) {
            // Handle the response from the server
            console.log(response);
        }).fail(function(jqXHR, textStatus, errorThrown) {
            // Handle the error case
            console.log("Failed to send booking details: " + errorThrown);
        });
            },
            error: function(xhr, status, error) {
                alert('Error submitting claim: ' + error);
            }
        });
    });
});
