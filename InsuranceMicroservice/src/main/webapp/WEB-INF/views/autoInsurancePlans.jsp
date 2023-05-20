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
                        <h4>Comprehensive Plan</h4>
                        <h4>Full Coverage</h4>
                        <p>Comprehensive coverage including collision, liability, uninsured motorist protection, and roadside assistance. Ideal for new, high-value cars.</p>
                        <label for="collisionDeductible1">Collision Deductible:</label>
                        <select id="collisionDeductible1" name="collisionDeductible1">
                            <option value="0" selected>$0</option>
                            <option value="500">$500</option>
                            <option value="1000">$1000</option>
                        </select>
                        <label for="uninsuredMotoristDeductible1">Uninsured Motorist Protection Deductible:</label>
                        <select id="uninsuredMotoristDeductible1" name="uninsuredMotoristDeductible1">
                            <option value="0" selected>$0</option>
                            <option value="500">$500</option>
                            <option value="1000">$1000</option>
                        </select>
                        
                        <h5>Base Price: $2000/year</h5>
                        <BR>
                        </BR>
                        <br><br>
                        <button type="button" class="btn btn-primary select-plan" data-plan="ComprehensivePlan">Select</button>
                    </div>
                </div>

                <div class="col-md-4" style="background-color: #d9d9d9; padding: 20px; border-radius: 10px;">
                    <div class="form-group">
                      <h4>Plus Plan</h4>
                      <h4>Partial Coverage</h4>
                      <p>Balanced coverage including collision                       and liability protection. Suitable for medium-value cars or drivers wanting a balance between cost and coverage.</p>
                      <label for="collisionDeductible2">Collision Deductible:</label>
                      <select id="collisionDeductible2" name="collisionDeductible2">
                        <option value="0" selected>$0</option>
                        <option value="500">$500</option>
                        <option value="1000">$1000</option>
                      </select>
                      <label for="uninsuredMotoristDeductible2">Uninsured Motorist Protection Deductible:</label>
                      <select id="uninsuredMotoristDeductible2" name="uninsuredMotoristDeductible2">
                        <option value="0" selected>$0</option>
                        <option value="500">$500</option>
                        <option value="1000">$1000</option>
                      </select>
                      <h5>Base Price: $1500/year</h5>
                      <BR></BR>
                      <br><br>
                      <button type="button" class="btn btn-primary select-plan" data-plan="PlusPlan">Select</button>
                    </div>
                  </div>
                  
                <div class="col-md-4" style="background-color: #bfbfbf; padding: 20px; border-radius: 10px;">
                    <div class="form-group">
                        <h4>Basic Plan</h4>
                        <h4>Basic Coverage</h4>
                        <p>Minimum coverage meeting state requirements, typically including liability coverage only. Suitable for older, low-value cars or drivers needing economical options.</p>
                        
                        <h5>Base Price: $1000/year</h5>
                        <BR>
                        </BR>
                        <br><br>
                        <button type="button" class="btn btn-primary select-plan" data-plan="BasicPlan">Select</button>
                    </div>
                </div>
            </div>
            <div id="planConfirmation">

            </div>
        </form>
    </div>
</body>
</html>

