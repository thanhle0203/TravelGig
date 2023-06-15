$(document).ready(function() {
  // Function to update claim status
  function updateClaimStatus(claimId, newStatus) {
    $.ajax({
      url: 'http://localhost:8383/api/claims/' + claimId + '/status',
      method: 'PUT',
      data: newStatus,
      crossDomain: true,
      xhrFields: {
        withCredentials: true
      },
      contentType: 'text/plain', // Add this option
      success: function(response) {
        console.log('Claim status updated successfully.');
        
        // Retrieve the updated claims data and reload the table
      	retrieveClaimsDataAndReloadTable();
      },
      error: function(xhr, status, error) {
        console.error(error);
      }
    });
  }
  
  // Function to update insured status
  function updateInsuredStatus(insuredId, newStatus) {
    $.ajax({
      url: 'http://localhost:8383/api/insured/' + insuredId + '/status',
      method: 'PUT',
      data: newStatus,
      crossDomain: true,
      xhrFields: {
        withCredentials: true
      },
      contentType: 'text/plain', // Add this option
      success: function(response) {
        console.log('Insured status updated successfully.');
        // Retrieve the updated insured data and reload the table
      	retrieveInsuredDataAndReloadTable();
      },
      error: function(xhr, status, error) {
        console.error(error);
      }
    });
  }
  
  // Function to retrieve the updated insured data and reload the table
function retrieveInsuredDataAndReloadTable() {
  $.ajax({
    url: 'http://localhost:8383/api/insured',
    method: 'GET',
    success: function(response) {
      // Clear the existing insured table
      $('#insuredTableBody').empty();

      // Populate the insured table with the received data
      response.forEach(function(insured) {
        var row = '<tr>' +
          '<td><span class="insured-id">' + insured.id + '</span></td>' +
          '<td>' + insured.name + '</td>' +
          '<td>' + insured.email + '</td>' +
          '<td>' + insured.phone + '</td>' +
          '<td><img class="driver-license-img" src="' + insured.document.driverLicense + '"  width="30" height="30"></td>' +
          '<td>' + insured.vehicle.make + '</td>' +
          '<td>' + insured.vehicle.model + '</td>' +
          '<td>' + insured.vehicle.year + '</td>' +
          '<td>' + insured.autoInsurance.autoPlan.name + '</td>' +
          '<td>' + '$' + insured.autoInsurance.totalPrice + '</td>' +
          '<td>' + insured.status + '</td>' +
          '<td>' +
          '<div class="btn-group" role="group" aria-label="Insured Actions">' +
          '<button type="button" class="btn btn-success approve-btn">Approve</button>' +
          '<button type="button" class="btn btn-danger reject-btn">Reject</button>' +
          '</div>' +
          '</td>' +
          '</tr>';

        $('#insuredTableBody').append(row);
      });
    },
    error: function(xhr, status, error) {
      console.error(error);
    }
  });
}

// Fetch insured data from the backend API and populate the insured table
retrieveInsuredDataAndReloadTable();

  // Function to update claim with repair price
  function updateClaimRepairPrice(claimId, repairPrice) {
    $.ajax({
      url: 'http://localhost:8383/api/claims/' + claimId + '/repair-price',
      method: 'PUT',
      data: JSON.stringify(repairPrice),
      crossDomain: true,
      xhrFields: {
        withCredentials: true
      },
      contentType: 'application/json', // Set the content type to application/json
      success: function(response) {
        console.log('Repair price updated successfully.');
        
        // Retrieve the updated claims data and reload the table
      	retrieveClaimsDataAndReloadTable();
      },
      error: function(xhr, status, error) {
        console.error(error);
      }
    });
  }
  
  // Function to retrieve the updated claims data and reload the table
function retrieveClaimsDataAndReloadTable() {
  $.ajax({
    url: 'http://localhost:8383/api/claims',
    method: 'GET',
    success: function(response) {
      // Clear the existing claims table
      $('#claimsTableBody').empty();

      // Populate the claims table with the received data
      response.forEach(function(claim) {
        var row = '<tr>' +
          '<td><span class="claim-id">' + claim.id + '</span></td>' +
          '<td>' + claim.phone + '</td>' +
          '<td>' + claim.accidentDate + '</td>' +
          '<td>' + claim.vehicle.make + '</td>' +
          '<td>' + claim.vehicle.model + '</td>' +
          '<td>' + claim.vehicle.year + '</td>' +
          '<td>' + claim.description + '</td>' +
          '<td>'; // Start the column for vehicle damage images

        // Loop through the images and add them to the row
        $.each(claim.images, function(index, image) {
          row += '<img class="vehicle-damage-img" src="' + image.data + '"  width="30" height="30">';
        });

        row += '</td>' +
          '<td>' + (claim.repairPrice ? '$' + claim.repairPrice.toFixed(2) : '') + '</td>' +
          '<td>' + claim.status + '</td>' +
          '<td>' +
          '<div class="btn-group" role="group" aria-label="Claim Actions">' +
          '<button type="button" class="btn btn-success approve-btn">Approve</button>' +
          '<button type="button" class="btn btn-danger reject-btn">Reject</button>' +
          '<button type="button" class="btn btn-info generate-price-btn">Generate Repair Price</button>' +
          '</div>' +
          '</td>' +
          '</tr>';

        $('#claimsTableBody').append(row);
      });
    },
    error: function(xhr, status, error) {
      console.error(error);
    }
  });
}


  // Fetch claims data from the backend API
  $.ajax({
    url: 'http://localhost:8383/api/claims',
    method: 'GET',
    success: function(response) {
      // Populate the claims table with the received data
      response.forEach(function(claim) {
        var row = '<tr>' +
          '<td><span class="claim-id">' + claim.id + '</span></td>' +
          '<td>' + claim.phone + '</td>' +
          '<td>' + claim.accidentDate + '</td>' +
          '<td>' + claim.vehicle.make + '</td>' +
          '<td>' + claim.vehicle.model + '</td>' +
          '<td>' + claim.vehicle.year + '</td>' +
          '<td>' + claim.description + '</td>' +
          '<td>'; // Start the column for vehicle damage images
        
        // Loop through the images and add them to the row
        $.each(claim.images, function(index, image) {
             row += '<img class="vehicle-damage-img" src="' + image.data + '"  width="30" height="30">';
        });
        
        // Loop through the images and add them to the row
        /*
  $.each(claim.images, function(index, image) {
    row += '<a href="' + image.url + '" target="_blank">' + image.filename + '</a>';
    if (index !== claim.images.length - 1) {
      row += ', ';
    }
  });
	*/
        row += '</td>' +
          '<td>' + (claim.repairPrice ? '$' + claim.repairPrice.toFixed(2) : '') + '</td>' +
          '<td>' + claim.status + '</td>' +
          '<td>' +
          '<div class="btn-group" role="group" aria-label="Claim Actions">' +
          '<button type="button" class="btn btn-success approve-btn">Approve</button>' +
          '<button type="button" class="btn btn-danger reject-btn">Reject</button>' +
          '<button type="button" class="btn btn-info generate-price-btn">Generate Repair Price</button>' +
          '</div>' +
          '</td>' +
          '</tr>';

        $('#claimsTableBody').append(row);
      });
    },
    error: function(xhr, status, error) {
      console.error(error);
    }
  });

  // Fetch insured data from the backend API
  $.ajax({
    url: 'http://localhost:8383/api/insured',
    method: 'GET',
    success: function(response) {
      // Populate the insured table with the received data
      response.forEach(function(insured) {
        var row = '<tr>' +
          '<td><span class="insured-id">' + insured.id + '</span></td>' +
          '<td>' + insured.name + '</td>' +
          '<td>' + insured.email + '</td>' +
          '<td>' + insured.phone + '</td>' +
          '<td><img class="driver-license-img" src="' + insured.document.driverLicense + '"  width="30" height="30"></td>' +
          '<td>' + insured.vehicle.make + '</td>' +
          '<td>' + insured.vehicle.model + '</td>' +
          '<td>' + insured.vehicle.year + '</td>' +
          '<td>' + insured.autoInsurance.autoPlan.name + '</td>' +
          '<td>' + '$' + insured.autoInsurance.totalPrice + '</td>' +
          '<td>' + insured.status + '</td>' +
          '<td>' +
          '<div class="btn-group" role="group" aria-label="Insured Actions">' +
          '<button type="button" class="btn btn-success approve-btn">Approve</button>' +
          '<button type="button" class="btn btn-danger reject-btn">Reject</button>' +
          '</div>' +
          '</td>' +
          '</tr>';

        $('#insuredTableBody').append(row);
      });
    },
    error: function(xhr, status, error) {
      console.error(error);
    }
  });

  // Show the repair price dialog
  function showRepairPriceDialog(claimId) {
    $('#claimId').val(claimId);
    $('#repairPriceDialog').modal('show');
  }

  // Handle form submission to update claim repair price
  $('#repairPriceForm').submit(function(e) {
    e.preventDefault();
    var claimId = $('#claimId').val();
    var repairPrice = $('#price').val();
    updateClaimRepairPrice(claimId, repairPrice);
    $('#repairPriceDialog').modal('hide');
  });

  // Handle click event for Approve button
  $(document).on('click', '.approve-btn', function() {
    var claimId = $(this).closest('tr').find('.claim-id').text();
    updateClaimStatus(claimId, 'approved');
  });

  // Handle click event for Reject button
  $(document).on('click', '.reject-btn', function() {
    var claimId = $(this).closest('tr').find('.claim-id').text();
    updateClaimStatus(claimId, 'rejected');
  });

  // Handle click event for Generate Repair Price button
  $(document).on('click', '.generate-price-btn', function() {
    var claimId = $(this).closest('tr').find('.claim-id').text();
    showRepairPriceDialog(claimId);
  });

  // Handle click event for Vehicle Damage image
  $(document).on('click', '.vehicle-damage-img', function() {
    var imgData = $(this).attr('src');
    if (imgData && imgData !== 'undefined') { // Check if the image data is defined
      var imgSrc = 'data:image/png;base64,' + imgData;
      $('#vehicleDamageImage').attr('src', imgSrc);
      $('#vehicleDamageModal').modal('show');
    }
  });
  
  
  
// Handle click event for Approve button
  $(document).on('click', '.approve-btn', function() {
    var insuredId = $(this).closest('tr').find('.insured-id').text();
    updateInsuredStatus(insuredId, 'approved');
  });

  // Handle click event for Reject button
  $(document).on('click', '.reject-btn', function() {
    var insuredId = $(this).closest('tr').find('.insured-id').text();
    updateInsuredStatus(insuredId, 'rejected');
  });

  // Handle click event for Driver License image
  $(document).on('click', '.driver-license-img', function() {
    var imgData = $(this).attr('src'); // Assuming the byte data is already encoded as Base64
    var imgSrc = 'data:image/png;base64,' + imgData;
    $('#driverLicenseImage').attr('src', imgSrc);
    $('#driverLicenseModal').modal('show');
  });
});
