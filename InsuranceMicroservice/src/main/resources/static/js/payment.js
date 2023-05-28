$(document).ready(function() {
    // Submit the form when the "Pay" button is clicked
    $("#paymentForm").on("submit", function(event) {
        // Prevent the default form submission
        event.preventDefault();

        // Get the payment details
        var cardNumber = $("#cardNumber").val();
        var expirationDate = $("#expirationDate").val();
        var cvc = $("#cvc").val();

        // Create a payment object
        var paymentData = {
            cardNumber: cardNumber,
            expirationMonth: expirationDate.slice(0, 2),
            expirationYear: expirationDate.slice(3),
            cvc: cvc
        };

        // Send the payment data to the server
        $.ajax({
            type: "POST",
            url: "http://localhost:8383/api/api/process-payment",
            data: JSON.stringify(paymentData),
            contentType: "application/json",
            success: function(response) {
                // Handle the success response
                console.log("Payment processed successfully:", response);
                // Display a success message to the user
                alert("Payment processed successfully");
                // Redirect to the success page or perform any other necessary actions
            },
            error: function(xhr, status, error) {
                // Handle the error response
                console.error("Payment processing failed:", error);
                // Display an error message to the user
                alert("Payment processing failed");
                // Perform any other necessary actions
            }
        });
    });
});
