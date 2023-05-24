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
      //url: "http://localhost:8383/api/auto-insurances/saveSelectedPlan",
      url: "http://localhost:8282/savedPlan",
      contentType: "application/json",
      data: JSON.stringify(selectedPlan),
      success: function(response) {
        // Handle the success response
        console.log("Selected plan saved successfully:", response);
        // You can update the UI or perform any other necessary actions here
        
        
        // Fetch the details of the saved auto plan and total price
        $.ajax({
          type: "GET",
          //url: "http://localhost:8383/api/auto-insurances/selected-plans/" + response.autoPlan.id,
          url: "http://localhost:8282/autoPlan/" + response.autoPlan.id,
          success: function(planDetails) {
            // Handle the success response
           // console.log("Fetched auto plan details successfully:", planDetails);
            // update the UI with the plan details and total price
            /*
            $("#confirmation-details").html("<h2>Auto Plan Details</h2>" +
              "<p>Plan Name: " + planDetails.autoPlan.name + "</p>" +
              "<p>Type: " + planDetails.autoPlan.type + "</p>" +
              "<p>Description: " + planDetails.autoPlan.description + "</p>" +
              "<p>Collision Deductible: $" + planDetails.collisionDeductible + "</p>" +
              "<p>Uninsured Motorist Deductible: $" + planDetails.uninsuredMotoristDeductible + "</p>" +
              "<p>Total Price: $" + planDetails.totalPrice + "</p>");
              */
			 
			 // Populate the modal body with the auto plan details
			 /*
            var modalBody = $("#confirmationModal").find(".modal-body");
            modalBody.html("<h2>Auto Plan Details</h2>" +
                "<p>Plan Name: " + planDetails.autoPlan.name + "</p>" +
                "<p>Type: " + planDetails.autoPlan.type + "</p>" +
                "<p>Description: " + planDetails.autoPlan.description + "</p>" +
                "<p>Collision Deductible: $" + planDetails.collisionDeductible + "</p>" +
                "<p>Uninsured Motorist Deductible: $" + planDetails.uninsuredMotoristDeductible + "</p>" +
                "<p>Total Price: $" + planDetails.totalPrice + "</p>");
            
            // Open the modal
            $("#confirmationModal").modal("show");
            */
            
            // Handle the success response and redirect to autoConfirmation.jsp with plan details
    		// Set planDetails as a request parameter in the URL
            var redirectUrl = "/autoConfirmation?planTitle=" + planDetails.autoPlan.name + " - " + planDetails.autoPlan.type +
              "&planDescription=" + planDetails.autoPlan.description +
              "&collisionDeductible=" + planDetails.collisionDeductible +
              "&uninsuredMotoristDeductible=" + planDetails.uninsuredMotoristDeductible +
              "&totalPrice=" + planDetails.totalPrice;

            // Redirect to autoConfirmation.jsp with plan details
            window.location.href = redirectUrl;
    
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
