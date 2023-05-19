$(document).ready(function() {
    $.ajax({
        url: 'http://localhost:8383/api/auto-plans', // replace with your actual API endpoint
        type: 'GET',
        success: function(data) {
            var planContainer = $(".row");
            data.forEach(function(plan) {
                var planForm = $("<form></form>").attr("action", "/autoConfirmationPlan").attr("method", "POST");
                var planDiv = $("<div class='col-md-4 form-group' style='background-color: #f2f2f2; padding: 20px; border-radius: 10px;'></div>");
                var planTitle = $("<h4></h4>").text(plan.name + " - " + plan.type);
                var planDescription = $("<p></p>").text(plan.description);
                var basePriceLabel = $("<label></label>").text('Base Price:');
                var basePrice = $("<h5></h5>").text('$' + plan.basePrice + '/year');

                // Add input fields for deductible selection if applicable
                var collisionDeductibleLabel = $("<label></label>").attr("for", "collisionDeductible" + plan.name).text("Collision Deductible:");
                var collisionDeductibleSelect = $("<select></select>").attr("id", "collisionDeductible" + plan.name).attr("name", "collisionDeductible");
                collisionDeductibleSelect.append($("<option value='500'>$500</option>"));
                collisionDeductibleSelect.append($("<option value='1000'>$1000</option>"));

                var uninsuredMotoristDeductibleLabel = $("<label></label>").attr("for", "uninsuredMotoristDeductible" + plan.name).text("Uninsured Motorist Protection Deductible:");
                var uninsuredMotoristDeductibleSelect = $("<select></select>").attr("id", "uninsuredMotoristDeductible" + plan.name).attr("name", "uninsuredMotoristDeductible");
                uninsuredMotoristDeductibleSelect.append($("<option value='500'>$500</option>"));
                uninsuredMotoristDeductibleSelect.append($("<option value='1000'>$1000</option>"));

                var planInput = $("<input type='hidden'/>").attr("name", "plan").attr("value", plan.name);
                var submitButton = $("<button type='submit' class='btn btn-primary'></button>").text("Select");

                // Append elements to planDiv
                planDiv.append(planTitle, planDescription, basePriceLabel, basePrice, collisionDeductibleLabel, collisionDeductibleSelect, uninsuredMotoristDeductibleLabel, uninsuredMotoristDeductibleSelect, planInput, submitButton);

                // Append planDiv to planForm
                planForm.append(planDiv);

                // Append planForm to planContainer
                planContainer.append(planForm);
            });
        },
        error: function() {
            alert("Error loading plans. Please try again later.");
        }
    });
});
