$(document).ready(function() {
  // Retrieve insured data from localStorage
  // var insuredData = JSON.parse(localStorage.getItem("insured"));
  // var autoInsuranceData = JSON.parse(localStorage.getItem("autoInsuranceData"));

  // Fetch the insured data of the logged-in user
  $.ajax({
    url: "http://localhost:8282/insured/getInsured",
    method: "GET",
    success: function (insureds) {
      $.each(insureds, function (index, insured) {
        phone = insured.phone;
        console.log(phone);
      });

      // Fetch the insured data by phone number
      $.get("http://localhost:8282/insured/phone/" + phone)
        .done(function(response) {
          var insuredData = response;
          var autoInsuranceData = response.autoInsurance;

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
        })
        .fail(function(xhr, status, error) {
          // Handle the failure case
          console.log("Error fetching insured data:", error);
          $("#insuredInfo").html("<p>Error fetching insured data.</p>");
          $("#autoInsuranceInfo").html("");
        });
    },
    error: function(xhr, status, error) {
      // Handle the failure case
      console.log("Error fetching insured data:", error);
      $("#insuredInfo").html("<p>Error fetching insured data.</p>");
      $("#autoInsuranceInfo").html("");
    }
  });
});
