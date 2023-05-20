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

    <!-- jQuery script to fetch data and populate the plan details -->
    <script src="./js/autoInsurancePlan.js"></script>


</head>
<body>
    <div class="container">
        <h1>Select Auto Insurance Plan</h1>
        <form id="planForm" action="/autoConfirmationPlan" method="post">
            <div class="row">
                <!-- The plan details will be loaded dynamically here -->
            </div>
        </form>
    </div>
</body>
</html>
