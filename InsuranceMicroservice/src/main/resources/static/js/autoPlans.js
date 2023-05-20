$(document).ready(function() {
  // Listen for button clicks
  $(".select-plan").on("click", function() {
    // Get the selected plan and its details
    var planName = $(this).data("plan");

    var collisionDeductible = null;
    var uninsuredMotoristDeductible = null;

    if (planName === "ComprehensivePlan") {
      collisionDeductible = $("#collisionDeductible1").val();
      uninsuredMotoristDeductible = $("#uninsuredMotoristDeductible1").val();
    } else if (planName === "PlusPlan") {
      collisionDeductible = $("#collisionDeductible2").val();
      uninsuredMotoristDeductible = $("#uninsuredMotoristDeductible2").val();
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
        
        
        // Fetch the details of the saved auto plan and total price
        $.ajax({
          type: "GET",
          url: "http://localhost:8383/api/auto-insurances/selected-plans/" + response.autoPlan.id,
          success: function(planDetails) {
            // Handle the success response
            console.log("Fetched auto plan details successfully:", planDetails);
            // You can update the UI with the plan details and total price
            // For example:
            $("#confirmation-details").html("<h2>Auto Plan Details</h2>" +
              "<p>Plan Name: " + planDetails.autoPlan.name + "</p>" +
              "<p>Type: " + planDetails.autoPlan.type + "</p>" +
              "<p>Description: " + planDetails.autoPlan.description + "</p>" +
              "<p>Collision Deductible: $" + planDetails.collisionDeductible + "</p>" +
              "<p>Uninsured Motorist Deductible: $" + planDetails.uninsuredMotoristDeductible + "</p>" +
              "<p>Total Price: $" + planDetails.totalPrice + "</p>");
          },
          error: function(xhr, status, error) {
            // Handle the error response
            console.error("Error fetching auto plan details:", error);
            // You can display an error message or perform any other necessary actions here
          }
        });
      },
      error: function(xhr, status, error) {
        // Handle the error response
        console.error("Error saving selected plan:", error);
        // You can display an error message or perform any other necessary actions here
      }
    });
  });
});
