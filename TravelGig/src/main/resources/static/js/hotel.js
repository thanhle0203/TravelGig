$(document).ready(function() {
  $("#searchBtn").click(function(){
    var searchText = $("#searchLocation").val();
    $('#tblHotel tr').not(':first').remove();
    $.get("http://localhost:8383/searchHotel/"+ searchText, {}, function(responseText) {
      $.each(responseText, function(key1, value1) {
		var amenities = value1.amenities.map(function(amenity) {
			return amenity.name;
		}).join(", ");
        var row = "<tr><td>" + value1.hotelName + "</td><td><img class='hotelImage' src='" + value1.imageURL + "' width='300' height='300'/></td><td>" + value1.averagePrice + "</td><td>" + value1.starRating + "</td><td>" + amenities + " </td></tr>";
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
  //var pricePerNight = roomTypeObject.averagePrice;
   var pricePerNight = responseText[0].averagePrice;
  if (isNaN(pricePerNight)) {
  	alert("Invalid price for room type.");
  	return;
  } 
  
  var discount = responseText[0].discount*pricePerNight/100;;
  
  var nights = moment(checkOutDate).diff(moment(checkInDate), 'days');
  console.log(nights);
  //var basePrice = pricePerNight * noRooms * nights;
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
  console.log(checkInDate);
  console.log(checkOutDate);

  $("#booking_roomType").val(roomType);
  $("#booking_discount").text(discount);
  $("#booking_price").text(totalPrice);
  
  // Display booking hotel room modal
  $("#bookingHotelRoomModal").modal();
});

$("#btn_confirmBooking").on('click', function() {
  var hotelId = $("#booking_hotelId").val();
  var hotelRoomId = $("#booking_hotelRoomId").val();
  var customerMobile = $("#booking_customerMobile").val();
  var noGuests = $("#booking_noGuests").val();
  var noRooms = $("#booking_noRooms").val();
  var checkInDate = moment($("#booking_checkInDate").val()).format('MM-DD-YYYY');
  var checkOutDate = moment($("#booking_checkOutDate").val()).format('MM-DD-YYYY');

  var roomType = $("#booking_roomType").val();
  var discount = parseFloat($("#booking_discount").text());
  var totalPrice = parseFloat($("#booking_price").text());
  
  // Send AJAX request to server to confirm booking
  $.post("http://localhost:8383/confirmBooking", {
    hotelId: hotelId,
    hotelRoomId: hotelRoomId,
    customerMobile: customerMobile,
    noGuests: noGuests,
    noRooms: noRooms,
    checkInDate: checkInDate,
    checkOutDate: checkOutDate,
    roomType: roomType,
    discount: discount,
    totalPrice: totalPrice
  }, function(response) {
    alert("Booking confirmed!");
    $("#bookingHotelRoomModal").modal('hide');
  });
});



});
