<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Auto Insurance</title>

    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <!-- Include jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="./js/vehicleInfo.js"></script>
</head>
<body>
    <div class="container">
        <h1>Enter Vehicle Information</h1>
        <form id="vehicleForm">
            <div class="form-group">
                <label for="vin">Vehicle Identification Number (VIN):</label>
                <input type="text" class="form-control" id="vin" name="vin">
            </div>
            <div class="form-group">
                <label for="year">Year:</label>
                <input type="number" class="form-control" id="year" name="year">
            </div>
            <div class="form-group">
                <label for="make">Make:</label>
                <input type="text" class="form-control" id="make" name="make">
            </div>
            <div class="form-group">
                <label for="model">Model:</label>
                <input type="text" class="form-control" id="model" name="model">
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</body>
</html>
