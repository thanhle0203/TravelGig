$(document).ready(function() {
  // Listen for form submission
  $("#documentForm").on("submit", function(e) {
    e.preventDefault(); // Prevent default form submission

    // Create a new FormData object
    var formData = new FormData();

    // Get the file input element
    var fileInput = $("#driverLicense")[0];

    // Add the file to the FormData object
    formData.append("driverLicense", fileInput.files[0]);

    // Create the JSON object
    var jsonData = {
      name: $("#name").val(),
      street: $("#street").val(),
      city: $("#city").val(),
      state: $("#state").val(),
      zipCode: $("#zipCode").val(),
      phone: $("#phone").val(),
      email: $("#email").val(),
      dob: $("#dob").val(),
      address: {
        street: $("#street").val(),
        city: $("#city").val(),
        state: $("#state").val(),
        zipCode: $("#zipCode").val()
      }
    };

    // Add the JSON data to the FormData object as a string
    formData.append("jsonData", JSON.stringify(jsonData));

    // Send the data to the server
    $.ajax({
      type: "POST",
      url: "http://localhost:8383/api/insured/submitDocument",
      data: formData,
      processData: false,
      contentType: false,
      success: function(response) {
        // Handle the success response
        console.log("Document submitted successfully:", response);
      },
      error: function(xhr, status, error) {
        // Handle the error response
        console.error("Error submitting document:", error);
      }
    });
  });
});
