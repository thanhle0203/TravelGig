// paymentConfirmation.js
$(document).ready(function() {
  // Retrieve the insured data from local storage
  var insuredData = JSON.parse(localStorage.getItem("insured"));
  var paymentData = JSON.parse(localStorage.getItem("paymentData"));
	var paymentId = JSON.parse(localStorage.getItem("paymentId"));
	
  //if (insuredData) {
    // Update the confirmation details in the HTML elements
    $("#insuredName").text(insuredData.name);
    $("#autoInsurancePlan").text(insuredData.autoInsurance.autoPlan.name);
    $("#totalPrice").text("$" + insuredData.autoInsurance.totalPrice.toFixed(2));
  //} else {
    // Handle the case when insuredData is not available
    //console.error("Insured data not found. Please ensure that insuredData is set correctly.");
    //return;
  //}

  //if (paymentData) {
    // Update the payment details in the HTML elements
    $("#paymentId").text(paymentId);
    $("#amount").text("$" + paymentData.totalAmount);
    $("#currency").text("USD");
    $("#paymentStatus").text("Success");
  //} else {
    // Handle the case when paymentData is not available
    //console.error("Payment data not found. Please ensure that paymentData is set correctly.");
    //return;
  //}
});
