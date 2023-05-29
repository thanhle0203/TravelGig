<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page isELIgnored="false" %> 
<!DOCTYPE html>
<html>
    
    <head>
        <meta charset="UTF-8">
        <title>Sign Up</title>
        <script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="./js/signup.js"></script>
    </head>
    <body style='height: 100vh' class='w-100'>
        <div class='mt-5 d-flex justify-content-center'>
            <form action='signup' method='POST'>
                <h1>Sign Up</h1>
                <br><br>
                <div class="form-group">
                    <label>Please Enter Username:</label>
                    <input class="form-control" type="text" name="username" id="username">
                </div>
                
                <div class="form-group">
                    <label>Please Enter Password:</label>
                    <input class="form-control" type="password" name="password" id="password">
                </div>

                <div class="form-group">
                    <label>Please Enter Email:</label>
                    <input class="form-control" type="email" name="email" id="email">
                </div>

                <div class="form-group">
                    <label>Please Enter Phone Number:</label>
                    <input class="form-control" type="text" name="mobile" id="mobile">
                </div>

                <div><span class='text-muted'>Have an Account? </span ><a href='/login'>Login</a></div>
                <input class="btn btn-primary mt-5" type="submit" value="Signup" id="sign-up-btn">
            </form>
        </div>
        
    </body>
</html>
