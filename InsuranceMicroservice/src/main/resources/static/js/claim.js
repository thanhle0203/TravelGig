$(document).ready(function() {
    // Load vehicle makes into the make select dropdown
    /*
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

    // Load vehicle models based on the selected make
    function loadVehicleModels(selectedMake) {
        $.ajax({
            url: 'http://localhost:8383/api/vehicles/models?make=' + selectedMake,
            type: 'GET',
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
    */
   
   // Load vehicle years into the year select dropdown
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
                console.error('Error loading vehicle years: ' + error);
            }
        });
    }
    
    // Load vehicle makes on page load
    loadVehicleMakes();

    
    // Load vehicle years into the year select dropdown
    function loadVehicleModels() {
        $.ajax({
            url: 'http://localhost:8383/api/vehicles/models',
            type: 'GET',
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
    
    loadVehicleModels();

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

    // Load vehicle models based on the selected make
    /*
    $('#vehicleMake').on('change', function() {
        var selectedMake = $(this).val();
        if (selectedMake) {
            loadVehicleModels(selectedMake);
        } else {
            $('#vehicleModel').empty();
        }
    });
    */

    // Load vehicle years on page load
    loadVehicleYears();

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

        // Add accident date, description, vehicle make, model, and year to formData
        var accidentDate = $('#accidentDate').val();
        var description = $('#description').val();
        var vehicleMake = $('#vehicleMake').val();
        var vehicleModel = $('#vehicleModel').val();
        var vehicleYear = $('#vehicleYear').val();

        formData.append('accidentDate', accidentDate);
        formData.append('description', description);
        formData.append('vehicleMake', vehicleMake);
        formData.append('vehicleModel', vehicleModel);
        formData.append('vehicleYear', vehicleYear);

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
                // Reload the vehicle makes and years
                //loadVehicleMakes();
                //loadVehicleModels();
                //loadVehicleYears();
            },
            error: function(xhr, status, error) {
                alert('Error submitting claim: ' + error);
            }
        });
    });
});
