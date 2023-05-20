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
                        <h4 id="name">Comprehensive Plan</h4>
                        <h4 id="type">Full Coverage</h4>
                        <p id="description">Comprehensive coverage including collision, liability, uninsured motorist protection, and roadside assistance. Ideal for new, high-value cars.</p>
                        <label for="collisionDeductible">Collision Deductible:</label>
                        <select id="collisionDeductible" name="collisionDeductible">
                            <option value="500">$500</option>
                            <option value="1000">$1000</option>
                        </select>
                        <label for="uninsuredMotoristDeductible">Uninsured Motorist Protection Deductible:</label>
                        <select id="uninsuredMotoristDeductible" name="uninsuredMotoristDeductible">
                            <option value="500">$500</option>
                            <option value="1000">$1000</option>
                        </select>
                        <h5 id="basePrice">Base Price: $2000/year</h5>
                        <BR>
                        </BR>
                        <br><br>
                        <button type="submit" class="btn btn-primary" name="autoPlan" value="ComprehensivePlan" data-plan-auto-id="1">Select</button>
                    </div>
                </div>
                <div class="col-md-4" style="background-color: #d9d9d9; padding: 20px; border-radius: 10px;">
                    <div class="form-group">
                        <h4 id="name">Plus Plan</h4>
                        <h4 id="type">Partial Coverage</h4>
                        <p id="description">Balanced coverage including collision and liability protection. Suitable for medium-value cars or drivers wanting a balance between cost and coverage.</p>
                        <label for="collisionDeductible">Collision Deductible:</label>
                        <select id="collisionDeductible" name="collisionDeductible">
                            <option value="500">$500</option>
                            <option value="1000">$1000</option>
                        </select>
                        <label for="uninsuredMotoristDeductible">Uninsured Motorist Protection Deductible:</label>
                        <select id="uninsuredMotoristDeductible" name="uninsuredMotoristDeductible">
                            <option value="500">$500</option>
                            <option value="1000">$1000</option>
                        </select>
                        <h5 id="basePrice">Base Price: $1500/year</h5>
                        <BR>
                        </BR>
                        <br><br>
                        <button type="submit" class="btn btn-primary" name="autoPlan" value="PlusPlan" data-plan-auto-id="2">Select</button>
                    </div>
                </div>
                <div class="col-md-4" style="background-color: #bfbfbf; padding: 20px; border-radius: 10px;">
                    <div class="form-group">
                        <h4 id="name">Basic Plan</h4>
                        <h4 id="type">Basic Coverage</h4>
                        <p id="description">Minimum coverage meeting state requirements, typically including liability coverage only. Suitable for older, low-value cars or drivers needing economical options.</p>
                        
                        <h5 id="basePrice">Base Price: $1000/year</h5>
                        <BR>
                        </BR>
                        <br><br>
                        <button type="submit" class="btn btn-primary" name="autoPlan" value="PlusPlan" data-plan-auto-id="3">Select</button>
                    </div>
                </div>
            </div>
            <div id="planConfirmation">
                
            </div>
        </form>
    </div>
</body>
</html>

