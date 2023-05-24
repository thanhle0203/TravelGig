$(document).ready(function() {
    // Listen for form submission
    $("#documentForm").on("submit", function(e) {
        e.preventDefault(); // Prevent default form submission

        // Create a new FormData object
        var formData = new FormData(this);

        // Send the data to the server
        $.ajax({
            type: "POST",
            url: "http://localhost:8383/api/documents",
            data: formData,
            processData: false,
            contentType: false,
            success: function(response) {
                // Handle the success response
                console.log("Document submitted successfully:", response);
                // You can update the UI or perform any other necessary actions here
            },
            error: function(xhr, status, error) {
                // Handle the error response
                console.error("Error submitting document:", error);
                // You can display an error message or perform any other necessary actions here
            }
        });
    });
});
