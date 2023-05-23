<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insurer Information</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <!-- Include jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="./js/driverInfo.js"></script>
    <!-- Include navbar.css -->
   <link rel="stylesheet" href="/css/navbar.css">
   <!-- Include footer.css -->
   <link rel="stylesheet" href="/css/footer.css">
    <style>
        .navbar {
            background-color: #343a40;
        }

        .navbar-brand {
            color: #fff;
            font-weight: bold;
        }

        .container {
            margin-top: 20px;
        }

        .card {
            background-color: #ffffff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .card-body {
            padding: 20px;
        }

        .footer {
            background-color: #f8f9fa;
            padding: 10px;
            text-align: center;
            width: 100%;
            position: absolute;
            bottom: 0;
            left: 0;
        }
    </style>
</head>
<body>
    <!-- Include navbar.jsp -->
    <jsp:include page="navbar.jsp" />

    <div class="container">
        <div class="card">
            <div class="card-body">
                <h1 class="card-title">Enter Driver Information</h1>
                <form id="driverForm">
                    <div class="form-group">
                        <label for="zipCode">Zip Code:</label>
                        <input type="text" class="form-control" id="zipCode" name="zipCode">
                    </div>
                    <div class="form-group">
                        <label for="license">Driver License Number:</label>
                        <input type="text" class="form-control" id="license" name="license">
                    </div>
                    <div class="form-group">
                        <label for="maritalStatus">Marital Status:</label>
                        <select class="form-control" id="maritalStatus" name="maritalStatus">
                            <option value="single">Single</option>
                            <option value="married">Married</option>
                            <option value="divorced">Divorced</option>
                            <option value="widowed">Widowed</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    </div>

    <!-- Include footer.jsp -->
    <jsp:include page="footer.jsp" />
</body>
</html>

                   
