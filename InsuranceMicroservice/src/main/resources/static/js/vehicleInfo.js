$(document).ready(function() {
// Handle button click
     $("#vehicleForm").submit(function(e) {
        e.preventDefault(); // prevent form from submitting the default way
        window.location.href = '/driverInfo';
     });
});

/*
<script>
    $(document).ready(function() {
        $("#vehicleForm").submit(function(e) {
            e.preventDefault(); // prevent form from submitting the default way

            var vehicleData = {
                make: $("#make").val(),
                model: $("#model").val(),
                year: $("#year").val(),
                vin: $("#vin").val()
            };

            $.ajax({
                type: "POST",
                url: "http://localhost:8383/api/vehicle", // replace with your actual API endpoint
                data: JSON.stringify(vehicleData),
                contentType: "application/json",
                dataType: 'json',
                success: function() {
                    alert("Vehicle information submitted successfully.");
                    window.location.href = 'http://localhost:8282/autoInsurancePlans'; // redirect to the next page
                },
                error: function() {
                    alert("There was an error submitting the form. Please try again.");
                }
            });
        });
    });
</script>

*/