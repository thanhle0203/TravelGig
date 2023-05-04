
<!doctype html>
<html lang="en">
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page isELIgnored="false" %> 
 
 <head>
    <meta charset="utf-8" />
    <title>Booking Tabs</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    <script src="./js/booking.js"></script>

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
                    </tr>
                </thead>
                <tbody>
                    <!-- The completed bookings will be displayed here -->
                </tbody>
            </table>
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
</body>

</html>
