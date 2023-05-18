$(document).ready(function() {
// Handle button click
     $("#insurerForm").submit(function(e) {
        e.preventDefault(); // prevent form from submitting the default way
        window.location.href = '/autoInsurancePlans';
     });
});