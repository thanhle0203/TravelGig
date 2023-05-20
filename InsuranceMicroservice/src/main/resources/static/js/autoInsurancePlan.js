$(document).ready(function() {
    $.ajax({
        url: 'http://localhost:8383/api/auto-plans',
        type: 'GET',
        success: function(data) {
            var planContainer = $(".row");
            data.forEach(function(plan) {
                var planDiv = $("<div class='col-md-4 form-group' style='background-color: #f2f2f2; padding: 20px; border-radius: 10px;'></div>");
                //var planForm = $("<form></form>").attr("action", "/autoConfirmationPlan").attr("method", "POST");
                var planForm = $("<form></form>").attr("action", "http://localhost:8383/api/auto-insurances/saveSelectedPlan").attr("method", "POST");
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

                // Handle form submission
                planForm.on('submit', function(e) {
                    e.preventDefault();

                    var selectedPlanForm = $(this);
                    var selectedPlan = {
                        autoPlan: {
                            name: selectedPlanForm.find("[name='plan']").val()
                        },
                        collisionDeductible: parseInt(selectedPlanForm.find("[name='collisionDeductible']").val()),
                        uninsuredMotoristDeductible: parseInt(selectedPlanForm.find("[name='uninsuredMotoristDeductible']").val()),
                        selected: true
                    };

                    $.ajax({
                        url: 'http://localhost:8383/api/auto-insurances/saveSelectedPlan',
                        type: 'POST',
                        contentType: 'application/json',
                        data: JSON.stringify(selectedPlan),
                        success: function(response) {
                            // If the server's response contains a confirmation message, display that message
                            if (response.message) {
                                alert(response.message);
                                // Redirect to the confirmation page
                                window.location.href = '/autoConfirmationPlan';
                            } else {
                                alert("Error selecting the plan. Please try again later.");
                            }
                        },
                        //error: function() {
                            //alert("Error selecting the plan. Please try again later.");
                        //}
                        error: function(xhr, status, error) {
    console.log(xhr.responseText); // Log the response from the server
    alert("Error selecting the plan. Please try again later.");
}

                    });
                });
            });
        },
        error: function() {
            alert("Error loading plans. Please try again later.");
        }
    });
});

