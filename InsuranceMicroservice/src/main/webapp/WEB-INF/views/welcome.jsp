<!DOCTYPE html>
<html>
<%@ page language="java" contentType="text/html; charset-ISO-8859-1" 
    pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false"%>
<head>
    <meta charset="ISO-8859-1">
    <title>Home</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <!-- Include jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="./js/insurance.js"></script>

</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">Insurance Portal</a>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#">My Insurance</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Cart</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">My Account</a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="container">
        <h1>Welcome to Our Online Insurance Portal</h1>
        <p>Welcome to our online insurance portal. Here you can find different types of insurance to suit your needs. Whether you're looking for auto, home, business, or life insurance, we have it all. Explore our offerings and choose the best plan for you.</p>
        <br><br><br>
        <h1>Get your free quote today!</h1>
        <h2>Select Type of Insurance</h2>
        <div id="insuranceTypes">
            <button class="btn btn-primary insurance-btn" data-insurance="auto">Auto Insurance</button>
            <button class="btn btn-primary insurance-btn" data-insurance="home">Home Insurance</button>
            <button class="btn btn-primary insurance-btn" data-insurance="business">Business Insurance</button>
            <button class="btn btn-primary insurance-btn" data-insurance="life">Life Insurance</button>
        </div>
    </div>
</body>
</html>
