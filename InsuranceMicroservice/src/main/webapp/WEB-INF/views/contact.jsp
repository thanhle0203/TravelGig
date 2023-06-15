<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Contact AFI</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <!-- Include jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
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
    <%@ include file="navbar.jsp" %>

    <div class="container">
        <h1 class="mt-5 mb-4">Contact Us</h1>
        <div class="card">
            
            <div class="card-body">
                <h3 class="card-title">Contact Numbers</h3>
                <p>Customer Service: 1-777-888-9999</p>
            </div>
        </div>
        <br>
        <div class="card">
            <div class="card-body">
                
                <h3 class="card-title">Send us a message</h3>
                <form id="contactForm" action="/api/contact" method="POST">

                    <div class="form-group">
                        <label for="name">Name:</label>
                        <input type="text" class="form-control" id="name" name="name" required>
                    </div>
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="email" class="form-control" id="email" name="email" required>
                    </div>
                    <div class="form-group">
                        <label for="message">Message:</label>
                        <textarea class="form-control" id="message" name="message" rows="5" required></textarea>
                    </div>

                    <button type="submit" class="btn btn-primary btn-submit">Submit</button>
                </form>
            </div>
        </div>
    </div>

    <!-- Include footer.jsp -->
    <!-- <%@ include file="footer.jsp" %> -->
</body>
</html>
