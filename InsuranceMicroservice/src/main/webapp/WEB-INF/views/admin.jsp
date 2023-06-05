<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
  <title>Admin Dashboard</title>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
  <script src="./js/admin.js"></script>
</head>
<body>
  <!-- Include navbar.jsp -->
  <%@ include file="navbar.jsp" %>
  <div class="container">
    <h1 class="mt-5">Admin Dashboard</h1>
  
    <!-- Nav tabs -->
    <ul class="nav nav-tabs mt-4">
      <li class="nav-item">
        <a class="nav-link active" data-toggle="tab" href="#manageClaim">Manage Claim</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" data-toggle="tab" href="#manageInsured">Manage Insured</a>
      </li>
    </ul>

    <!-- Tab panes -->
    <div class="tab-content">
      <!-- Manage Claim tab -->
      <div id="manageClaim" class="tab-pane fade show active">
        <table class="table mt-4">
          <thead>
            <tr>
              <th>Claim ID</th>
              <th>Accident Date</th>
              <th>Description</th>
              <th>Vehicle Damage Image</th>
              <th>Repair Price</th>
              <th>Status</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody id="claimsTableBody">
            <!-- Claims will be dynamically added here -->
          </tbody>
        </table>

        <div id="repairPriceDialog" class="modal fade" tabindex="-1" role="dialog">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title">Enter Repair Price</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                <form id="repairPriceForm">
                  <div class="form-group">
                    <label for="claimId">Claim ID:</label>
                    <input type="number" class="form-control" id="claimId" name="claimId" required readonly>
                  </div>
                  <div class="form-group">
                    <label for="price">Repair Price:</label>
                    <input type="number" class="form-control" id="price" name="price" required>
                  </div>
                  <button type="submit" class="btn btn-primary">Submit</button>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>

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

      <!-- Manage Insured tab -->
      <div id="manageInsured" class="tab-pane fade">
        <table class="table mt-4">
          <thead>
            <tr>
              <th>Insured ID</th>
              <th>Name</th>
              <th>Email</th>
              <th>Phone</th>
              <th>Driver License Image</th>
              <th>Vehicle Make</th>
              <th>Vehicle Model</th>
              <th>Vehicle Year</th>
              <th>Auto Plan Name</th>
              <th>Total Price</th>
              <th>Status</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody id="insuredTableBody">
            <!-- Insured data will be dynamically added here -->
          </tbody>
        </table>
      </div>

      <!-- Driver License Image Modal -->
      <div class="modal fade" id="driverLicenseModal" tabindex="-1" role="dialog" aria-labelledby="driverLicenseModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="driverLicenseModalLabel">Driver License Image</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
              <img id="driverLicenseImage" src="" alt="Driver License" class="img-fluid">
            </div>
          </div>
        </div>
      </div>

    </div>
  </div>
  
</body>
</html>
