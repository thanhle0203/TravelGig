<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Blog Insurance</title>
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
    </style>
</head>
<body>
    <!-- Include navbar.jsp -->
    <%@ include file="navbar.jsp" %>

    <div class="container">
        <h1 class="mt-5 mb-4">Blog Insurance</h1>
        <div class="card">
            <div class="card-body">
                <h2 class="card-title">News</h2>
                <div id="blogNews">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Title of Blog Post 1</h5>
                            <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla id lectus vel mi gravida ullamcorper. In hac habitasse platea dictumst. Phasellus cursus urna ac nunc accumsan ullamcorper. Quisque efficitur mi sit amet sem porta, non accumsan dui porttitor. Morbi interdum metus eget leo efficitur consequat. Donec pharetra, magna nec dapibus tincidunt, nisi lorem iaculis urna, sit amet dignissim risus elit in nisi.</p>
                            <a href="#" class="card-link">Read More</a>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Title of Blog Post 2</h5>
                            <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla id lectus vel mi gravida ullamcorper. In hac habitasse platea dictumst. Phasellus cursus urna ac nunc accumsan ullamcorper. Quisque efficitur mi sit amet sem porta, non accumsan dui porttitor. Morbi interdum metus eget leo efficitur consequat. Donec pharetra, magna nec dapibus tincidunt, nisi lorem iaculis urna, sit amet dignissim risus elit in nisi.</p>
                            <a href="#" class="card-link">Read More</a>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Title of Blog Post 3</h5>
                            <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla id lectus vel mi gravida ullamcorper. In hac habitasse platea dictumst. Phasellus cursus urna ac nunc accumsan ullamcorper. Quisque efficitur mi sit amet sem porta, non accumsan dui porttitor. Morbi interdum metus eget leo efficitur consequat. Donec pharetra, magna nec dapibus tincidunt, nisi lorem iaculis urna, sit amet dignissim risus elit in nisi.</p>
                            <a href="#" class="card-link">Read More</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    
</body>

</html>
<!-- Include footer.jsp -->
<!-- <%@ include file="footer.jsp" %> -->
