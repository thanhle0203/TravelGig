
<!doctype html>
<html lang="en">
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page isELIgnored="false" %> 
 
 <head>
    <meta charset="utf-8" />
    <title>Booking Tabs</title>
    
    <!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.1/css/bootstrap.min.css" integrity="sha512-6KY5s6UI5J7SVYuZB4S/CZMyPylqyyNZco376NM2Z8Sb8OxEdp02e1jkKk/wZxIEmjQ6DRCEBhni+gpr9c4tvA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.0/themes/smoothness/jquery-ui.css" integrity="sha384-0v0PR7InjhOjDDbGO8C514wieCsOuPTRo6wIO4OY7kPzW6gsop0VtjfcXfZNieYW" crossorigin="anonymous" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha384-vtXRMe3mGCbOeY7l30aIg8H9p3GdeSe4IFlP6G8JMa7o7lXvnz3GFKzPxzJdPfGK" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/ui/1.13.0/jquery-ui.min.js" integrity="sha384-GH7wmqAxDa43XGS89eXGbziWEki6l/Smy1U+dAI7ZbxlrLsmal+hLlTMqoPIIg1V" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.1/js/bootstrap.min.js" integrity="sha512-+RJc/N1+ZCQ2Z63otyaWRYQ9noAWV7sC+OarLz8wJfTjTtn7/jfw1vmN+4U4pX9fEgUOFOLyVDKj1U6S2Jo6zQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

    <script src="./js/bookings.js" defer></script> -->

    <!-- Bootstrap CSS -->
    <script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.13.1/themes/smoothness/jquery-ui.css">

<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.min.js"></script>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="./js/bookings.js" defer></script>


    <script>
        $(function() {
            $("#tabs").tabs();
        });
    </script>
</head>

<body>
    <div id="tabs">
        <ul>
            <li><a href="#upcomingBookings">Upcoming Bookings</a></li>
            <li><a href="#completedBookings">Completed Booking</a></li>
            <li><a href="#cancelledBookings">Cancelled Bookings</a></li>
        </ul>
        <div id="upcomingBookings">
            <h3>List of upcoming bookings.</h3>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Booking ID</th>
                        <th>Check-in Date</th>
                        <th>Check-out Date</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- The upcoming bookings will be displayed here -->
                </tbody>
            </table>
        </div>
        <div id="completedBookings">
            <h3>List of completed bookings.</h3>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Booking ID</th>
                        <th>Check-in Date</th>
                        <th>Check-out Date</th>
                        <th>Status</th>
                        <th>Review</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- The completed bookings will be displayed here -->
                </tbody>
            </table>
            <!-- <button id="reviewBtn" class="btn btn-primary">Leave a review</button> -->
        </div>
        
        <div id="cancelledBookings">
            <h3>List of cancelled bookings.</h3>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Booking ID</th>
                        <th>Check-in Date</th>
                        <th>Check-out Date</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- The cancelled bookings will be displayed here -->
                </tbody>
            </table>
        </div>
    </div>

    <div class="modal" id="reviewModal">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Leave a Review</h5>
              <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
              <form id="reviewForm">
                <div class="form-group">
                  <label for="rating">Rating:</label>
                  <select class="form-control" id="rating" name="rating">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                  </select>
                </div>
                <div class="form-group">
                  <label for="reviewText">Review:</label>
                  <textarea class="form-control" id="reviewText" name="reviewText" rows="3"></textarea>
                </div>
                <button type="submit" class="btn btn-primary" id="btnModalReview">Submit</button>
              </form>
            </div>
          </div>
        </div>
      </div>
      
      
</body>

</html>
