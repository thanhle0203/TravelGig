// Fetch mobile number of logged-in user
  var mobile = "";

$(document).ready(function() {
  
  // Fetch the bookings of the logged in user
  // Ajax call to fetch the bookings
  $.ajax({
    url: "http://localhost:8282/bookings",
    method: "GET",
    success: function (bookings) {
      $.each(bookings, function (index, booking) {
        mobile = booking.customerMobile;
      });

      // Fetch upcoming bookings for the logged-in user
      $.get("http://localhost:8282/getUpComingBookingsByPhone/" + mobile)
        .done(function(response) {
          displayBookings(response, "upcomingBookings", true, true, true, mobile);
        })
        .fail(function(jqXHR, textStatus, errorThrown) {
          console.error("Error fetching upcoming bookings: " + errorThrown);
        });

      // Fetch completed bookings for the logged-in user
      $.get("http://localhost:8282/getCompletedBookingsByPhone/" + mobile)
        .done(function(response) {
          displayBookings(response, "completedBookings", true, true, false, mobile);
        })
        .fail(function(jqXHR, textStatus, errorThrown) {
          console.error("Error fetching completed bookings: " + errorThrown);
        });

      // Fetch cancelled bookings for the logged-in user
      $.get("http://localhost:8282/getCancelledBooking/" + mobile)
        .done(function(response) {
          displayBookings(response, "cancelledBookings", false, false, false, mobile);
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


function displayBookings(bookings, tableId, displayDetailsLink, displayReviewLink, displayCancelButton, mobile) {
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
              cancelBooking(booking.bookingId, mobile);
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
        $("#" + tableId + " tbody").append(row);
      } else if (booking.status === "completed" && booking.customerMobile === mobile) {
        if (displayReviewLink) {
                    //var reviewLink = $("<a>")
            //.attr("href", "review.jsp?bookingId=" + booking.bookingId)
            //.text("Write Review");
            var reviewBtn = jQuery("<button>")	
  		.addClass("btn btn-primary")
  		.text("Leave a review")
  		.click(function (event) {
    		event.preventDefault();
    		jQuery.noConflict();
   			jQuery("#reviewModal").modal("show");

    // Save review data when the modal form is submitted
    jQuery("#reviewForm").submit(function (event) {
      event.preventDefault();

      var hotelId = booking.hotelId;
      var rating = jQuery("#rating").val();
      var reviewText = jQuery("#reviewText").val();

	  var reviewData = { "rating": rating, "review": reviewText };
	  console.log(reviewData);
	  
      jQuery.ajax({
        type: "POST",
        url: "http://localhost:8383/hotel/reviews/" + hotelId,
        contentType: "application/json",
        data: JSON.stringify(reviewData),
        dataType: 'json',
        success: function (data) {
          console.log(data);
          jQuery("#reviewModal").modal("hide");
          location.reload();
        },
        error: function (xhr, status, error) {
          console.error(xhr.responseText);
        },
      });
    });
    
  });

            
          row.append($("<td>").text(booking.status));
          //row.append($("<td>").append(reviewLink));
          row.append($("<td>").append(reviewBtn));
        } 
        
        /* else  {		
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
        */
        $("#completedBookings tbody").append(row);
      } else if (booking.status === "cancelled" && booking.customerMobile === mobile) {
        row.append($("<td>").text(booking.status));
        row.append($("<td>"));
        $("#cancelledBookings tbody").append(row);
      }
    });
  }
};

function cancelBooking(bookingId, mobile) {
  $.ajax({
    type: "POST",
    url: "http://localhost:8282/saveCancelBooking/" + bookingId,
    contentType: "application/json", // Specify the media type as JSON
    data: JSON.stringify({ bookingId: bookingId }), // Convert the data to JSON format
    success: function(response) {
      console.log("Booking cancelled successfully.");
      // Reload the cancelled bookings table
      $.get("http://localhost:8282/getCancelledBooking/" + mobile)
        .done(function(response) {
          displayBookings(response, "cancelledBookings", true, false, false, mobile);
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


