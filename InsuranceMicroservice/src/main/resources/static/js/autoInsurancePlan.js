$(document).ready(function() {
  // Listen for button clicks
  $(".select-plan").on("click", function() {
	  
	  // Retrieve the vehicle data from local storage
	   var vehicleData = JSON.parse(localStorage.getItem("vehicleData"));


    // Get the selected plan and its details
    var planName = $(this).data("plan");

    var collisionDeductible = null;
    var uninsuredMotoristDeductible = null;

    if (planName === "ComprehensivePlan") {
      collisionDeductible = $("#collisionDeductible1").val();
      uninsuredMotoristDeductible = $("#uninsuredMotoristDeductible1").val();
      var type = "Full Coverage";
      var description = "Comprehensive coverage including collision, liability, uninsured motorist protection, and roadside assistance. Ideal for new, high-value cars.";
      var basePrice = 2000; 
      
    } else if (planName === "PlusPlan") {
      collisionDeductible = $("#collisionDeductible2").val();
      uninsuredMotoristDeductible = $("#uninsuredMotoristDeductible2").val();
      var type = "Partial Coverage";
      var description = "Balanced coverage including collision and liability protection. Suitable for medium-value cars or drivers wanting a balance between cost and coverage.";
      var basePrice = 1500; 
    }
     else if (planName === "BasicPlan") {
      var type = "Basic Coverage";
      var description = "Minimum coverage meeting state requirements, typically including liability coverage only. Suitable for older, low-value cars or drivers needing economical options.";
      var basePrice = 1000; 
    }

    console.log("Collision Deductible:", collisionDeductible);
    console.log("Uninsured Motorist Deductible:", uninsuredMotoristDeductible);

    // Create an object with the selected plan data
    var selectedPlan = {
      autoPlan: {
        name: planName,
        type: type,
    	description: description,
    	basePrice: basePrice
      },
      collisionDeductible: parseInt(collisionDeductible),
      uninsuredMotoristDeductible: parseInt(uninsuredMotoristDeductible)
    };

    // Send an AJAX request to save the selected plan to /api/auto-plans
    $.ajax({
      type: "POST",
      url: "http://localhost:8383/api/auto-plans",
      contentType: "application/json",
      data: JSON.stringify(selectedPlan.autoPlan),
      success: function(response) {
        // Handle the success response
        console.log("Selected plan saved successfully:", response);
        // Assign the autoPlan ID to the selectedPlan
        selectedPlan.autoPlan.id = response.id;
		const vehicle_id = vehicleData.id;
        // Send another AJAX request to save the selected plan of AutoInsurance
        $.ajax({
          type: "POST",
          url: "http://localhost:8383/api/auto-insurances/saveSelectedPlan",
          contentType: "application/json",
          data: JSON.stringify(selectedPlan),
          success: function(response) {
            // Handle the success response
            console.log("Selected plan of AutoInsurance saved successfully:", response);
            // Fetch the details of the saved auto plan and total price
            $.ajax({
              type: "GET",
              url: "http://localhost:8383/api/auto-insurances/selected-plans/" + response.autoPlan.id,
              success: function(planDetails) {
                // Update the selectedPlan object with the totalPrice
                selectedPlan.totalPrice = planDetails.totalPrice;

                // Store the selected plan data in local storage
                localStorage.setItem("selectedPlan", JSON.stringify(selectedPlan));

				// Store the vehicle data in local storage
                localStorage.setItem("vehicleData", JSON.stringify(vehicleData));
                
                // Redirect to the autoConfirmation.jsp page
                //window.location.href = "/autoConfirmationPlan";
                window.location.href = "/autoConfirmationPlan?vehicle_id=" + vehicle_id + "&autoInsurance_id=" + response.id;
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
            console.error("Error saving selected plan of AutoInsurance:", error);
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
