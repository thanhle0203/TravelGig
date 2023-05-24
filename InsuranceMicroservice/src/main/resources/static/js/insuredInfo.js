$(document).ready(function() {
  // Listen for form submission
  $("#documentForm").on("submit", function(e) {
    e.preventDefault(); // Prevent default form submission

    // Create a FormData object to store the form data
    var formData = new FormData(this);

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
      }
    };

    // Convert the insured data to JSON and append it to the FormData
    formData.append("insured", JSON.stringify(insuredData));

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
        // You can display an error message or perform any other necessary actions here
      }
    });
  });
});
