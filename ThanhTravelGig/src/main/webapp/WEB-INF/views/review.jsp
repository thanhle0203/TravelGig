<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hotel Review</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container" style="margin-top: 50px;">
	<div class="row">
		<div class="col-md-6 offset-md-3">
			<h3>Leave a Review</h3>
			<form:form action="/submitReview" method="post" modelAttribute="reviewForm">
				<div class="form-group">
					<label for="rating">Rating:</label>
					<select class="form-control" name="rating" id="rating" required>
						<option value="">-- Select Rating --</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
					</select>
					<small class="form-text text-muted">Select rating from 1 to 5</small>
				</div>
				<div class="form-group">
					<label for="guestNames">Guest Names:</label>
					<input type="text" class="form-control" id="guestNames" name="guestNames" required>
				</div>
				<div class="form-group">
					<label for="dateOfReview">Date of Review:</label>
					<input type="date" class="form-control" id="dateOfReview" name="dateOfReview" required>
				</div>
				<div class="form-group">
					<label for="reviewTitle">Title:</label>
					<input type="text" class="form-control" id="reviewTitle" name="reviewTitle" required>
				</div>
                <div class="form-group">
					<label for="review">Review</label>
					<textarea class="form-control" id="review" name="review" rows="5" required></textarea>
				</div>
				<div class="form-group">
					<input type="hidden" id="bookingId" name="bookingId" value="${booking.id}">
					<button type="submit" class="btn btn-primary">Submit Review</button>
				</div>
			</form:form>
		</div>
	</div>
</div>
</body>
</html>
