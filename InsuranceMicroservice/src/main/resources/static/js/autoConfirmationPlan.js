$(document).ready(function() {
  // Listen for confirm button click
  $("#confirmButton").on("click", function() {
    // Retrieve the selected plan data from local storage
    var selectedPlan = JSON.parse(localStorage.getItem("selectedPlan"));

    // Redirect to the insured page
    window.location.href = "/insured";
  });
});
