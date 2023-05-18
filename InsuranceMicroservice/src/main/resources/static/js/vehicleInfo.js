$(document).ready(function() {
// Handle button click
     $("#detailsForm").submit(function(e) {
        e.preventDefault(); // prevent form from submitting the default way
        window.location.href = '/insurerInfo';
     });
});