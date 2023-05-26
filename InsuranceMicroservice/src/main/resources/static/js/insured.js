$(document).ready(function() {
  // Retrieve the selected plan data from local storage
  var selectedPlan = JSON.parse(localStorage.getItem("selectedPlan"));
  if (selectedPlan) {
    // Pre-fill the form fields with the selected plan data
    $("#name").val(selectedPlan.name);
    $("#email").val(selectedPlan.email);
    $("#phone").val(selectedPlan.phone);
    $("#dob").val(selectedPlan.dob);

    // Check if the address property is defined
    if (selectedPlan.address) {
      $("#street").val(selectedPlan.address.street);
      $("#city").val(selectedPlan.address.city);
      $("#state").val(selectedPlan.address.state);
      $("#zipCode").val(selectedPlan.address.zipCode);
    }
  }

  // Listen for form submission
  $("#documentForm").on("submit", function(e) {
    e.preventDefault(); // Prevent default form submission

    // Create a FormData object to store the form data
    var formData = new FormData(this);

    // Add the 'insured' parameter to the form data
    formData.append("insured", JSON.stringify(selectedPlan));

    // Send the data to the server
    $.ajax({
      type: "POST",
      url: "http://localhost:8383/api/insured",
      processData: false, // Prevent jQuery from processing the data
      contentType: false, // Let the server handle the content type
      data: formData,
      success: function(response) {
        // Handle the success response
        console.log("Document submitted successfully:", response);
        // Redirect to the document page or perform any other necessary actions
      },
      error: function(xhr, status, error) {
        // Handle the error response
        console.error("Error submitting document:", error);
        console.error("Response:", xhr.responseText);
        // You can display an error message or perform any other necessary actions here
      }
    });
  });
});
