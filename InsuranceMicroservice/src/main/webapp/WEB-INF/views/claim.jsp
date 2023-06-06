<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Claim</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <!-- Include jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <!-- Include claim.js -->
    <script src="./js/claim.js"></script>
    <!-- Include navbar.css -->
    <link rel="stylesheet" href="/css/navbar.css">
    <!-- Include footer.css -->
    <link rel="stylesheet" href="/css/footer.css">
</head>
<body>
    <!-- Include navbar.jsp -->
    <%@ include file="navbar.jsp" %>

    <div class="container">
        <br>
        <h1>Claim Form</h1>
        <form id="claimForm" enctype="multipart/form-data">
            
            <div class="form-group">
                <label for="accidentDate">Accident Date:</label>
                <input type="date" class="form-control" id="accidentDate" name="accidentDate" required>
            </div>
            <div class="form-group">
                <label for="vehicleMake">Vehicle Make:</label>
                <select class="form-control" id="vehicleMake" name="vehicleMake" required>
                    <option value="">Select a vehicle make</option>
                </select>
            </div>
            <div class="form-group">
                <label for="vehicleModel">Vehicle Model:</label>
                <select class="form-control" id="vehicleModel" name="vehicleModel" required>
                    <option value="">Select a vehicle model</option>
                </select>
            </div>
            <div class="form-group">
                <label for="vehicleYear">Vehicle Year:</label>
                <select class="form-control" id="vehicleYear" name="vehicleYear" required>
                    <option value="">Select a vehicle year</option>
                </select>
            </div>
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea class="form-control" id="description" name="description" rows="5" required></textarea>
            </div>
            <div class="form-group">
                <label for="phone">Phone:</label>
                <input type="text" class="form-control" id="phone" name="phone" required>
            </div>
            
            <div class="form-group">
                <label for="image">Upload Images:</label>
                <input type="file" class="form-control-file" id="image" name="image" accept="image/*" multiple required>
            </div>
            
            <button type="submit" class="btn btn-primary">Submit Claim</button>

        </form>
    </div>

    <!-- Include footer.jsp -->
    <!-- <%@ include file="footer.jsp" %> -->
</body>
</html>
