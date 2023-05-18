<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Plan Confirmation</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <!-- Include jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="./js/autoConfirmationPlan.js"></script>
</head>
<body>
    <div class="container">
        <h1>Plan Confirmation</h1>
        <div class="row">
            <div class="col-md-6 offset-md-3" style="background-color: #f2f2f2; padding: 20px; border-radius: 10px;">
                <h3>Your Selected Plan Details</h3>
                <% String plan = request.getParameter("plan");
                if (plan != null) { %>
                    <p>Plan: <%= plan %></p>
                    <% if (plan.equals("PlanA")) { %>
                        <p>Collision Deductible: <%= request.getParameter("collisionDeductibleA") %></p>
                        <p>Uninsured Motorist Protection Deductible: <%= request.getParameter("uninsuredMotoristDeductibleA") %></p>
                        <p>Total Price: $1500/year</p>
                    <% } else if (plan.equals("PlanB")) { %>
                        <p>Collision Deductible: <%= request.getParameter("collisionDeductibleB") %></p>
                        <p>Total Price: $1000/year</p>
                    <% } else { %>
                        <p>Total Price: $500/year</p>
                    <% } %>
                    <button class="btn btn-primary">Confirm</button>
                <% } else { %>
                    <p>No plan selected</p>
                <% } %>
            </div>
        </div>
    </div>
</body>
</html>
