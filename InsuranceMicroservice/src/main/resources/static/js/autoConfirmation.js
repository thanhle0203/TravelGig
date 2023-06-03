$(document).ready(function() {
	
   // Retrieve the vehicle data from local storage
	   var vehicleData = JSON.parse(localStorage.getItem("vehicleData"));
	   
  // Retrieve the plan details from the request attribute
  var planTitle = '<%= request.getAttribute("planTitle") %>';
  var planDescription = '<%= request.getAttribute("planDescription") %>';
  var collisionDeductible = '<%= request.getAttribute("collisionDeductible") %>';
  var uninsuredMotoristDeductible = '<%= request.getAttribute("uninsuredMotoristDeductible") %>';
  var totalPrice = '<%= request.getAttribute("totalPrice") %>';

  // Update the UI with the plan details
  $("#planTitle").text(planTitle);
  $("#planDescription").text(planDescription);
  $("#collisionDeductible").text(collisionDeductible);
  $("#uninsuredMotoristDeductible").text(uninsuredMotoristDeductible);
  $("#totalPrice").text(totalPrice);
  
  // Handle button click
     $("#confirmButton").on("click", function(e) {
        e.preventDefault(); // prevent form from submitting the default way
       window.location.href = '/insuredInfo';
     
     });
});
