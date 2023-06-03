$(document).ready(function() {
	// Retrieve the vehicle data from local storage
	var vehicleData = JSON.parse(localStorage.getItem("vehicleData"));
	   
  // Retrieve the selected plan ID from the URL query parameter
  const urlParams = new URLSearchParams(window.location.search);
  var selectedPlanId = urlParams.get('autoInsurance_id');
  var vehicleId = urlParams.get('vehicle_id');
  var vehicle_id = urlParams.get('vehicle_id');

  // Fetch the details of the saved auto plan and total price
  $.ajax({
    type: "GET",
    url: "http://localhost:8383/api/auto-insurances/" + selectedPlanId,
    success: function(planDetails) {
      var autoInsurance = planDetails; // Retrieve the autoInsurance object from the response

      // Store the autoInsurance object in local storage
      localStorage.setItem("autoInsuranceData", JSON.stringify(autoInsurance));

      // Submit the form when the "Submit" button is clicked
      $("#documentForm").on("submit", function(event) {
        // Prevent the default form submission
        event.preventDefault();

        // Create a FormData object to store the form data
        var formData = new FormData(this);
        
        // Get the insured JSON data
        /*
        var insuredData = {
          name: $("#name").val(),
          email: $("#email").val(),
          phone: $("#phone").val(),
          dob: $("#dob").val(),
          address: {
            street: $("#street").val(),
            city: $("#city").val(),
            state: $("#state").val(),
            zipCode: $("#zipCode").val()
          },
          autoInsurance: {
            id: autoInsurance.id,
            totalPrice: autoInsurance.totalPrice,
            collisionDeductible: autoInsurance.collisionDeductible,
            uninsuredMotoristDeductible: autoInsurance.uninsuredMotoristDeductible,
            autoPlan: {
              id: autoInsurance.autoPlan.id,
              name: autoInsurance.autoPlan.name,
              type: autoInsurance.autoPlan.type,
              description: autoInsurance.autoPlan.description,
              basePrice: autoInsurance.autoPlan.basePrice
            }
          }
        };
        */

        // Get the insured JSON data
        var insuredData = {
          name: $("#name").val(),
          email: $("#email").val(),
          phone: $("#phone").val(),
          dob: $("#dob").val(),
          address: {
            street: $("#street").val(),
            city: $("#city").val(),
            state: $("#state").val(),
            zipCode: $("#zipCode").val()
          },
          autoInsurance: {
            id: autoInsurance.id,
            totalPrice: autoInsurance.totalPrice,
            collisionDeductible: autoInsurance.collisionDeductible,
            uninsuredMotoristDeductible: autoInsurance.uninsuredMotoristDeductible,
            autoPlan: {
              id: autoInsurance.autoPlan.id,
              name: autoInsurance.autoPlan.name,
              type: autoInsurance.autoPlan.type,
              description: autoInsurance.autoPlan.description,
              basePrice: autoInsurance.autoPlan.basePrice
            }
          },   
          vehicle: {
			  id: vehicleData.id,
			  make: vehicleData.make,
			  model: vehicleData.model,
			  vin: vehicleData.vin,
			  year: vehicleData.year
		  }
		  
		  //vehicleData: JSON.stringify(vehicleData) // Convert the vehicleData to JSON string
        };


        // Store the insuredData object in local storage
        localStorage.setItem("insured", JSON.stringify(insuredData));
        
        // Add the vehicle data to the form data
		//formData.append("vehicleData", JSON.stringify(vehicleData));

        // Convert the insured data to JSON and append it to the FormData
        formData.append("insured", JSON.stringify(insuredData));
        
        //formData.append("insuredData", JSON.stringify(insuredData));
        //formData.append("insuredData", JSON.stringify(insuredData));
        console.log("Insured Data json: ", insuredData);
     

        // Send the insured data to the server
        $.ajax({
          type: "POST",
          url: "http://localhost:8383/api/insured",
          data: formData,
          processData: false,
          contentType: false,
          success: function(response) {
            // Handle the success response
            console.log("Insured data submitted successfully:", response);

            // Perform any additional actions or show a success message to the user

            // Clear the selected plan from local storage
            //localStorage.removeItem("selectedPlan");

            // Redirect to the insured details page
            window.location.href = "/payment";
          },
          error: function(xhr, status, error) {
            // Handle the error response
            console.error("Error submitting insured data:", error);
            // You can display an error message or perform any other necessary actions here
          },
          complete: function() {
            // This function will be called regardless of success or error
            // You can perform any cleanup or post-submission actions here
          }
        });
      });

      // Add autocomplete attribute to form fields
      // ...
    },
    error: function(xhr, status, error) {
      console.error("Error fetching auto plan details:", error);
      // Handle the error fetching the auto plan details
    }
  });
});
