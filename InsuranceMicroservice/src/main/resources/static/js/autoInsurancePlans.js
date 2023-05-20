$(document).ready(function() {
  // Listen for form submission
  $("#planForm").on("submit", function(event) {
    event.preventDefault(); // Prevent the default form submission

    // Get the selected plan and its details
    var planName = $(this).find(":submit").attr("name");

    if (planName === "ComprehensivePlan") {
      var collisionDeductible = $(this).find("#collisionDeductible").val();
      var uninsuredMotoristDeductible = $(this).find("#uninsuredMotoristDeductible").val();
    } else if (planName === "PlusPlan") {
      var collisionDeductible = $(this).find("#collisionDeductible").val();
      var uninsuredMotoristDeductible = $(this).find("#uninsuredMotoristDeductible").val();
    } else {
      var collisionDeductible = null;
      var uninsuredMotoristDeductible = null;
    }

    console.log("Collision Deductible:", collisionDeductible);
    console.log("Uninsured Motorist Deductible:", uninsuredMotoristDeductible);

    // Create an object with the selected plan data
    var selectedPlan = {
      autoPlan: {
        name: planName
      },
      collisionDeductible: parseInt(collisionDeductible),
      uninsuredMotoristDeductible: parseInt(uninsuredMotoristDeductible)
    };

    // Send an AJAX request to the server to save the selected plan
    $.ajax({
      type: "POST",
      url: "http://localhost:8383/api/auto-insurances/saveSelectedPlan",
      contentType: "application/json",
      data: JSON.stringify(selectedPlan),
      success: function(response) {
        // Handle the success response
        console.log("Selected plan saved successfully:", response);
        // You can update the UI or perform any other necessary actions here
      },
      error: function(xhr, status, error) {
        // Handle the error response
        console.error("Error saving selected plan:", error);
        // You can display an error message or perform any other necessary actions here
      }
    });
  });
});
