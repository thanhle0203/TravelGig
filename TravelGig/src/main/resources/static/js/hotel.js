$(document).ready(function() {
    $("#searchBtn").click(function(){
        var searchText = $("#searchLocation").val();
      $('#tblHotel tr').not(':first').remove();
        $.get("http://localhost:8383/searchHotel/"+ searchText, {}, function(responseText) {
            $.each(responseText, function(key1, value1) {
                var row = "<tr><td>" + value1.hotelName + "</td><td><img class='hotelImage' src='" + value1.imageURL + "' width='300' height='300'/></td><td>" + value1.averagePrice + "</td><td>" + value1.starRating + "</td></tr>";
                $("#tblHotel").append(row);
            });
        });
    });
});
