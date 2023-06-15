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
          var vehicleData = response.vehicle;

          // Display insured information
          var insuredInfo = `
            <h4>Name: ${insuredData.name}</h4>         
            <p>Phone: ${insuredData.phone}</p>
            <p>Email: ${insuredData.email}</p>
            <p>Address: ${insuredData.address.street}, ${insuredData.address.city}, ${insuredData.address.state}, ${insuredData.address.zipCode}</p>
          `;
          $("#insuredInfo").html(insuredInfo);

          // Display vehicle information
          var vehicleInfo = `
          	<h4>VIN: ${insuredData.vehicle.vin}</h4>
            <p>Make: ${insuredData.vehicle.make}</p>
            <p>Model: ${insuredData.vehicle.model}</p>       
            <p>Year: ${insuredData.vehicle.year}</p>
          `;
          $("#vehicleInfo").html(vehicleInfo);

          // Display auto insurance plan information
          var autoInsuranceInfo = `
            <h4>Auto Insurance Plan: ${autoInsuranceData.autoPlan.name}</h4>
            <p>Collision Deductible: $${autoInsuranceData.collisionDeductible}</p>
            <p>Uninsured Motorist Deductible: $${autoInsuranceData.uninsuredMotoristDeductible}</p>
            <h4>Total Price: $${autoInsuranceData.totalPrice} /year</h4>
          `;
          $("#autoInsuranceInfo").html(autoInsuranceInfo);

          // Display insured status
          var insuredStatus = `
            <p>Status: ${insuredData.status}</p>
          `;
          $("#insuredStatus").html(insuredStatus);

          // Check if insured has an approved status
          if (insuredData.status === "approved") {
            // Display the "Pay" button
            var payButton = `
              <button class="btn btn-primary" id="payButton">Pay</button>
            `;
            $("#payButtonContainer").html(payButton);

            // Handle the click event for the "Pay" button
            $("#payButton").click(function() {
              // Perform the necessary actions when the button is clicked
              // For example, you can redirect the user to the payment page
              window.location.href = "/payment?insuredId=" + insuredData.id;
            });
          }
          
          // Display insured status
var insuredStatus = `
    <p>Status: ${insuredData.status}</p>
`;
$("#insuredStatus").html(insuredStatus);

// Check if insured has an approved status
if (insuredData.status === "approved") {
    // Display the "Generate PDF" button
    var generatePdfButton = `
        <button class="btn btn-primary" id="generatePdfButton" data-insured-id="${insuredData.id}">Generate PDF</button>
    `;
    $("#generatePdfButtonContainer").html(generatePdfButton);
}

        })
        .fail(function(xhr, status, error) {
          // Handle the failure case
          console.log("Error fetching insured data:", error);
          $("#insuredInfo").html("<p>Error fetching insured data.</p>");
          $("#vehicleInfo").html("");
          $("#autoInsuranceInfo").html("");
          $("#insuredStatus").html("");
        });
    },
    error: function(xhr, status, error) {
      // Handle the failure case
      console.log("Error fetching insured data:", error);
      $("#insuredInfo").html("<p>Error fetching insured data.</p>");
      $("#vehicleInfo").html("");
      $("#autoInsuranceInfo").html("");
      $("#insuredStatus").html("");
    }
  });
  
  // Function to handle the click event on the "Generate PDF" button
    $(document).on('click', '#generatePdfButton', function() {
        // Retrieve the insured ID from the page
        var insuredId = $(this).data('insured-id');

        // Send the AJAX request to generate the PDF file
        $.ajax({
            url: 'http://localhost:8282/api/insured/' + insuredId + '/generate-pdf',
            type: 'GET',
            responseType: 'blob',
            success: function(response) {
                if (response && response.size > 0) {
                    var blob = new Blob([response], { type: 'application/pdf' });
                    var url = URL.createObjectURL(blob);

                    // Create a link element and simulate a click to initiate the download
                    var link = document.createElement('a');
                    link.href = url;
                    link.download = 'insurance.pdf';
                    link.click();

                    // Clean up the temporary URL
                    URL.revokeObjectURL(url);
                } else {
                    console.error('Error generating PDF: Invalid response');
                }
            },
            error: function(xhr, status, error) {
                console.error('Error generating PDF: ' + error);
            }
        });
    });
});
