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

    // Function to handle the click event on the "Upload More" link
    $(document).on('click', '.upload-link', function(e) {
        e.preventDefault();

        var claimId = $(this).data('claim-id');

        // Create an input element of type file
        var inputFile = $('<input type="file" accept="image/*" multiple>');

        // Trigger a click event on the input element
        inputFile.trigger('click');

        // Listen for the change event on the input element
        inputFile.on('change', function() {
            var files = $(this).get(0).files;
            handleImageUpload(files, claimId);
        });
    });

    // Function to handle the click event on the "Generate PDF" button/link
$(document).on('click', '.generate-pdf-link', function(e) {
  e.preventDefault();

  var claimId = $(this).data('claim-id');
  console.log(claimId)

 // Send the AJAX request to generate the PDF file
$.ajax({
  url: 'http://localhost:8383/api/claims/' + claimId + '/generate-pdf',
  type: 'GET',
  //responseType: 'arraybuffer', // Set the response type as 'arraybuffer' to handle binary data
  xhrFields: {
    responseType: 'blob'
  },
 success: function(response, status, xhr) {
  if (response && response.size > 0) {
    var blob = new Blob([response], { type: 'application/pdf' });
    var url = URL.createObjectURL(blob);

    // Create a link element and simulate a click to initiate the download
    var link = document.createElement('a');
    link.href = url;
    link.download = 'claim.pdf';
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


    // Function to handle the image upload
    function handleImageUpload(files, claimId) {
        // Check if any files were selected
        if (files.length > 0) {
            var formData = new FormData();

            // Append each file to the formData object
            for (var i = 0; i < files.length; i++) {
                formData.append('image', files[i]);
            }

            // Send the AJAX request to upload the images
            $.ajax({
                url: 'http://localhost:8383/api/claims/upload/' + claimId,
                type: 'POST',
                data: formData,
                contentType: false,
                processData: false,
                success: function(response) {
                    // Handle the success case

                    // Retrieve the updated claims data and reload the table
                    retrieveClaimsDataAndReloadTable();
                },
                error: function(xhr, status, error) {
                    // Handle the error case
                    console.error('Error uploading images: ' + error);
                }
            });
        }
    }

    // Function to retrieve the updated claims data and reload the table
    function retrieveClaimsDataAndReloadTable() {
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
    }

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

            // Loop through the images and add them to the row
            row += '<a href="#" class="upload-link" data-claim-id="' + claim.id + '">Upload More</a>';

            row += '</td>';
            row += '<td>';
            row += '<a href="#" class="generate-pdf-link" data-claim-id="' + claim.id + '">claim.pdf</a>';
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
