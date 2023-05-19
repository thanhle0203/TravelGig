<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
    pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Auto Insurance Plans</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <!-- Include jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="./js/autoInsurancePlans.js"></script>
</head>
<body>
    <div class="container">
        <h1>Select Auto Insurance Plan</h1>
        <br><br><br>
        <form id="planForm" action="autoConfirmationPlan" method="POST">
            <div class="row">
                <div class="col-md-4" style="background-color: #f2f2f2; padding: 20px; border-radius: 10px;">
                    <div class="form-group">
                        <h4>Plan A - Premium:</h4>
                        <p>Comprehensive coverage including collision, liability, uninsured motorist protection, and roadside assistance. Ideal for new, high-value cars.</p>
                        <label for="collisionDeductibleA">Collision Deductible:</label>
                        <select id="collisionDeductibleA" name="collisionDeductibleA">
                            <option value="500">$500</option>
                            <option value="1000">$1000</option>
                        </select>
                        <label for="uninsuredMotoristDeductibleA">Uninsured Motorist Protection Deductible:</label>
                        <select id="uninsuredMotoristDeductibleA" name="uninsuredMotoristDeductibleA">
                            <option value="500">$500</option>
                            <option value="1000">$1000</option>
                        </select>
                        <h5>Base Price: $1500/year</h5>
                        <BR>
                        </BR>
                        <br><br>
                        <button type="submit" class="btn btn-primary" name="plan" value="PlanA">Select</button>
                    </div>
                </div>
                <div class="col-md-4" style="background-color: #d9d9d9; padding: 20px; border-radius: 10px;">
                    <div class="form-group">
                        <h4>Plan B - Plus:</h4>
                        <p>Balanced coverage including collision and liability protection. Suitable for medium-value cars or drivers wanting a balance between cost and coverage.</p>
                        <label for="collisionDeductibleB">Collision Deductible:</label>
                        <select id="collisionDeductibleB" name="collisionDeductibleB">
                            <option value="500">$500</option>
                            <option value="1000">$1000</option>
                        </select>
                        <h5>Base Price: $1000/year</h5>
                        
                        <br><br><br><br><br><br>
                        <button type="submit" class="btn btn-primary" name="plan" value="PlanB">Select</button>
                    </div>
                </div>
                <div class="col-md-4" style="background-color: #bfbfbf; padding: 20px; border-radius: 10px;">
                    <div class="form-group">
                        <h4>Plan C - Basic:</h4>
                        <p>Minimum coverage meeting state requirements, typically including liability coverage only. Suitable for older, low-value cars or drivers needing economical options.</p>
                        <h5>Base Price: $500/year</h5>
                        <br><br><br><br><br><br>
                        <button type="submit" class="btn btn-primary" name="plan" value="PlanC">Select</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</body>
</html>

