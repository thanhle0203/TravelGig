/*
$(document).ready(function() {
// Handle button click
     $("#planForm").submit(function(e) {
        e.preventDefault(); // prevent form from submitting the default way
        window.location.href = '/autoConfirmationPlan';
     });
});
*/ 


$(document).ready(function() {
    $('#planForm').on('submit', function(event) {
        event.preventDefault(); // prevent the form from submitting normally

        var selectedPlan = $('button[type=submit][clicked=true]').val();
        var collisionDeductible;
        var uninsuredMotoristDeductible;

        // Get the selected deductibles based on the selected plan
        switch (selectedPlan) {
            case 'PlanA':
                collisionDeductible = $('#collisionDeductibleA').val();
                uninsuredMotoristDeductible = $('#uninsuredMotoristDeductibleA').val();
                break;
            case 'PlanB':
                collisionDeductible = $('#collisionDeductibleB').val();
                break;
            case 'PlanC':
                // Plan C does not have deductibles
                break;
        }

        // Build the data to send
        var data = {
            plan: selectedPlan,
            collisionDeductible: collisionDeductible,
            uninsuredMotoristDeductible: uninsuredMotoristDeductible
        };

        // Send the data to the server
        $.post('autoConfirmationPlan.jsp', data, function(response) {
            // Handle the server response here
            // For example, you could display a confirmation message
            alert('Plan selected successfully!');

            // Or redirect to another page
            window.location.href = '/autoConfirmationPlan';
        });
    });

    $('button[type=submit]').click(function() {
        $('button[type=submit]').removeAttr('clicked');
        $(this).attr('clicked', 'true');
    });
});
