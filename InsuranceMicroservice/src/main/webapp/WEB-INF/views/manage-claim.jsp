<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Claims</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <!-- Include jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <!-- Include manage-claim.js -->
    <script src="./js/manage-claim.js"></script>
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
        <h1>Manage Claims</h1>
        <table id="claimTable" class="table table-striped">
            <thead>
                <tr>
                    <th>Accident Date</th>
                    <th>Vehicle Make</th>
                    <th>Vehicle Model</th>
                    <th>Vehicle Year</th>
                    <th>Description</th>
                    <th>Repair Price</th>
                    <th>Status</th>
                    <th>Images</th>
                </tr>
            </thead>
            <tbody>
                <!-- Table rows will be dynamically added by JavaScript -->
            </tbody>
        </table>
    </div>

    <!-- Include footer.jsp -->
    <!-- <%@ include file="footer.jsp" %> -->
</body>
</html>
