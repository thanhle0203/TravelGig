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
      },
      error: function(xhr, status, error) {
        console.error(error);
      }
    });
  }

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
          '<td>' + claim.accidentDate + '</td>' +
          '<td>' + claim.description + '</td>' +
          '</td>' +
        '<td><img class="vehicle-damage-img" src="' + claim.images[0].data + '"  width="30" height="30"></td>' +
        
          '<td>' + '$' + (claim.repairPrice ? claim.repairPrice.toFixed(2) : '') + '</td>' +
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
          '</td>' +
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
  $(document).on('click', '.rejected-btn', function() {
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