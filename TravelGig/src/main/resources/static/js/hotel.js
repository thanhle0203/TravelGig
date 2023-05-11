$(document).ready(function() {
    $("#searchBtn").click(function(){
      var searchText = $("#searchLocation").val();
      $('#tblHotel tr').not(':first').remove();
      $.get("http://localhost:8383/searchHotel/"+ searchText, {}, function(responseText) {
        $.each(responseText, function(key1, value1) {
          var amenities = value1.amenities.map(function(amenity) {
              return amenity.name;
          }).join(", ");
          var hotelName = value1.hotelName;
          var imageURL = value1.imageURL;
          var averagePrice = value1.averagePrice;
          var starRating = value1.starRating;
          var row = "<tr><td>" + hotelName + "</td><td><img class='hotelImage' src='" + imageURL + "' width='300' height='300'/></td><td>" + averagePrice + "</td><td>" + starRating + "</td><td>" + amenities + " </td></tr>";
          $("#tblHotel").append(row);
        });
      });
    });
    
    $("#filterBtn").click(function(){
        var price = parseInt($("#priceValue").text());
            $("#tblHotel tr").not(":first").each(function() {
            $(this).show();
            var hotelPriceText = $(this).children("td").eq(2).text();
            var startRatingText = $(this).children("td").eq(3).text();
            var hotelPrice = /^\d+$/.test(hotelPriceText) ? parseInt(hotelPriceText) : null;
            var startRating = /^\d+$/.test(startRatingText) ? parseInt(startRatingText) : null;
            var flag = 0;
            $('.star_rating').each(function() {
                if (this.checked && $(this).val() == startRating) {
                flag = 1;
                }
            });
            if (flag == 0) {
                $(this).hide();
            } else if (price > hotelPrice) {
                $(this).hide();
            }
        });
    });
  
      // Define responseText variable
    var responseText;
    
    // Initialize datepicker with format option
    $("#modal_CheckInDate").datepicker({
        format: "yyyy-mm-dd"
    });
    
    $("#modal_CheckOutDate").datepicker({
        format: "yyyy-mm-dd"
    });
    
    // Click event handler for hotel image in table
    $("#tblHotel").on('click', '.hotelImage', function () {
        // Get hotel name and search text
        var hotelName = $(this).parent().parent().children("td").eq(0).text();
        var searchText = $("#searchLocation").val();
    
        // Send AJAX request to server to get hotel room types
        $.get("http://localhost:8383/searchHotel/" + searchText, function (response) {
            responseText = response;
            var hotelRoomTypes = responseText[0].hotelRooms;
        
            // Populate modal fields with data
            $("#myModal").modal();
            $("#modal_hotelName").val(hotelName);
            $("#modal_noGuests").val($("#noGuests").val());
            $("#modal_CheckInDate").val($("#checkInDate").val());
            $("#modal_CheckOutDate").val($("#checkOutDate").val());
            $("#modal_noRooms").val($("#noRooms").val());
        
            // Remove existing options before adding new ones
            $("#select_roomTypes").empty();
        
            // Populate room types dropdown with options
            // for (var i = 0; i < hotelRoomTypes.length; i += 1) {
                //$("#select_roomTypes").append($("<option />").val(hotelRoomTypes[i].type.name).text(hotelRoomTypes[i].type.name));
            //}
            
            hotelRoomTypes.forEach(function(roomType) {
                $("#select_roomTypes").append($("<option />").val(roomType.type.name).text(roomType.type.name));
            });
    
        });
    });
    
    
    var hotelId;
    var hotelRoomId;
    var customerMobile;
    var noGuests;
    var noRooms;
    var checkInDate;
    var checkOutDate;
    var price;
    var discount;
    
    $("#myModal").on('click', '.btn-searchHotelRooms', function () {
        var hotelName = $("#modal_hotelName").val();
        //var responseText = JSON.parse($("#modal_hotelDetails").val());
        hotelId = responseText[0].hotelId;
        var hotelRoomTypes = responseText[0].hotelRooms;
        hotelRoomId = hotelRoomTypes.find(room => room.type.name === $("#select_roomTypes").val()).hotelRoomId;
        
        //noGuests = $("#modal_noGuests").val();
        noGuests = parseInt($("#modal_noGuests").val());
        

        
        
        noRooms = parseInt($("#modal_noRooms").val(), 10);
        checkInDate = moment($("#modal_checkInDate").val(), "YYYY-MM-DD");
        checkOutDate = moment($("#modal_checkOutDate").val(), "YYYY-MM-DD");
    
    
        var roomType = $("#select_roomTypes").val();
        
        
    
        // Validate check-in and check-out dates
        if (moment(checkOutDate).isBefore(checkInDate)) {
        alert("Invalid date range: Check-out date must be after Check-in date.");
        return;
        }
    
        // Calculate discount and total price
        var totalPrice = 0;
        var roomTypeObject = hotelRoomTypes.find(room => room.type.name === roomType);
        if (!roomTypeObject) {
            alert("Invalid room type selected.");
            return;
        }
            
        var pricePerNight = responseText[0].averagePrice;
        if (isNaN(pricePerNight)) {
            alert("Invalid price for room type.");
            return;
        } 
        
        discount = responseText[0].discount*pricePerNight/100;;
        
        var nights = moment(checkOutDate).diff(moment(checkInDate), 'days');
        var basePrice = pricePerNight * noRooms*nights;
    
        totalPrice = basePrice - discount;
        price = totalPrice;
    
        // Update confirm booking modal fields
        $("#booking_hotelId").val(hotelId);
        $("#booking_hotelRoomId").val(hotelRoomId);
        $("#booking_hotelName").val(hotelName);
        $("#booking_customerMobile").val(customerMobile);
        $("#booking_noGuests").val(noGuests);
        $("#booking_noRooms").val(noRooms);
        $("#booking_checkInDate").val(checkInDate);
        $("#booking_checkOutDate").val(checkOutDate);
        $("#booking_roomType").val(roomType);
        $("#booking_discount").text(discount);
        $("#booking_price").text(totalPrice);
            
        // Display booking hotel room modal
        $("#bookingHotelRoomModal").modal();
    
       
    });
    
    
    // Close booking hotel model and close search hotel modal when clicking confirm booking button
    $("#btn_confirmBooking").on('click', function() {
        customerMobile = $("#booking_customerMobile").val();
        console.log(noGuests);
       
      	// Open guest details modal
    	$("#guestDetailsModal").modal();  	
    	
    });
  
    // Click event handler for Submit button in Guest Details modal
	$("#submitGuestDetailsBtn").on('click', function() {
		// Get the guest details entered by the user
	
		var firstName = $("#guestFirstName").val();
		var lastName = $("#guestLastName").val();
		var gender = $("#guestGender").val();
		var age = $("#guestAge").val();
		
	    var guests = {"firstName": firstName, "lastName": lastName, "gender": gender, "age": age};
  
     //Prepare booking data
     var status = "upcoming";
     var bookedOnDate = new Date();
   
    var roomType = $("#select_roomTypes").val();
   	var email = "XXXXXXXX@gmail.com";
   	
   	
   	
   	var booking = {"hotelId": hotelId,  "hotelRoomId": hotelRoomId, "noRooms": noRooms,"guests": [guests],
	     "checkInDate": checkInDate, "checkOutDate": checkOutDate, "bookedOnDate": bookedOnDate,
	     "status": status, "price": price, "discount": discount,
	     "customerMobile": customerMobile, "roomType": roomType, "email": email};
	     
	console.log(booking);
  
    // Display guest details modals
    //for (var i = 0; i < noGuests; i++) {
    $("#guestDetailsModal").modal();
  
    //}

	// define bookingId variable here
var bookingId;

$.ajax({
    type: "POST",
    contentType: 'application/json',
    url: "http://localhost:8484/bookings",
    data: JSON.stringify(booking),
    dataType: 'json',
    success: function(response) {
        // set bookingId from response
        bookingId = parseInt(response.bookingId);
        console.log("BookingId: " + bookingId);
        
        // Display the booking confirmation modal
        $("#bookingConfirmationModal").modal();
        $("#guestDetailsModal").modal('hide');
        $("#bookingHotelRoomModal").modal('hide');
        $("#myModal").modal('hide');

        // Send booking details
        //$.post("http://localhost:8282/sendBookingDetails/" + bookingId, {}, function(response) {
		$.post("http://localhost:8282/sendBooking/" + bookingId, {}, function(response) {
            // Handle the response from the server
            console.log(response);
        }).fail(function(jqXHR, textStatus, errorThrown) {
            // Handle the error case
            console.log("Failed to send booking details: " + errorThrown);
        });
    
    },
    error: function(jqXHR, textStatus, errorThrown) {
        // Display an error message
        alert("Failed to submit booking details. Please try again.");
    }
});
	

});
	

});

 


