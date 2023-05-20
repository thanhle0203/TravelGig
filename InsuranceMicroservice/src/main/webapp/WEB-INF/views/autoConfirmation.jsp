<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
    pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Auto Insurance Confirmation</title>
    

    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <!-- Include jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="./js/autoInsurancePlans.js"></script>
</head>
<body>
    <h1>Auto Insurance Confirmation</h1>

    <h4>${planTitle}</h4>
    <p>${planDescription}</p>
    <h5>Base Price: $${basePrice}/year</h5>
    <h5>Collision Deductible: $${collisionDeductible}</h5>
    <h5>Uninsured Motorist Deductible: $${uninsuredMotoristDeductible}</h5>
    <h5>Total Price: $${totalPrice}/year</h5>
</body>
</html>
