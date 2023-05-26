<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Auto Insurance Confirmation</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

    <!-- Include navbar.css -->
    <link rel="stylesheet" href="/css/navbar.css">
    <!-- Include footer.css -->
    <link rel="stylesheet" href="/css/footer.css">
    <script src="./js/autoConfirmationPlan.js"></script>
    <style>
        .card {
            box-shadow: 0 4px 8px 0 rgba(145, 140, 140, 0.2);
            background-color: rgba(228, 230, 230, 0.908);
        }
    </style>

</head>
<body>
    <!-- Include navbar.jsp -->
    <jsp:include page="navbar.jsp" />

    <div class="container">
        <h1 class="mt-5 mb-4">Auto Insurance Confirmation</h1>
        <div class="card">
            <div class="card-body">
                <h2 class="card-title">Auto Plan Details</h2>
                <%-- Retrieve the selected plan data from local storage --%>
                <script>
                    var selectedPlan = JSON.parse(localStorage.getItem("selectedPlan"));
                    if (selectedPlan) {
                        var planName = selectedPlan.autoPlan.name;
                        var collisionDeductible = selectedPlan.collisionDeductible;
                        var uninsuredMotoristDeductible = selectedPlan.uninsuredMotoristDeductible;
                        var totalPrice = selectedPlan.totalPrice;

                        // Display the plan details
                        document.write("<p class='card-text'><strong>Plan Name:</strong> " + planName + "</p>");
                        document.write("<p class='card-text'><strong>Collision Deductible:</strong> $" + collisionDeductible + "</p>");
                        document.write("<p class='card-text'><strong>Uninsured Motorist Deductible:</strong> $" + uninsuredMotoristDeductible + "</p>");
                        document.write("<p class='card-text'><strong>Total Price:</strong> $" + (totalPrice ? totalPrice.toFixed(2) : "") + "</p>");

                        // Store the updated selectedPlan in local storage
                        localStorage.setItem("selectedPlan", JSON.stringify(selectedPlan));
                    }
                </script>

                <button type="button" class="btn btn-primary" id="confirmButton">Confirm</button>
            </div>
        </div>
    </div>

    <!-- Include footer.jsp -->
    <jsp:include page="footer.jsp" />
</body>
</html>
