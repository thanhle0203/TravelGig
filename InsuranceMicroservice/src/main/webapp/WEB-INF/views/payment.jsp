<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Payment Processing</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <!-- Include jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <!-- Include Stripe.js -->
    <script src="https://js.stripe.com/v3/"></script>
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
        <h1 class="mt-5 mb-4">Payment Processing</h1>
        <div class="card">
            <div class="card-body">
                <h2 class="card-title">Enter Payment Details</h2>
                <div class="form-group">
                    <label for="insuredName">Insured Name:</label>
                    <input type="text" class="form-control" id="insuredName" disabled>
                </div>
                <div class="form-group">
                    <label for="autoPlan">Auto Plan:</label>
                    <input type="text" class="form-control" id="autoPlan" disabled>
                </div>
                <div class="form-group">
                    <label for="totalPrice">Total Price:</label>
                    <input type="text" class="form-control" id="totalPrice" disabled>
                </div>
                <form id="paymentForm">
                    <div class="form-group">
                        <label for="cardNumber">Card Number:</label>
                        <input type="text" class="form-control" id="cardNumber" required>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="expirationDate">Expiration Date:</label>
                            <input type="text" class="form-control" id="expirationDate" placeholder="MM/YY" required>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="cvc">CVC:</label>
                            <input type="text" class="form-control" id="cvc" required>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary">Pay</button>
                </form>
            </div>
        </div>
    </div>

    <!-- Include footer.jsp -->
    <!-- <%@ include file="footer.jsp" %> -->

    <script>
        // Set the insured name, auto plan, and total price values
        $(document).ready(function() {
            var insuredData = JSON.parse(localStorage.getItem("insuredData"));
            var insuredName = insuredData.name;
            var autoPlanName = insuredData.autoInsurance.autoPlan.name;
            var totalPrice = insuredData.autoInsurance.totalPrice;

            $("#insuredName").val(insuredName);
            $("#autoPlan").val(autoPlanName);
            $("#totalPrice").val(totalPrice);
        });
    </script>
</body>
</html>
