$(document).ready(function() {
  // Retrieve the insured data from local storage
  //var insuredData = JSON.parse(localStorage.getItem("insuredData"));
  var insuredData = JSON.parse(localStorage.getItem("insured"));

  if (insuredData) {
    // Update the payment details in the HTML elements
    $("#insuredName").text(insuredData.name);
    $("#autoInsurancePlan").text(insuredData.autoInsurance.autoPlan.name);
    $("#totalPrice").text("$" + insuredData.autoInsurance.totalPrice.toFixed(2));
  } else {
    // Handle the case when insuredData is not available
    console.error("Insured data not found. Please ensure that insuredData is set correctly.");
    return;
  }

  // Submit the payment form
  $("#paymentForm").on("submit", function(event) {
    event.preventDefault();

    // Retrieve the payment form data
    var cardNumber = $("#cardNumber").val();
    var expiryDate = $("#expiryDate").val();
    var cvv = $("#cvv").val();
    var nameOnCard = $("#nameOnCard").val();

    // Perform any necessary validation on the form fields

    // Create a payment object with the form data
    var payment = {
      cardNumber: cardNumber,
      expiryDate: expiryDate,
      cvv: cvv,
      nameOnCard: nameOnCard,
      totalAmount: insuredData.autoInsurance.totalPrice
    };

    // Send the payment data to the server for processing
    $.ajax({
      type: "POST",
      url: "/process-payment", // Update the URL with your server-side payment processing endpoint
      data: JSON.stringify(payment),
      contentType: "application/json",
      success: function(response) {
        // Handle the success response
        console.log("Payment successful:", response);

        // Perform any necessary actions or show a success message to the user

        // Redirect to the confirmation page
        window.location.href = "/confirmation";
      },
      error: function(xhr, status, error) {
        // Handle the error response
        console.error("Payment failed:", error);

        // Display an error message to the user
        alert("Payment failed. Please try again.");
      }
    });
  });
});
