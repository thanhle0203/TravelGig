// function to cancel a booking
function cancelBooking(bookingId) {
  // make AJAX call to change booking status to "cancelled"
  jQuery.ajax({
    type: "POST",
    url: "/cancelBooking/" + bookingId,
    url: "http://localhost:8484/bookings/deleteBooking/" + bookingId,
    data: { bookingId: bookingId },
    success: function () {
      // reload the page to display updated bookings
      location.reload();
    },
  });
}

jQuery(document).ready(function () {
  jQuery(function () {
    // Fetch the bookings of the logged in user
    // Ajax call to fetch the bookings
    jQuery.ajax({
      url: "/myBookings",
      //url: "/mybookings",
      method: "GET",
      success: function (bookings) {
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

        jQuery.each(bookings, function (index, booking) {
          if (
            booking.status === "upcoming" &&
            new Date(booking.checkInDate) > currentDate
          ) {
            upcomingBookings.push(booking);
          } else if (
            booking.status === "completed" ||
            (booking.status === "upcoming" &&
              new Date(booking.checkInDate) <= currentDate)
          ) {
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
      error: function (xhr, status, error) {
        console.error(xhr.responseText);
      },
    });
  });
  
  function displayBookings(bookings, tableId) {
  var tableBody = jQuery("#" + tableId + " tbody");
  tableBody.empty();

  jQuery.each(bookings, function (index, booking) {
    var row = jQuery("<tr>");
    row.append(jQuery("<td>").text(booking.bookingId));
    row.append(jQuery("<td>").text(booking.checkInDate));
    row.append(jQuery("<td>").text(booking.checkOutDate));

    if (booking.status === "upcoming") {
      var cancelBtn = jQuery("<button>")
        .addClass("btn btn-danger")
        .text("Cancel")
        .click(function () {
          cancelBooking(booking.bookingId);
        });

      row.append(jQuery("<td>").append(cancelBtn));
    } else if (booking.status === "completed") {
		/*
      var reviewBtn = jQuery("<button>")
  .addClass("btn btn-primary")
  .text("Leave a review")
  .click(function (event) {
    console.log("Modal button clicked");
    event.preventDefault();
    jQuery.noConflict();
   jQuery("#reviewModal").modal("show");
  });
*/

	var reviewBtn = jQuery("<button>")
  .addClass("btn btn-primary")
  .text("Leave a review")
  .click(function (event) {
    console.log("Modal button clicked");
    event.preventDefault();
    jQuery.noConflict();
    jQuery("#reviewModal").modal("show");

    // Save review data when the modal form is submitted
    jQuery("#reviewForm").submit(function (event) {
      event.preventDefault();

	  //var hotelId = booking.hotel.hotelId;
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


      row.append(jQuery("<td>").text(booking.status));
      row.append(jQuery("<td>").append(reviewBtn));
    } else if (booking.status === "cancelled") {
      row.append(jQuery("<td>").text(booking.status));
    }

    tableBody.append(row);
  });
}

});


  