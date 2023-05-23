<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Submit Document</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <!-- Include jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <!-- Include submitDocument.js -->
    <!-- <script src="${pageContext.request.contextPath}/js/submitDocument.js"></script> -->
    <script src="./js/submitDocument.js"></script>
    <!-- Include navbar.css -->
    <link rel="stylesheet" href="/css/navbar.css">
    <!-- Include footer.css -->
    <link rel="stylesheet" href="/css/footer.css">
    <style>
        .card {
            box-shadow: 0 4px 8px 0 rgba(145, 140, 140, 0.2);
            background-color: rgba(228, 230, 230, 0.908);
        }

        .form-group {
            margin-bottom: 20px;
        }

        .btn-submit {
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <!-- Include navbar.jsp -->
    <jsp:include page="navbar.jsp" />

    <div class="container">
        <h1 class="mt-5 mb-4">Submit Document</h1>
        <div class="card">
            <div class="card-body">
                <h2 class="card-title">Enter Information</h2>
                <form id="documentForm" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="name">Name:</label>
                        <input type="text" class="form-control" id="name" name="name" required>
                    </div>
                    <div class="form-group">
                        <label for="street">Street:</label>
                        <input type="text" class="form-control" id="street" name="street" required>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="city">City:</label>
                            <input type="text" class="form-control" id="city" name="city" required>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="state">State:</label>
                            <select class="form-control" id="state" name="state" required>
                                <option value="" selected disabled>Select a state</option>
                                <option value="AL">Alabama</option>
                                <option value="AK">Alaska</option>
                                <option value="AZ">Arizona</option>
                                <option value="CA">California</option>
                                <option value="CO">Colodo</option>
                                <option value="FL">Florida</option>
                                <option value="IL">Ilinos</option>
                                <option value="NY">New York</option>                          
                                <option value="TX">Texas</option>
                                <!-- Include more states here -->
                            </select>
                        </div>
                        <div class="form-group col-md-2">
                            <label for="zipCode">Zip Code:</label>
                            <input type="text" class="form-control" id="zipCode" name="zipCode" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="phone">Phone:</label>
                        <input type="text" class="form-control" id="phone" name="phone" required>
                    </div>
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="email" class="form-control" id="email" name="email" required>
                    </div>
                    <div class="form-group">
                        <label for="dob">Date of Birth:</label>
                        <input type="date" class="form-control" id="dob" name="dob" required>
                    </div>
            
                    <div class="form-group">
                        <label for="driverLicense">Driver's License:</label>
                        <input type="file" class="form-control-file" id="driverLicense" name="driverLicense" required>

                    </div>

                    <!-- <div class="form-group">
                        <label for="driverLicense">Driver's License:</label>
                        <input type="file" class="form-control-file" id="driverLicense" name="driverLicense" required>

                    </div> -->
                    <button type="submit" class="btn btn-primary btn-submit">Submit</button>
                </form>
            </div>
        </div>
    </div>

    <!-- Include footer.jsp -->
    <jsp:include page="footer.jsp" />
</body>
</html>
