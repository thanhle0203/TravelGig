$(document).ready(function() {
  // Function to update claim status
  function updateClaimStatus(claimId, newStatus) {
    $.ajax({
      url: 'http://localhost:8383/api/claims/' + claimId,
      method: 'PUT',
      data: { status: newStatus },
      success: function(response) {
        console.log('Claim status updated successfully.');
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
      data: { repairPrice: repairPrice },
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
          '<td>' + claim.id + '</td>' +
          '<td>' + claim.description + '</td>' +
          '<td>' + claim.status + '</td>' +
          '<td>' +
          '<button onclick="updateClaimStatus(' + claim.id + ', \'approved\')">Approve</button>' +
          '<button onclick="updateClaimStatus(' + claim.id + ', \'rejected\')">Reject</button>' +
          '<button onclick="showRepairPriceDialog(' + claim.id + ')">Generate Repair Price</button>' +
          '</td>' +
          '</tr>';
        
        $('#claimsTableBody').append(row);
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
});
