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
    <!-- Include navbar.css -->
   <link rel="stylesheet" href="/css/navbar.css">
   <!-- Include footer.css -->
   <link rel="stylesheet" href="/css/footer.css">
    <style>    
        .container {
            margin-top: 20px;
        }

        .card {
            background-color: #f8f9fa;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .card-body {
            padding: 20px;
        }
        
    </style>
</head>
<body>
    <!-- Include navbar.jsp -->
    <jsp:include page="navbar.jsp" />

    <div class="container">
        <div class="card">
            <div class="card-body">
                <h1 class="card-title">Enter Vehicle Information</h1>
                <form id="vehicleForm">
                    <div class="form-group">
                        <label for="vin">Vehicle Identification Number (VIN):</label>
                        <input type="text" class="form-control" id="vin" name="vin">
                    </div>
                    
                    <div class="form-group">
                        <label for="make">Make:</label>
                        <input type="text" class="form-control" id="make" name="make">
                    </div>
                    <div class="form-group">
                        <label for="model">Model:</label>
                        <input type="text" class="form-control" id="model" name="model">
                    </div>
                    <div class="form-group">
                        <label for="year">Year:</label>
                        <input type="number" class="form-control" id="year" name="year">
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    </div>

    <!-- Include footer.jsp -->
    <!-- <jsp:include page="footer.jsp" /> -->
</body>
</html>
