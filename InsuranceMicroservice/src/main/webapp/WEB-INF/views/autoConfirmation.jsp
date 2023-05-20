<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Auto Insurance Confirmation</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1 class="mt-5 mb-4">Auto Insurance Confirmation</h1>
        <div class="card">
            <div class="card-body">
                <h2 class="card-title">Auto Plan Details</h2>
                <p class="card-text"><strong>Plan Title:</strong> ${param.planTitle}</p>
                <p class="card-text"><strong>Description:</strong> ${param.planDescription}</p>
                <p class="card-text"><strong>Collision Deductible:</strong> $${param.collisionDeductible}</p>
                <p class="card-text"><strong>Uninsured Motorist Deductible:</strong> $${param.uninsuredMotoristDeductible}</p>
                <p class="card-text"><strong>Total Price:</strong> $${param.totalPrice}</p>
                <button type="button" class="btn btn-primary" id="confirmButton">Confirm</button>
            </div>
        </div>
    </div>

    <!-- Include jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <!-- Include autoConfirmation.js -->
    <script src="./js/autoConfirmation.js"></script>
    <script src="./js/autoInsurancePlans.js"></script>
    <script>
        $(document).ready(function() {
            // Listen for button click
            $("#confirmButton").on("click", function() {
                // Perform any confirmation actions here
                alert("Confirmation Successful!");
            });
        });
    </script>
</body>
</html>
