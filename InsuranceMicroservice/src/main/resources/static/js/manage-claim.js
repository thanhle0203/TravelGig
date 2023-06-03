$(document).ready(function() {
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
            row += '<td>' + '$'+ claim.repairPrice + '</td>';
            row += '<td>' + claim.status + '</td>';
            row += '<td>';
            
            // Loop through the images and add them to the row
            $.each(claim.images, function(index, image) {
                row += '<img src="' + image.url + '" alt="Claim Image" class="claim-image">';
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
            var claims = response;
            populateClaimsTable(claims);
        },
        error: function(xhr, status, error) {
            console.error('Error fetching claims: ' + error);
        }
    });
});
