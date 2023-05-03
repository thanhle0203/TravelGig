$(document).ready(function() {
    // function to cancel a booking
    function cancelBooking(bookingId) {    
        // make AJAX call to change booking status to "cancelled"
        $.ajax({
            type: "POST",
            url: "/cancelBooking/{bookingId}",
            data: { bookingId: bookingId },
            success: function() {
                // reload the page to display updated bookings
                location.reload();
            }
        });
    };
    
    $(function () {
        // Fetch the bookings of the logged in user
        // Ajax call to fetch the bookings
        $.ajax({
            url: "/bookings/" + "${mobile}",
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
                    cancelLinkHtml = "<a href='/cancelBooking/' onclick='cancelBooking(" + booking.bookingId + ")'>Cancel</a>";
                }
                tableHtml += "<tr><td>" + booking.bookingId + "</td><td>" + new Date(booking.checkInDate).toDateString() + "</td><td>" + new Date(booking.checkOutDate).toDateString() + "</td><td>" + booking.status + "</td><td>" + cancelLinkHtml + "</td></tr>";
            });

            tableHtml += "</tbody></table>";
            $tab.html(tableHtml);
        } else {
            $("<p>").text("No bookings to display").appendTo($tab);
        }
    }
});
