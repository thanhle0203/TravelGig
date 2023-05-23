<!DOCTYPE html>
<html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<head>
    <meta charset="ISO-8859-1">
    <title>Home</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <!-- Include jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="./js/insurance.js"></script>
    <!-- Include navbar.css -->
    <link rel="stylesheet" href="/css/navbar.css">
    <!-- Include footer.css -->
    <link rel="stylesheet" href="/css/footer.css">
    <style>
        .card-grid {
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            gap: 20px;
            margin-bottom: 20px;
        }

        .shadow-card {
            position: relative;
            background-color: #e2e8e8;
            box-shadow: 0 4px 8px rgba(30, 31, 31, 0.192);
            border-radius: 10px;
            padding: 20px;
        }

        .card {
            position: relative;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            padding: 20px;
        }

        .card-body {
            text-align: center;
        }

        .card-title {
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <!-- Include navbar.jsp -->
    <jsp:include page="navbar.jsp" />

    <div class="container">
        <h1>Welcome to our Online Affordable Insurance</h1>
        <p>Here you can find different types of insurance to suit your needs with cheaper prices compared to other auto companies. Whether you're looking for auto, home, business, or life insurance, we have it all. Explore our offerings and choose the best plan for you.</p>
        <br><br>
        <h1>Get your free quote today!</h1>
        <h2>Select Type of Insurance</h2>
        <div class="card-grid">
            <div class="shadow-card"></div>
            <div class="shadow-card"></div>
            <div class="card">
                <div class="card-body">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-car-front" viewBox="0 0 16 16">
                        <!-- SVG path -->
                    </svg>
                    <h5 class="card-title">Auto Insurance</h5>
                    <button class="btn btn-primary insurance-btn" data-insurance="auto">Select</button>
                </div>
            </div>
            <div class="card">
                <div class="card-body">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-house" viewBox="0 0 16 16">
                        <!-- SVG path -->
                    </svg>
                    <h5 class="card-title">Home Insurance</h5>
                    <button class="btn btn-primary insurance-btn" data-insurance="home">Select</button>
                </div>
            </div>
            <div class="card">
                <div class="card-body">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-buildings" viewBox="0 0 16 16">
                        <!-- SVG path -->
                    </svg>
                    <h5 class="card-title">Business Insurance</h5>
                    <button class="btn btn-primary insurance-btn" data-insurance="business">Select</button>
                </div>
            </div>
            <div class="card">
                <div class="card-body">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person" viewBox="0 0 16 16">
                        <!-- SVG path -->
                    </svg>
                    <h5 class="card-title">Life Insurance</h5>
                    <button class="btn btn-primary insurance-btn" data-insurance="life">Select</button>
                </div>
            </div>
        </div>
    </div>


<!-- Include footer.jsp -->
<jsp:include page="footer.jsp" />

</body>
</html>

