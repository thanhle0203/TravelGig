<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Claims</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
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
                    <th>Phone Number</th>
                    <th>Images</th>
                    <th>Claim PDf File</th>
                </tr>
            </thead>
            <tbody>
                <!-- Table rows will be dynamically added by JavaScript -->
            </tbody>
        </table>

        <!-- Vehicle Damage Image Modal -->
        <div class="modal fade" id="vehicleDamageModal" tabindex="-1" role="dialog" aria-labelledby="vehicleDamageModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="vehicleDamageModalLabel">Vehicle Damage Image</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <img id="vehicleDamageImage" src="" alt="Vehicle Damage" class="img-fluid">
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Include footer.jsp -->
    <!-- <%@ include file="footer.jsp" %> -->

   
</body>
</html>
