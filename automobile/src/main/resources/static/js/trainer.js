$(document).ready(function () {
  $('#add-trainer-form').submit(function (event) {
    event.preventDefault();
    var trainerName = $('#trainerName').val();
    var trainerAge = $('#trainerAge').val();

    $.ajax({
      url: 'http://localhost:8383/saveTrainer',
      type: 'POST',
      contentType: 'application/json',
      data: JSON.stringify({
        'trainerName': trainerName,
        'trainerAge': trainerAge
      }),
      success: function (data) {
        alert('Trainer added successfully!');
        $('#trainerName').val('');
        $('#trainerAge').val('');
      },
      error: function () {
        alert('Error adding trainer!');
      }
    });
  });

  $('#trainersModal').on('show.bs.modal', function (event) {
    var modal = $(this);
    modal.find('#trainersTableBody').empty();
    $.ajax({
      url: 'http://localhost:8383/getAllTrainers',
      type: 'GET',
      success: function (data) {
        for (var i = 0; i < data.length; i++) {
          var trainer = data[i];
          modal.find('#trainersTableBody').append('<tr><td>' + trainer.trainerId + '</td><td>' + trainer.trainerName + '</td><td>' + trainer.trainerAge + '</td></tr>');
        }
      },
      error: function () {
        alert('Error retrieving trainers!');
      }
    });
  });
});
