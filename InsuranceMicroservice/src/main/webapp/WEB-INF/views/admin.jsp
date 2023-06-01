<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
  <title>Admin Dashboard</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src="./js/admin.js"></script>
</head>
<body>
  <div class="container">
    <h1 class="mt-5">Admin Dashboard</h1>
  
    <table class="table mt-4">
      <thead>
        <tr>
          <th>Claim ID</th>
          <th>Description</th>
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
  
</body>
</html>
