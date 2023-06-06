$(document).ready(function() {
    // Function to handle the click event on claim images
    $(document).on('click', '.vehicle-damage-img', function() {     
        var imgSrc = $(this).attr('src');
 		 if (imgSrc && imgSrc !== 'undefined') { // Check if the image data is defined
    		var imgSrc = 'data:image/png;base64,' + imgSrc;
    		$('#vehicleDamageImage').attr('src', imgSrc);
    		$('#vehicleDamageModal').modal('show');
  		}
    });

    // Function to populate the claims table with data
    function populateClaimsTable(claims) {
        var tableBody = $('#claimTable tbody');
        tableBody.empty();

        $.each(claims, function(index, claim) {
            var row = '<tr>';
            row += '<td>' + claim.accidentDate + '</td>';
            row += '<td>' + claim.vehicle.make + '</td>';
            row += '<td>' + claim.vehicle.model + '</td>';
            row += '<td>' + claim.vehicle.year + '</td>';
            row += '<td>' + claim.description + '</td>';
            row += '<td>' + '$' + claim.repairPrice + '</td>';
            row += '<td>' + claim.status + '</td>';
            row += '<td>' + claim.phone + '</td>';
            row += '<td>';

            // Loop through the images and add them to the row
            $.each(claim.images, function(index, image) {
                row += '<img class="vehicle-damage-img" src="' + image.data + '"  width="30" height="30">';
            });

            row += '</td>';
            row += '</tr>';

            tableBody.append(row);
        });
    }

    // Fetch the claim data from the server
    $.ajax({
        url: 'http://localhost:8383/api/claims',
        type: 'GET',
        success: function(response) {
            var phone = response.phone;
            // Fetch the insured data by phone number
            $.get("http://localhost:8282/claim/phone/" + phone)
                .done(function(response) {
                    var claims = response;
                    populateClaimsTable(claims);
                })
                .fail(function(xhr, status, error) {
                    // Handle the failure case
                    console.log("Error fetching claim data:", error);
                });
        },
        error: function(xhr, status, error) {
            console.error('Error fetching claims: ' + error);
        }
    });
});
