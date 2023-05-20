
    $(document).ready(function() {
        // Submit form on button click
        $("button[type='submit']").on("click", function(e) {
            e.preventDefault();
            //var planAutoId = $(this).data("plan-auto-id"); // Get the planAutoId from the data attribute
            var selectedPlanName = $(this).val();
            $("#planForm").append('<input type="hidden" name="selectedPlanName" value="' + selectedPlanName + '">');
        	//$("#planForm").append('<input type="hidden" name="planAutoId" value="' + planAutoId + '">'); // Append the planAutoId as a hidden input field
        	$("#planForm").submit();
        });

        // Handle form submission
        $("#planForm").on("submit", function(e) {
            e.preventDefault();

            var selectedPlan = $("button[type='submit']").val();
            var collisionDeductible = parseInt($("#collisionDeductible").val());
            var uninsuredMotoristDeductible = parseInt($("#uninsuredMotoristDeductible").val());

            // Create the data object
            var data = {
                autoPlan: selectedPlan,
                collisionDeductible: collisionDeductible,
                uninsuredMotoristDeductible: uninsuredMotoristDeductible
            };

            // Send the AJAX request
            $.ajax({
                url: "http://localhost:8383/api/auto-insurances/saveSelectedPlan",
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(data),
                
               success: function(response) {
    // Handle success response here
    console.log("Selected plan saved successfully:", response);

    // Get the autoPlan object from the response
    var autoPlan = response.autoPlan;
    if (autoPlan) {
        var planAutoId = autoPlan.id;

        // Send the AJAX request to retrieve the confirmed plan details
        $.ajax({
            url: "http://localhost:8383/api/auto-insurances/selected-plans/" + planAutoId,
            type: "GET",
            
            /*
            success: function(data) {
    // Handle the success response and display the plan confirmation details
    var planConfirmation = $("#planConfirmation");

    // Clear the existing content
    planConfirmation.empty();

    var planTitle = $("<h4></h4>").text(data.autoPlan.name + " - " + data.autoPlan.type);
    var planDescription = $("<p></p>").text(data.autoPlan.description);
    var basePrice = $("<h5></h5>").text('Base Price: $' + data.autoPlan.basePrice.toFixed(2) + '/year');
    var collisionDeductible = $("<h5></h5>").text('Collision Deductible: $' + data.collisionDeductible.toFixed(2));
    var uninsuredMotoristDeductible = $("<h5></h5>").text('Uninsured Motorist Deductible: $' + data.uninsuredMotoristDeductible.toFixed(2));
    var totalPrice = $("<h5></h5>").text('Total Price: $' + data.totalPrice.toFixed(2) + '/year');

    // Append these elements to the planConfirmation div
   planConfirmation.append(planTitle, planDescription, basePrice, collisionDeductible, uninsuredMotoristDeductible, totalPrice);
	},
	*/
		success: function(data) {
    // Handle the success response and redirect to autoConfirmation.jsp with plan details
    var planDetails = {
        planTitle: data.autoPlan.name + " - " + data.autoPlan.type,
        planDescription: data.autoPlan.description,
        basePrice: data.autoPlan.basePrice.toFixed(2),
        collisionDeductible: data.collisionDeductible.toFixed(2),
        uninsuredMotoristDeductible: data.uninsuredMotoristDeductible.toFixed(2),
        totalPrice: data.totalPrice.toFixed(2)
    };

    // Set planDetails as a request attribute
    $.ajax({
        url: "/autoConfirmation",
        type: "POST",
        data: planDetails,
        success: function(response) {
            // Redirect to autoConfirmation.jsp
            window.location.href = "/autoConfirmation";
        },
        error: function(error) {
            // Handle error response here
            console.log("Error redirecting to autoConfirmation.jsp:", error);
            // Show error message
        }
    });
},

            
            error: function() {
                alert("Error loading confirmed plan details. Please try again later.");
            }
        });
    } else {
        // Handle the case when autoPlan is undefined or null
        alert("Error: Missing autoPlan object in the response.");
    }
},


                error: function(error) {
                    // Handle error response here
                    console.log("Error saving selected plan:", error);
                    // Show error message
                }
                
                
                
            });
        });
    });

