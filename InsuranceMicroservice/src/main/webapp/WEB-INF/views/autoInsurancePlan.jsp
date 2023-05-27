<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Auto Insurance Plans</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <!-- Include jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <!-- Include Bootstrap JavaScript -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="./js/autoInsurancePlan.js"></script>
    <!-- Include navbar.css -->
    <link rel="stylesheet" href="/css/navbar.css">
    <!-- Include footer.css -->
    <link rel="stylesheet" href="/css/footer.css">
    <style>    
        .card {
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
        }
        .bg-light-gray {
            background-color: #f2f2f2;
        }
        .bg-light-blue {
            background-color: #d9d9d9;
        }
        .bg-light-green {
            background-color: #bfbfbf;
        }
        .card-deck .card {
            height: 100%;
        }
    </style>
</head>
<body>
    <!-- Include navbar.jsp -->
    <jsp:include page="navbar.jsp" />

    <div class="container">
        <h1 class="mt-3 mb-4 mt-4">Select Auto Insurance Plan</h1>
        <form id="autoInsurancePlanForm" action="autoConfirmationPlan.jsp" method="post">
            <%-- Add the hidden input field for autoInsuranceId --%>
            <input type="hidden" id="autoInsurance_id" name="autoInsurance_id" value="12345">

            <div class="card-deck">
                <div class="card bg-light-gray">
                    <div class="card-body">
                        <h4 class="card-title">Comprehensive Plan</h4>
                        <h4 class="card-subtitle mb-3">Full Coverage</h4>
                        <p class="card-text">Comprehensive coverage including collision, liability, uninsured motorist protection, medical bills, car rental, and roadside assistance. Ideal for new, high-value cars.</p>
                        <label for="collisionDeductible1">Collision Deductible:</label>
                        <select id="collisionDeductible1" name="collisionDeductible1">       
                            <option value="500" selected>$500</option>
                            <option value="1000">$1000</option>
                        </select>
                        <label for="uninsuredMotoristDeductible1">Uninsured Motorist Deductible:</label>
                        <select id="uninsuredMotoristDeductible1" name="uninsuredMotoristDeductible1">
                            <option value="500" selected>$500</option>
                            <option value="1000">$1000</option>
                        </select>
                        <h5 class="mt-4">Base Price: $2000/year</h5>
                        <button type="button" class="btn btn-primary select-plan mt-4" data-plan="ComprehensivePlan">Select</button>
                    </div>
                </div>
                <div class="card bg-light-blue">
                    <div class="card-body">
                        <h4 class="card-title">Plus Plan</h4>
                        <h4 class="card-subtitle mb-3">Partial Coverage</h4>
                        <p class="card-text">Balanced coverage including collision and liability protection. Suitable for medium-value cars or drivers wanting a balance between cost and coverage.</p>
                        <label for="collisionDeductible2">Collision Deductible:</label>
                        <select id="collisionDeductible2" name="collisionDeductible2">
                            <option value="500" selected>$500</option>
                            <option value="1000">$1000</option>
                        </select>
                        <label for="uninsuredMotoristDeductible2">Uninsured Motorist Deductible:</label>
                        <select id="uninsuredMotoristDeductible2" name="uninsuredMotoristDeductible2">
                            <option value="500" selected>$500</option>
                            <option value="1000">$1000</option>
                        </select>
                        <h5 class="mt-4">Base Price: $1500/year</h5>
                        <button type="button" class="btn btn-primary select-plan mt-4" data-plan="PlusPlan">Select</button>
                    </div>
                </div>
                <div class="card bg-light-green">
                    <div class="card-body">
                        <h4 class="card-title">Basic Plan</h4>
                        <h4 class="card-subtitle mb-3">Basic Coverage</h4>
                        <p class="card-text">Minimum coverage meeting state requirements, typically including liability coverage only. Suitable for older, low-value cars or drivers needing economical options.</p>
                        <h5 class="mt-4">Base Price: $1000/year</h5>
                        <button type="button" class="btn btn-primary select-plan mt-4" data-plan="BasicPlan">Select</button>
                    </div>
                </div>
            </div>
        </form>

        <!-- Modal for Confirmation Details -->
        <div class="modal fade" id="confirmationModal" tabindex="-1" role="dialog" aria-labelledby="confirmationModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="confirmationModalLabel">Auto Plan Details</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body" id="confirmation-details">
                        <!-- Auto plan details will be dynamically filled here -->
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Confirm</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Include footer.jsp -->
    <jsp:include page="footer.jsp" />

</body>
</html>
