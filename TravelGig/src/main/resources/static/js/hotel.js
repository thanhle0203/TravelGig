$(document).ready(function() {
  $("#searchBtn").click(function(){
    var searchText = $("#searchLocation").val();
    $('#tblHotel tr').not(':first').remove();
    $.get("http://localhost:8383/searchHotel/"+ searchText, {}, function(responseText) {
      $.each(responseText, function(key1, value1) {
        var row = "<tr><td>" + value1.hotelName + "</td><td><img class='hotelImage' src='" + value1.imageURL + "' width='300' height='300'/></td><td>" + value1.averagePrice + "</td><td>" + value1.starRating + "</td> </tr>";
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



  $("#tblHotel").on('click','.hotelImage', function() {
    var hotelName = $(this).parent().parent().children("td").eq(0).text();
    var hotelRoomTypes;
    var searchText = $("#searchLocation").val();

    $.get("http://localhost:8383/searchHotel/" + searchText, function(responseText) {
      hotelRoomTypes = responseText[0].hotelRooms;

      $("#myModal").modal();
      $("#modal_hotelName").val(hotelName);
      $("#modal_noGuests").val($("#noGuests").val());
      $("#modal_CheckInDate").val($("#checkInDate").val());
      $("#modal_CheckOutDate").val($("#checkOutDate").val());
      $("#modal_noRooms").val($("#noRooms").val());

      // Retrieve the room types for the selected hotel
      // hotelRoomTypes = responseText.find(hotel => hotel.hotelName === hotelName).roomTypes;

      // Remove existing options before adding new ones
      $("#select_roomTypes").empty();

      for (var i=0; i< hotelRoomTypes.length; i+=1){
        $("#select_roomTypes").append($("<option />").val(hotelRoomTypes[i].type.name).text(hotelRoomTypes[i].type.name));
      }
    });
  });
});