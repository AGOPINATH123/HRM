<%@ page import="com.hrm.session.SharedObject"%>
<%@ page language="java" contentType="text/html" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Dhatsol</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
 <style> 
     .carousel-item {
  height: 100vh;
  min-height: 350px;
  background: no-repeat center center scroll;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
}
</style>

    <style type="text/css">
    body {
		font-family: 'Varela Round', sans-serif;
	}
	.modal-login {
		color: #636363;
		width: 350px;
	}
	.modal-login .modal-content {
		padding: 20px;
		border-radius: 5px;
		border: none;
	}
	.modal-login .modal-header {
		border-bottom: none;
		position: relative;
		justify-content: center;
	}
	.modal-login h4 {
		text-align: center;
		font-size: 26px;
	}
	.modal-login  .form-group {
		position: relative;
	}
	.modal-login i {
		position: absolute;
		left: 13px;
		top: 11px;
		font-size: 18px;
	}
	.modal-login .form-control {
		padding-left: 40px;
	}
	.modal-login .form-control:focus {
		border-color: #00ce81;
	}
	.modal-login .form-control, .modal-login .btn {
		min-height: 40px;
		border-radius: 3px; 
	}
	.modal-login .hint-text {
		text-align: center;
		padding-top: 10px;
	}
	.modal-login .close {
        position: absolute;
		top: -5px;
		right: -5px;
	}
	.modal-login .btn {
		background: #00ce81;
		border: none;
		line-height: normal;
	}
	.modal-login .btn:hover, .modal-login .btn:focus {
		background: #00bf78;
	}
	.modal-login .modal-footer {
		background: #ecf0f1;
		border-color: #dee4e7;
		text-align: center;
		margin: 0 -20px -20px;
		border-radius: 5px;
		font-size: 13px;
		justify-content: center;
	}
	.modal-login .modal-footer a {
		color: #999;
	}
	.trigger-btn {
		display: inline-block;
		margin: 100px auto;
	}
</style>
<script type="text/javascript">

	function loginValidation() {
		var employeeId = document.getElementById("loginId").value;
		var mobileNumber = document.getElementById("Password").value;
		var type = document.getElementById("type").value;
		if (employeeId == null || employeeId == "") {
			document.getElementById("errorMsg").innerHTML = "please enter EmployeeId";
			return false;
		} else if (mobileNumber == null || mobileNumber == "") {
			document.getElementById("errorMsg1").innerHTML = "please enter Password";
			return false;
		} else if (type == "" || type == "None") {
			document.getElementById("errorMsg2").innerHTML = "Enter Given Type";
			return false;
		}

		else
			return true;
	}
	function clearForm() {
		document.getElementById("errorMsg").innerHTML = "";
		document.getElementById("errorMsg1").innerHTML = "";
		document.getElementById("errorMsg2").innerHTML = "";
	}
</script>
</head>
<body>

	<%
	SharedObject.setSession(session);
	%>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		function showcommentform() {
			var data = "EmployeeId:<input type='text' name='employeeId'>";

			document.getElementById('location').innerHTML = data;
		}

		function showcommentform1() {
			document.getElementById('location').innerHTML = "";
		}
	</script>
 <nav class="navbar navbar-dark bg-dark">
  <div class="container">
    <a class="navbar-brand" href="index.jsp">Dhatsol It Solutions</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav ml-auto">
        <li class="nav-item active"><a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a></li>
        <li class="nav-item"><a class="nav-link" href="#myModal" class="trigger-btn" data-toggle="modal">Login</a></li>
        <li class="nav-item"><a class="nav-link" href="Aboutus.jsp">About</a></li>
      </ul>
    </div>
  </div>
</nav>
    
<header>
<img src="data:image/jpeg;base64,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" data-deferred="1" class="rg_i Q4LuWd" name="Q4LuWd" width="1600" height="765" alt="Backgrounds, 960000+ Background Images, Wallpaper, Poster, Banners for Free  Download" 

data-iml="1856.5" data-atf="false">

  <!-- <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
      <ol class="carousel-indicators">
      <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
      <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
      <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner" role="listbox">
      Slide One - Set the background image for this slide in the line below
      <div class="carousel-item active" style="background-image: url('https://source.unsplash.com/LAaSoL0LrYs/1920x1080')">
        <div class="carousel-caption d-none d-md-block">
        
        </div>
      </div> -->
      <!-- Slide Two - Set the background image for this slide in the line below -->
      <!-- <div class="carousel-item" style="background-image: url('https://source.unsplash.com/bF2vsubyHcQ/1920x1080')">
        <div class="carousel-caption d-none d-md-block">
         
        </div>
      </div>
      Slide Three - Set the background image for this slide in the line below
      <div class="carousel-item" style="background-image: url('https://source.unsplash.com/szFUQoyvrxM/1920x1080')">
        <div class="carousel-caption d-none d-md-block">
          
        </div>
      </div> -->
      
    </div>
    
    <!-- <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="sr-only">Previous</span>
        </a>
    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="sr-only">Next</span>
        </a> --> 
  
    </header>
    
    <div id="myModal" class="modal fade">
		<div class="modal-dialog modal-login">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Member Login</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
				</div>
				<div class="modal-body">
				<form action="LoginServlet" method="post">
				<div class="form-group">
				<i class="fa fa-user"></i>
	            <input type="text onkeypress = "clearForm()" id="loginId" name="employeeId" class="form-control" placeholder="EmployeeId" required="required">
	            </div>
				<div class="form-group">
				<i class="fa fa-lock"></i>
				  <input type="password"class="form-control" onkeypress="clearForm()" id="Password"
								name="mobileNumber" placeholder="Enter password"
								required="required">
			    </div>
			<div class="form-group">
							<p>Type</p>
							<select name="type" class="form-control" onkeypress="clearForm()"
								id="type">
								<option value="None">Enter Given Type</option>
								<option value="employee">EMPLOYEE</option>
								<option value="employeer"> Employee</option>
								<option value="admin">ADMIN</option>
								<option value="manager">Manager</option>
								<option value="hr">HR</option>
							</select>
			</div>
						<div class="form-group">
							<input type="submit" class="btn btn-primary btn-block btn-lg"
								type="submit" onclick="return loginValidation()" value="login">
						</div>
					</form>

				</div>
				<div class="modal-footer">
					<a href="ForgetPassword.jsp">forgot password</a>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
			</div>
		</div>
	</div>
	
    
</body>
</html>                            