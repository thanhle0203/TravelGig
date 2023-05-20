$(document).ready(function() {
    $.ajax({
        url: 'http://localhost:8383/api/auto-insurances/selected-plans/{planAutoId}',  
        type: 'GET',
        success: function(data) {
            var planConfirmation = $("#planConfirmation");
            data.forEach(function(plan) {
                // Assuming that 'plan' is the confirmed AutoInsurance object returned by your server...
                var planTitle = $("<h4></h4>").text(plan.autoPlan.name + " - " + plan.autoPlan.type);
                var planDescription = $("<p></p>").text(plan.autoPlan.description);
                var basePrice = $("<h5></h5>").text('Base Price: $' + plan.autoPlan.basePrice + '/year');
                var collisionDeductible = $("<h5></h5>").text('Collision Deductible: $' + plan.collisionDeductible);
                var uninsuredMotoristDeductible = $("<h5></h5>").text('Uninsured Motorist Deductible: $' + plan.uninsuredMotoristDeductible);
                var totalPrice = $("<h5></h5>").text('Total Price: $' + plan.totalPrice + '/year');  // assuming 'totalPrice' is a property of the AutoInsurance object
                
                // Append these elements to the planConfirmation div
                planConfirmation.append(planTitle, planDescription, basePrice, collisionDeductible, uninsuredMotoristDeductible, totalPrice);
            });
        },
        error: function() {
            alert("Error loading confirmed plan details. Please try again later.");
        }
    });
});
