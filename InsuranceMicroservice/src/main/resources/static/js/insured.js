$(document).ready(function() {
  // Listen for form submission
  $("#documentForm").on("submit", function(e) {
    e.preventDefault(); // Prevent default form submission

    // Get the form data and convert it to JSON
    var formData = $(this).serializeArray();
    var jsonData = {};
    $.each(formData, function(index, field) {
      jsonData[field.name] = field.value;
    });
    
    // Create the address object and add it to the insured object
    var addressData = {
      street: jsonData.street,
      city: jsonData.city,
      state: jsonData.state,
      zipCode: jsonData.zipCode
    };
    jsonData.address = addressData;

    // Send the data to the server
    $.ajax({
      type: "POST",
      url: "http://localhost:8383/api/insured",
      //contentType: "application/json",
      	processData: false,
  		contentType: false,
      data: JSON.stringify(jsonData),
      success: function(response) {
        // Handle the success response
        console.log("Document submitted successfully:", response);
        // Handle button click
     $("#confirmButton").on("click", function(e) {
        e.preventDefault(); // prevent form from submitting the default way
       window.location.href = '/document';
     
     });
      },
      error: function(xhr, status, error) {
        // Handle the error response
        console.error("Error submitting document:", error);
        // You can display an error message or perform any other necessary actions here
      }
    });
  });
});
