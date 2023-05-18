<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Auto Insurance Confirmation</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1>Auto Insurance Confirmation</h1>
        <h3>Selected Plan Details</h3>
        <table class="table">
            <tr>
                <th>Plan Name</th>
                <th>Base Price</th>
                <th>Collision Deductible</th>
                <th>Uninsured Motorist Deductible</th>
                <th>Total Price</th>
            </tr>
            <tr>
                <td><%= selectedPlan.getName() %></td>
                <td>$<%= selectedPlan.getBasePrice() %>/year</td>
                <td>$<%= selectedPlan.getCollisionDeductible() %></td>
                <td>$<%= selectedPlan.getUninsuredMotoristDeductible() %></td>
                <td>$<%= selectedPlan.getTotalPrice() %>/year</td>
            </tr>
        </table>
        <h3>Total Price: $<%= selectedPlan.getTotalPrice() %>/year</h3>
    </div>
</body>
</html>
