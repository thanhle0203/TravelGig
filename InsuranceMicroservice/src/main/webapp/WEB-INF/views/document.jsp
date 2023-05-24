<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Submit Document</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <!-- Include jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <!-- Include document.js -->
    <script src="/js/document.js"></script>
    <!-- Include navbar.css -->
    <link rel="stylesheet" href="/css/navbar.css">
    <!-- Include footer.css -->
    <link rel="stylesheet" href="/css/footer.css">
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
                        <label for="driverLicense">Driver's License:</label>
                        <input type="file" class="form-control-file" id="driverLicense" name="driverLicense" required>
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
