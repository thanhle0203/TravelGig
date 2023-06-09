<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Confirmation Plan</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <!-- Include jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <!-- Include autoConfirmationPlan.js -->
    <script src="./js/autoConfirmationPlan.js"></script>
    <!-- Include navbar.css -->
    <link rel="stylesheet" href="/css/navbar.css">
    <!-- Include footer.css -->
    <link rel="stylesheet" href="/css/footer.css">
    <style>
        .card {
            box-shadow: 0 4px 8px 0 rgba(145, 140, 140, 0.2);
            background-color: rgba(228, 230, 230, 0.908);
        }
    </style>
</head>
<body>
    <!-- Include navbar.jsp -->
    <jsp:include page="navbar.jsp" />

    <div class="container">
        <h1 class="mt-5 mb-4">Confirmation Plan</h1>
        <div class="card">
            <div class="card-body">
                <h2 class="card-title">Confirmation Details</h2>
                <div id="confirmation-details">
                    <p class='card-text'><strong>Plan Name:</strong> <span id="planName"></span></p>
                    <p class='card-text'><strong>Collision Deductible:</strong> <span id="collisionDeductible"></span></p>
                    <p class='card-text'><strong>Uninsured Motorist Deductible:</strong> <span id="uninsuredMotoristDeductible"></span></p>
                    <p class='card-text'><strong>Total Price:</strong> <span id="totalPrice"></span></p>
                </div>
                <!-- Add hidden form fields to store the confirmation details -->
                <form id="confirmationForm" method="post">
                    <input type="hidden" name="planName" id="hiddenPlanName">
                    <input type="hidden" name="collisionDeductible" id="hiddenCollisionDeductible">
                    <input type="hidden" name="uninsuredMotoristDeductible" id="hiddenUninsuredMotoristDeductible">
                    <input type="hidden" name="totalPrice" id="hiddenTotalPrice">
                    <button type="submit" class="btn btn-primary">Continue</button>
                </form>
            </div>
        </div>
    </div>

    <!-- Include footer.jsp -->
    <!-- <jsp:include page="footer.jsp" /> -->

    
</body>
</html>
