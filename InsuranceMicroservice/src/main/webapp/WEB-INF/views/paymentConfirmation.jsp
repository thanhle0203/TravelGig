<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Payment Confirmation</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <!-- Include jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <!-- Include paymentConfirmation.js -->
    <script src="./js/paymentConfirmation.js"></script>
    <!-- Include navbar.css -->
    <link rel="stylesheet" href="/css/navbar.css">
    <!-- Include footer.css -->
    <link rel="stylesheet" href="/css/footer.css">
    <style>
        .card {
            box-shadow: 0 4px 8px 0 rgba(145, 140, 140, 0.2);
            background-color: rgba(228, 230, 230, 0.908);
        }

        .confirmation-info {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <!-- Include navbar.jsp -->
    <%@ include file="navbar.jsp" %>

    <div class="container">
        <h1 class="mt-5 mb-4">Payment Confirmation</h1>
        <div id="confirmation-details">
            <p class='card-text confirmation-info'><strong>Insured Name:</strong> <span id="insuredName"></span></p>
            <p class='card-text confirmation-info'><strong>Auto Insurance Plan:</strong> <span id="autoInsurancePlan"></span></p>
            <p class='card-text confirmation-info'><strong>Total Price:</strong> <span id="totalPrice"></span></p>

            <p class='card-text confirmation-info'><strong>Payment ID:</strong> <span id="paymentId"></span></p>
            <p class='card-text confirmation-info'><strong>Amount:</strong> <span id="amount"></span></p>
            <p class='card-text confirmation-info'><strong>Currency:</strong> <span id="currency"></span></p>
            <p class='card-text confirmation-info'><strong>Payment Status:</strong> <span id="paymentStatus"></span></p>
        </div>
    </div>

    <!-- Include footer.jsp -->
    <!-- <%@ include file="footer.jsp" %> -->
</body>
</html>
