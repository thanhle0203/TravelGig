<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Payment</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <!-- Include jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <!-- Include payment.js -->
    <script src="./js/payment.js"></script>
    <!-- Include navbar.css -->
    <link rel="stylesheet" href="/css/navbar.css">
    <!-- Include footer.css -->
    <link rel="stylesheet" href="/css/footer.css">
    <style>
        .card {
            box-shadow: 0 4px 8px 0 rgba(145, 140, 140, 0.2);
            background-color: rgba(228, 230, 230, 0.908);
        }

        .form-group {
            margin-bottom: 20px;
        }

        .btn-submit {
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <!-- Include navbar.jsp -->
    <%@ include file="navbar.jsp" %>

    <div class="container">
        <h3 class="mt-5 mb-4">Payment</h3>
        <div id="payment-details">
            <p class='card-text'><strong>Insured Name:</strong> <span id="insuredName"></span></p>
            <p class='card-text'><strong>Auto Insurance Plan:</strong> <span id="autoInsurancePlan"></span></p>
            <p class='card-text'><strong>Total Price:</strong> <span id="totalPrice"></span></p>
        </div>
        <br>
        <!-- Payment form -->
        <form id="paymentForm" method="POST" action="/process-payment">
            <div class="form-group">
                <label for="cardNumber">Card Number:</label>
                <input type="text" class="form-control" id="cardNumber" name="cardNumber" required>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="expiryDate">Expiry Date:</label>
                    <input type="text" class="form-control" id="expiryDate" name="expiryDate" required>
                </div>
                <div class="form-group col-md-6">
                    <label for="cvv">CVV:</label>
                    <input type="text" class="form-control" id="cvv" name="cvv" required>
                </div>
            </div>
            <div class="form-group">
                <label for="nameOnCard">Name on Card:</label>
                <input type="text" class="form-control" id="nameOnCard" name="nameOnCard" required>
            </div>
            <button type="submit" class="btn btn-primary">Pay Now</button>
        </form>
        
    </div>

    <!-- Include footer.jsp -->
    <!-- <%@ include file="footer.jsp" %> -->
</body>
</html>
