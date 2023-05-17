<!DOCTYPE html>
<html>
<%@ page language="java" contentType="text/html; charset-ISO-8859-1" 
    pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false"%>
<head>
    <title>Home</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <!-- Include jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
    <div class="container">
        <h1>Welcome to Our Auto Insurance Portal</h1>
        <p>Please select "Auto Insurance" to continue</p>
        <button class="btn btn-primary" id="autoInsuranceBtn">Auto Insurance</button>
    </div>
    <script>
        // Handle button click
        $('#autoInsuranceBtn').click(function() {
            // Redirect user to the auto insurance page
            window.location.href = "/auto-insurance";
        });
    </script>
</body>
</html>
