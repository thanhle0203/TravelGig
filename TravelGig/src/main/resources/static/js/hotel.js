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
    
    $("#myModal").on('click', '.btn-searchHotelRooms', function () {
        var hotelName = $("#modal_hotelName").val();
        //var responseText = JSON.parse($("#modal_hotelDetails").val());
        var hotelId = responseText[0].hotelId;
        var hotelRoomTypes = responseText[0].hotelRooms;
        var hotelRoomId = hotelRoomTypes.find(room => room.type.name === $("#select_roomTypes").val()).hotelRoomId;
        var customerMobile = $("#modal_customerMobile").val();
        var noGuests = $("#modal_noGuests").val();
        var noRooms = parseInt($("#modal_noRooms").val(), 10);
        var checkInDate = moment($("#modal_checkInDate").val(), "YYYY-MM-DD");
        var checkOutDate = moment($("#modal_checkOutDate").val(), "YYYY-MM-DD");
    
    
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
        
        var discount = responseText[0].discount*pricePerNight/100;;
        
        var nights = moment(checkOutDate).diff(moment(checkInDate), 'days');
        var basePrice = pricePerNight * noRooms*nights;
    
        totalPrice = basePrice - discount;
    
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
        
        
        // Display booking hotel room modal
        //$("#bookingHotelRoomModal").modal();
        // Get guest details
     var guests = [];	
     $(".guest").each(function() {
            var guest = {
              firstName: $(this).find(".guest-firstName").val(),
              lastName: $(this).find(".guest-lastName").val(),
              age: $(this).find(".guest-age").val(),
              gender: $(this).find(".guest-gender").val()
           };
            
     	guests.push(guest);
     	});
     
      
	var roomType = roomTypeObject;
     //Prepare booking data
     var status = "upcoming";
     var bookedOnDate = new Date();
    var price = totalPrice;
    var roomType = $("#select_roomTypes").val();
   var email = "XXXXXXXX@gmail.com";
	var booking = {"hotelId": hotelId,  "hotelRoomId": hotelRoomId, "noRooms": noRooms,"guests": guests,
	     "checkInDate": checkInDate, "checkOutDate": checkOutDate, "bookedOnDate": bookedOnDate,
	     "status": status, "price": price, "discount": discount,
	     "customerMobile": customerMobile, "roomType": roomType, "email": email};
  
  	// Make an AJAX request to submit the guest details
	$.ajax({
  			type: "POST",
            contentType: 'application/json',
           url: "http://localhost:8484/booking",
            data: JSON.stringify(booking),
            dataType: 'json',
  			success: function(response) {
    		// Display the booking confirmation modal
    		$("#bookingConfirmationModal").modal();
  		},
  		error: function(jqXHR, textStatus, errorThrown) {
    	// Display an error message
    	alert("Failed to submit booking details. Please try again.");
  		}
	});

    });
    
    
    // Close booking hotel model and close search hotel modal when clicking confirm booking button
    $("#btn_confirmBooking").on('click', function() {
        
    	//alert("Booking confirmed!");
        //$("#bookingHotelRoomModal").modal('hide');
      	//$("#myModal").modal('hide');
      	
      
      	// Open guest details modal
    	$("#guestDetailsModal").modal();
   
    	
    	
    });
  
    // Click event handler for Submit button in Guest Details modal
	$("#submitGuestDetailsBtn").click(function(event) {
		event.preventDefault();
	// Get the guest details entered by the user
		var firstName = $("#guestFirstName").val();
		var lastName = $("#guestLastName").val();
		var gender = $("#guestGender").val();
		var age = $("#guestAge").val();
		
	    var guest = {"firstName": firstName, "lastName": lastName, "gender": gender, "age": age};
  
  	// Make an AJAX request to submit the guest details
	$.ajax({
  			type: "POST",
            contentType: 'application/json',
            url: "http://localhost:8484/guest",
            data: JSON.stringify(guest),
            dataType: 'json',
  			success: function(response) {
    		// Display the booking confirmation modal
    		$("#bookingConfirmationModal").modal();
  		},
  		error: function(jqXHR, textStatus, errorThrown) {
    	// Display an error message
    	alert("Failed to submit guest details. Please try again.");
  		}
	});
	
		
	
	
	


	})
	
});

  