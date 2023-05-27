$(document).ready(function() {
  // Retrieve the selected plan ID from the URL query parameter
  const urlParams = new URLSearchParams(window.location.search);
  var selectedPlanId = urlParams.get('autoInsurance_id');

  // Populate the selected plan ID in the insured form
  //$("#selectedPlanId").val(selectedPlanId);

  // Submit the form when the "Submit" button is clicked
  $("#documentForm").on("submit", function(event) {
    // Prevent the default form submission
    event.preventDefault();

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
      }, 
      autoInsurance_id: $("#selectedPlanId").val(selectedPlanId)
    };

    // Convert the insured data to JSON and append it to the FormData
    formData.append("insured", JSON.stringify(insuredData));

    // Append the autoInsurance_id to the FormData
    //formData.append("autoInsurance_id", selectedPlanId);

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
        localStorage.removeItem("selectedPlan");

        // Redirect to the insured details page
        // window.location.href = "/insured.jsp";
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
});
