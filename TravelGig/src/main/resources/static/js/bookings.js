$(document).ready(function() {
  // Fetch mobile number of logged-in user
  var mobile = "";

  // Fetch the bookings of the logged in user
  // Ajax call to fetch the bookings
  $.ajax({
    url: "/bookings",
    method: "GET",
    success: function (bookings) {
      $.each(bookings, function (index, booking) {
        mobile = booking.customerMobile;
      });
      
      // Fetch upcoming bookings for the logged-in user
      //$.get("localhost:8282/getUpComingBookingsByPhone/" + mobile)
      $.get("localhost:8484/bookings/upcomingMobile/" + mobile)
        .done(function(response) {
          displayBookings(response, "upcomingBookings", true, true, true);
        })
        .fail(function(jqXHR, textStatus, errorThrown) {
          console.error("Error fetching upcoming bookings: " + errorThrown);
        });

      // Fetch completed bookings for the logged-in user
      $.get("localhost:8282/getCompletedBookingsByPhone/" + mobile)
        .done(function(response) {
          displayBookings(response, "completedBookings", true, false, true);
        })
        .fail(function(jqXHR, textStatus, errorThrown) {
          console.error("Error fetching completed bookings: " + errorThrown);
        });

      // Fetch cancelled bookings for the logged-in user
      $.get("localhost:8282/saveCancelBooking", { mobile: mobile })
        .done(function(response) {
          displayBookings(response, "cancelledBookings", true, false, false);
        })
        .fail(function(jqXHR, textStatus, errorThrown) {
          console.error("Error fetching cancelled bookings: " + errorThrown);
        });
    },
    error: function(jqXHR, textStatus, errorThrown) {
      console.error("Error fetching bookings: " + errorThrown);
    }
  });
});

function displayBookings(bookings, tableId, displayDetailsLink, displayReviewLink, displayCancelButton) {
  var tableBody = $("#" + tableId + " tbody");
  tableBody.empty();

  if (bookings.length === 0) {
    var row = "<tr><td colspan='4'>No bookings found.</td></tr>";
    tableBody.append(row);
  } else {
    $.each(bookings, function(index, booking) {
      var row = $("<tr>");
      row.append($("<td>").text(booking.bookingId));
      row.append($("<td>").text(booking.checkInDate));
      row.append($("<td>").text(booking.checkOutDate));

      if (booking.status === "upcoming" && booking.customerMobile === mobile) {
        if (displayCancelButton) {
          var cancelBtn = $("<button>")
            .addClass("btn btn-danger")
            .text("Cancel")
            .click(function() {
              cancelBooking(booking.bookingId);
            });
          row.append($("<td>").append(cancelBtn));
        }
        if (displayDetailsLink) {
          var detailsLink = $("<a>")
            .attr("href", "bookingDetails.jsp?bookingId=" + booking.bookingId)
            .text("View Details");
          row.append($("<td>").append(detailsLink));
        } else {
          row.append($("<td>"));
        }
        $("#upcomingBookings tbody").append(row);
      } else if (booking.status === "completed" && booking.customerMobile === mobile) {
        if (displayReviewLink) {
          var reviewLink = $("<a>")
            .attr("href", "review.jsp?bookingId=" + booking.bookingId)
            .text("Write Review");
          row.append($("<td>").text(booking.status));
          row.append($("<td>").append(reviewLink));
        } else {
          row.append($("<td>").text(booking.status));
          if (displayDetailsLink) {
            var detailsLink = $("<a>")
              .attr("href", "bookingDetails.jsp?bookingId=" + booking.bookingId)
              .text("View Details");
            row.append($("<td>").append(detailsLink));
          } else {
            row.append($("<td>"));
          }
        }
        $("#completedBookings tbody").append(row);
      } else if (booking.status === "cancelled" && booking.customerMobile === mobile) {
        row.append($("<td>").text(booking.status));
        row.append($("<td>"));
        $("#cancelledBookings tbody").append(row);
      }
    });
  }
}

function cancelBooking(bookingId) {
$.ajax({
type: "POST",
url: "/saveCancelBooking",
data: { bookingId: bookingId },
success: function(response) {
console.log("Booking cancelled successfully.");
// Reload the cancelled bookings table
$.get("http://localhost:8282/getCancelledBookingsByPhone/" + mobile)
.done(function(response) {
displayBookings(response, "cancelledBookings", true, false, false);
})
.fail(function(jqXHR, textStatus, errorThrown) {
console.error("Error fetching cancelled bookings: " + errorThrown);
});
},
error: function(jqXHR, textStatus, errorThrown) {
console.error("Error cancelling booking: " + errorThrown);
}
});
}
