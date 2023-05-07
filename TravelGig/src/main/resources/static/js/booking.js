 // function to cancel a booking
    function cancelBooking(bookingId) {    
        // make AJAX call to change booking status to "cancelled"
        $.ajax({
            type: "POST",
            url: "/cancelBooking/" + bookingId,
            data: { bookingId: bookingId },
            success: function() {
                // reload the page to display updated bookings
                location.reload();
            }
        });
    }

$(document).ready(function() {
   
  	// Handle the review button click event
  $('#reviewBtn').click(function() {
    // Show the review modal
    $('#reviewModal').modal('show');
  });

  // Handle the review form submission
  $('#reviewForm').submit(function(event) {
    event.preventDefault();

    // Get the review data
    var rating = $('#rating').val();
    var reviewText = $('#reviewText').val();

    // TODO: Save the review to the database

    // Hide the review modal
    $('#reviewModal').modal('hide');

    // Show a confirmation message
    alert('Thank you for your review!');
  });
  
    $(function () {
        // Fetch the bookings of the logged in user
        // Ajax call to fetch the bookings
        $.ajax({
            url: "/myBookings",
            //url: "/mybookings",
            method: "GET",
            success: function(bookings) {
                console.log(bookings);
                // Group the bookings by status
                var upcomingBookings = [];
                var completedBookings = [];
                var cancelledBookings = [];
                var currentDate = new Date();

                // Convert bookings to an array if it's not an array
                if (!Array.isArray(bookings)) {
                    bookings = [bookings];
                }

                $.each(bookings, function(index, booking) {
                    if (booking.status === "upcoming" && new Date(booking.checkInDate) > currentDate) {
                        upcomingBookings.push(booking);
                    } else if (booking.status === "completed" || (booking.status === "upcoming" && new Date(booking.checkInDate) <= currentDate)) {
                        booking.status = "completed";
                        completedBookings.push(booking);
                    } else if (booking.status === "cancelled") {
                        cancelledBookings.push(booking);
                    }
                });

                // Display the bookings under different tabs
                displayBookings(upcomingBookings, "upcomingBookings");
                displayBookings(completedBookings, "completedBookings");
                displayBookings(cancelledBookings, "cancelledBookings");
            },
            error: function(xhr, status, error) {
                console.error(xhr.responseText);
            }
        });
    });
    


   
   /*
    function displayBookings(bookings, tabId) {
        var $tab = $("#" + tabId);
        $tab.empty();
        if (bookings.length > 0) {
            var tableHtml = "<table class='table table-striped'><thead><tr><th>BookingId</th><th>Check In Date</th><th>Check Out Date</th>";
            if (tabId === "upcomingBookings") {
                tableHtml += "<th>Action</th>";
            } else {
                tableHtml += "<th>Status</th>";
            }
            tableHtml += "</tr></thead><tbody>";

            $.each(bookings, function(index, booking) {
                var cancelLinkHtml = "";
                if (tabId === "upcomingBookings") {
                    cancelLinkHtml = "<a href='#' onclick='cancelBooking(" + booking.bookingId + ")' class='btn btn-danger text-white'>Cancel</a>";
                }
                tableHtml += "<tr><td>" + booking.bookingId + "</td><td>" + new Date(booking.checkInDate).toDateString() + "</td><td>" + new Date(booking.checkOutDate).toDateString() + "</td><td>" + booking.status + "</td><td>" + cancelLinkHtml + "</td></tr>";
            });

            tableHtml += "</tbody></table>";
            $tab.html(tableHtml);
        } else {
            $("<p>").text("No bookings to display").appendTo($tab);
        }
    }
    */
   function displayBookings(bookings, tabId) {
    var $tab = $("#" + tabId);
    $tab.empty();
    if (bookings.length > 0) {
        var tableHtml = "<table class='table table-striped'><thead><tr><th>Booking ID</th><th>Check-in Date</th><th>Check-out Date</th><th>Status</th>";
        if (tabId === "upcomingBookings") {
            tableHtml += "<th>Action</th>";
        } else if (tabId === "completedBookings") {
            tableHtml += "<th>Review</th>";
        }
        tableHtml += "</tr></thead><tbody>";

        $.each(bookings, function(index, booking) {
            var cancelLinkHtml = "";
            var reviewBtnHtml = "";
            if (tabId === "upcomingBookings") {
                cancelLinkHtml = "<a href='#' onclick='cancelBooking(" + booking.bookingId + ")' class='btn btn-danger text-white'>Cancel</a>";
            } else if (tabId === "completedBookings") {
                //reviewBtnHtml = "<button id='reviewBtn' class='btn btn-primary' onclick='leaveReview(" + booking.bookingId + ")'>Leave a review</button>";
            	reviewBtnHtml = "<button id='reviewBtn' class='btn btn-primary'>Leave a review</button>";
            }
            tableHtml += "<tr><td>" + booking.bookingId + "</td><td>" + new Date(booking.checkInDate).toDateString() + "</td><td>" + new Date(booking.checkOutDate).toDateString() + "</td><td>" + booking.status + "</td><td>" + cancelLinkHtml + reviewBtnHtml + "</td></tr>";
        });

        tableHtml += "</tbody></table>";
        $tab.html(tableHtml);
    } else {
        $("<p>").text("No bookings to display").appendTo($tab);
    }
}

	
});
