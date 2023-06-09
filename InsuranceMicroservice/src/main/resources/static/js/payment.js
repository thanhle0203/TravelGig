$(document).ready(function() {
  // Set your publishable key
  var publishableKey = 'pk_test_8B9VvZ6OI2wjUnICvr2qArjv';
  // Retrieve the insured data from local storage
  var insuredData = JSON.parse(localStorage.getItem("insured"));

  if (insuredData) {
    // Update the payment details in the HTML elements
    $("#insuredName").text(insuredData.name);
    $("#autoInsurancePlan").text(insuredData.autoInsurance.autoPlan.name);
    $("#totalPrice").text("$" + (insuredData.autoInsurance.totalPrice / 12).toFixed(2));
  } else {
    // Handle the case when insuredData is not available
    console.error("Insured data not found. Please ensure that insuredData is set correctly.");
    return;
  }

  // Show/hide credit card fields based on payment method selection
  /*
  $("#paymentMethod").change(function() {
    var selectedPaymentMethod = $(this).val();
    if (selectedPaymentMethod === "card") {
      $("#creditCardFields").show();
    } else {
      $("#creditCardFields").hide();
    }
  });
  */

  // Submit the payment form
  $("#paymentForm").on("submit", function(event) {
    event.preventDefault();

    // Retrieve the payment form data
    var paymentMethod = $("#paymentMethod").val();
    var cardNumber = $("#cardNumber").val();
    var expiryDate = $("#expiryDate").val();
    var cvv = $("#cvv").val();
    var cvvNumber = parseInt(cvv);
    var nameOnCard = $("#nameOnCard").val();
    var street = $("#street").val();
    var city = $("#city").val();
    var state = $("#state").val();
    var zipCode = $("#zipCode").val();
    var email = $("#email").val();
    var phone = $("#phone").val();
	
    // Perform any necessary validation on the form fields

    // Create a payment object with the form data
    var paymentData = {
      paymentMethod: paymentMethod,
      cardNumber: cardNumber,
      expiryDate: expiryDate,
      cvv: cvvNumber,
      nameOnCard: nameOnCard,
      totalAmount: insuredData.autoInsurance.totalPrice/12,
      billingAddress: {
        street: street,
        city: city,
        state: state,
        zipCode: zipCode,
        email: email
      },
      phone: phone
    };

    // Send the payment data to the server for processing
    $.ajax({
      type: "POST",
      url: "http://localhost:8383/process-payment",
      data: JSON.stringify(paymentData),
      contentType: "application/json",
      success: function(response) {
        // Handle the success response
        console.log("Payment successful:", response);

        // Perform any necessary actions or show a success message to the user

        // Redirect to the confirmation page
        localStorage.setItem("paymentStatus", "Payment successful");
        localStorage.setItem("paymentData", JSON.stringify(paymentData));
        localStorage.setItem("paymentId", JSON.stringify(response.id));
        
        var paymentId = response.id;
        $.post("http://localhost:8282/sendPaymentDetails/" + paymentId, {}, function(response) {
            // Handle the response from the server
            console.log(response);
           window.location.href = "/paymentConfirmation?payment_id=" + paymentId;
        }).fail(function(jqXHR, textStatus, errorThrown) {
            // Handle the error case
            console.log("Failed to send booking details: " + errorThrown);
        });
        
        //window.location.href = "/paymentConfirmation?payment_id="+response.id;
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
