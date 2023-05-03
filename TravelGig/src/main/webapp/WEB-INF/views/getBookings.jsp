
<!doctype html>
<html lang="en">
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page isELIgnored="false" %> 
 
<head>
<meta charset="utf-8" />
<title>Booking Tabs</title>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script src="./js/booking.js"></script>

<script>
$(function() {
$( "#tabs" ).tabs();
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
<p>List of upcoming bookings.</p>
</div>
<div id="completedBookings">
<p>List of completed bookings.</p>
</div>
<div id="cancelledBookings">
<p>List of cancelled bookings.</p>
</div>
</div>
</body>
</html>
