$(document).ready(function() {
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
});
