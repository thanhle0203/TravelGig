$(document).ready(function() {
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

// Submit the form
$("#confirmationForm").submit(function(event) {
    // Prevent the default form submission
    event.preventDefault();

    // Set the autoInsuranceId as a query parameter in the form action URL
    const formAction = "/insured?autoInsurance_id=" + encodeURIComponent(autoInsurance_id);
    $("#confirmationForm").attr("action", formAction);

    // Submit the form
    this.submit();
});

});
