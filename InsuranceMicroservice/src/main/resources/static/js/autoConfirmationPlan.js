$(document).ready(function() {
    // Retrieve the vehicle data from local storage
    var vehicleData = JSON.parse(localStorage.getItem("vehicleData"));

    // Retrieve the selected plan from local storage
    var selectedPlan = JSON.parse(localStorage.getItem("selectedPlan"));

    // Update the confirmation details in the HTML elements
    $("#planName").text(selectedPlan.autoPlan.name);
    $("#collisionDeductible").text("$" + selectedPlan.collisionDeductible);
    $("#uninsuredMotoristDeductible").text("$" + selectedPlan.uninsuredMotoristDeductible);
    $("#totalPrice").text("$" + (selectedPlan.totalPrice ? selectedPlan.totalPrice.toFixed(2) : ""));

    // Set the values of the hidden form fields
    $("#hiddenPlanName").val(selectedPlan.autoPlan.name);
    $("#hiddenCollisionDeductible").val(selectedPlan.collisionDeductible);
    $("#hiddenUninsuredMotoristDeductible").val(selectedPlan.uninsuredMotoristDeductible);
    $("#hiddenTotalPrice").val(selectedPlan.totalPrice);

    // Retrieve the autoInsuranceId from the URL query parameters
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const autoInsurance_id = urlParams.get('autoInsurance_id');
    //const vehicle_id = urlParams.get('vehicle_id');
   	const vehicle_id = vehicleData.id;
	console.log(vehicle_id)
    // Submit the form
    $("#confirmationForm").submit(function(event) {
        // Prevent the default form submission
        event.preventDefault();

        // Store the vehicle data in local storage
        localStorage.setItem("vehicleData", JSON.stringify(vehicleData));

        // Set the autoInsuranceId as a query parameter in the form action URL
        const formAction = "/insured?vehicle_id=" + encodeURIComponent(vehicle_id) + "&autoInsurance_id=" + encodeURIComponent(autoInsurance_id);
        $("#confirmationForm").attr("action", formAction);

        // Submit the form
        this.submit();
    });
});
