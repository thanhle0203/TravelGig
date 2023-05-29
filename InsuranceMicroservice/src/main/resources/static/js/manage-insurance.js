$(document).ready(function() {
  // Retrieve insured data from localStorage
  var insuredData = JSON.parse(localStorage.getItem("insured"));
  var autoInsuranceData = JSON.parse(localStorage.getItem("autoInsuranceData"));

  // Display insured information
  var insuredInfo = `
    <h4>Name: ${insuredData.name}</h4>
    <p>Address: ${insuredData.address.street}, ${insuredData.address.city}, ${insuredData.address.state}, ${insuredData.address.zipCode}</p>
    <p>Phone: ${insuredData.phone}</p>
    <p>Email: ${insuredData.email}</p>
  
  `;
  $("#insuredInfo").html(insuredInfo);

  // Display auto insurance plan information
  var autoInsuranceInfo = `
    <h4>Auto Insurance Plan: ${autoInsuranceData.autoPlan.name}</h4>
    <p>Total Price: $${autoInsuranceData.totalPrice} /year</p>
    <p>Collision Deductible: $${autoInsuranceData.collisionDeductible}</p>
    <p>Uninsured Motorist Deductible: $${autoInsuranceData.uninsuredMotoristDeductible}</p>
  `;
  $("#autoInsuranceInfo").html(autoInsuranceInfo);
});
