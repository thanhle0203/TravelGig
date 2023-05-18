<!DOCTYPE html>
<html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
    pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false"%>
<head>
    <meta charset="ISO-8859-1">
    <title>Auto Insurance</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <!-- Include jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="./js/auto.js"></script>
</head>
<body>
    <div class="container">
        <h1>Enter Personal Details</h1>
        <form id="detailsForm">
            <!-- Form fields for name, email, date of birth, phone number and address -->
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" class="form-control" id="name" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" class="form-control" id="email" required>
            </div>
            <div class="form-group">
                <label for="dob">Date of Birth:</label>
                <input type="date" class="form-control" id="dob" required>
            </div>
            <div class="form-group">
                <label for="phone">Phone Number:</label>
                <input type="tel" class="form-control" id="phone" required>
            </div>
            <!-- Address fields -->
            <div class="form-group">
                <label for="street">Street:</label>
                <input type="text" class="form-control" id="street" required>
            </div>
            <div class="form-group">
                <label for="city">City:</label>
                <input type="text" class="form-control" id="city" required>
            </div>
            <div class="form-group">
                <label for="state">State:</label>
                <input type="text" class="form-control" id="state" required>
            </div>
            <div class="form-group">
                <label for="zip">Zip Code:</label>
                <input type="text" class="form-control" id="zip" required>
            </div>
            <button type="submit" class="btn btn-primary" id="next-btn">Next</button>
        </form>
    </div>
    <script>
        // JavaScript/jQuery code for handling form submission goes here
    </script>
</body>
</html>
