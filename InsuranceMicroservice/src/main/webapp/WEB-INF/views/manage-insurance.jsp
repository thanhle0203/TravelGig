<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Insurance</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <!-- Include jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <!-- Include manage-insurance.js -->
    <script src="./js/manage-insurance.js"></script>
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
    <%@ include file="navbar.jsp" %>

    <div class="container">
        <h1 class="mt-5 mb-4">Manage Insurance</h1>
        <div class="card">
            <div class="card-body">
                <h2 class="card-title">Insured Information</h2>
                <div id="insuredInfo"></div>

                <h2 class="card-title">Vehicle Information</h2>
                <table class="table table-bordered">
                    <tbody id="vehicleInfo"></tbody>
                </table>

                <h2 class="card-title">Auto Insurance Plan</h2>
                <div id="autoInsuranceInfo"></div>

                <br>

                <h2 class="card-title">Insured Status</h2>
                <div id="insuredStatus"></div>

                <br>

                <div id="payButtonContainer"></div> 

                <br>

                <!-- Add the "Generate PDF" button -->
                <div id="generatePdfButtonContainer"></div>
                
            </div>
        </div>
    </div>

    <!-- Include footer.jsp -->
    <!-- <%@ include file="footer.jsp" %> -->
</body>
</html>
