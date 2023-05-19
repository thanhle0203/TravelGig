$(document).ready(function() {
    $('#confirm-plan-button').click(function() {
        // Get the data from the selected plan.
        // This is just a placeholder, replace it with actual data.
        var autoInsurance = {
            // Fill this object with the appropriate data.
        };

        $.ajax({
            url: 'http://localhost:8383/api/auto-insurances/confirm-auto-plan',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(autoInsurance),
            success: function(response) {
                // Handle the success of the confirmation.
                // For example, you could redirect to a success page or display a success message.
                console.log(response);
                alert('Your auto insurance plan has been confirmed!');
                window.location.href = '/confirmationSuccess';
            },
            error: function() {
                // Handle the error from the confirmation.
                // For example, you could display an error message.
                alert('Error confirming your auto insurance plan. Please try again.');
            }
        });
    });
});
