$(document).ready(function() {
// Handle button click
    $('.insurance-btn').click(function() {
         var insuranceType = $(this).data('insurance');
         // Redirect user to the next page based on the insurance type
         window.location.href = './' + insuranceType;
     });
       
   // $('.insurance-btn').click(function() {
        //var insuranceType = $(this).data('insurance');
        // Make an AJAX call to the server
       // $.ajax({
            //url: '/getInsuranceTypeURL',
            //type: 'POST',
            //data: { insuranceType: insuranceType },
            //success: function(response) {
                // On success, redirect to the URL provided by the server
                //window.location.href = response.redirectURL;
            //},
            //error: function(error) {
                // Handle any errors
                //console.log('Error:', error);
            //}
        //});
    //});

});