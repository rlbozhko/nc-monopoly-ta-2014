<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Ebay analyzer - sign up</title>
		<script type="text/javascript">
			function checkEmail() {
				var email = document.getElementById("inputEmail").value;
				if (email !== "") {
					var xhr = new XMLHttpRequest();
					xhr.open('get', 'email.action?email=' + email);
					// Track the state changes of the request
					xhr.onreadystatechange = function() {
					    // Ready state 4 means the request is done
					    if (xhr.readyState === 4) {
					        // 200 is a successful return
					        if (xhr.status === 200) {
					        	if (xhr.responseText === 'true') {
					        		document.getElementById("inputEmail").style.background = "#ffcece";
					        		document.getElementById("message_email").style.visibility = "visible";
					        	} else {
					        		document.getElementById("inputEmail").style.background = "#e8ffce";
					        		document.getElementById("message_email").style.visibility = "hidden";					        		
					        	}
					    	}
					    }
					};
					// Send the request email.action
					xhr.send(null);
				}
			}
			
			function checkNickName() {
				var nickname = document.getElementById("inputNickName").value;
				if (nickname !== "") {
					var xhr = new XMLHttpRequest();
					xhr.open('get', 'nickname.action?nickname=' + nickname);
					// Track the state changes of the request
					xhr.onreadystatechange = function() {
					    // Ready state 4 means the request is done
					    if (xhr.readyState === 4) {
					        // 200 is a successful return
					        if (xhr.status === 200) {
					        	if (xhr.responseText === 'true') {
					        		document.getElementById("inputNickName").style.background = "#ffcece";
					        		document.getElementById("message_nickname").style.visibility = "visible";
					        	} else {
					        		document.getElementById("inputNickName").style.background = "#e8ffce";
					        		document.getElementById("message_nickname").style.visibility = "hidden";					        		
					        	}
					    	}
					    }
					};
					// Send the request email.action
					xhr.send(null);
				}
			}
			
			function checkPassword() {
				var password = document.getElementById("inputPassword").value;
				var retypePassword = document.getElementById("retypePassword").value;
				
				if (password !== "" && retypePassword !== "") {
					if (password !== retypePassword) {
						document.getElementById("retypePassword").style.background = "#ffcece";
		        		document.getElementById("message_password").style.visibility = "visible";
					} else {
						document.getElementById("retypePassword").style.background = "#e8ffce";
		        		document.getElementById("message_password").style.visibility = "hidden";	
					}
				}
			}
		</script>
		<style>
			.form-signup {
				width: 300px;
			}
			
			.navbar {
			    margin-bottom:0;
  				background-color: #205081;
			}
		</style>
			<link href="webjars/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet">
			<link href="webjars/bootstrapvalidator/0.5.3/css/bootstrapValidator.min.css" rel="stylesheet">
<!-- 			<link href="css/main.css" rel="stylesheet"> -->
			<script src="webjars/jquery/2.1.1/jquery.min.js"></script>
			<script src="webjars/bootstrapvalidator/0.5.3/js/bootstrapValidator.min.js"></script>
			<script src="webjars/bootstrap/3.3.1/js/bootstrap.min.js"></script>
	</head>
	<body>
		<div id="navtop">
	  		<div class="navbar navbar-custom navbar-static-top">
	    		<div class="container">
	      			<div class="navbar-collapse collapse">        
	        			<ul class="nav pull-right navbar-nav">
	          				<li>
					            <form class="navbar-form" action="signin.action" method="get">
					            	<button type="submit" class="btn btn-primary">Sign in</button>
					            </form>
	          				</li>
	          				<li>
					            <form class="navbar-form" action="signup.action" method="get">
					              <button type="submit" class="btn btn-primary">Sign up</button>
					            </form>
	          				</li>
	        			</ul>
	      			</div>		
	    		</div>
	  		</div>
		</div>
		<div class="container" align="center" role="form">
			<form class="form-signup" action="signup.action"method="post">
				<h2 class="form-signup-heading">Please sign up</h2>
				<label class="sr-only" for="inputNickName">Your nickname</label>
			 	<input id="inputNickName" class="form-control" type="text" name="nickName" value="${nickName}" autofocus required placeholder="Your nickname" onblur="checkNickName()">
				<label class="sr-only" for="inputEmail">Email address</label>
				<input id="inputEmail" class="form-control" type="email" name="email" value="${email}" required placeholder="Email address" onblur="checkEmail()">
				<label class="sr-only" for="inputPassword">Password</label>
				<input id="inputPassword" class="form-control" type="password" name="password" pattern=".{5,25}" required placeholder="Password (min 5 characters)" onchange="checkPassword()">
				<label class="sr-only" for="retypePassword">Retype password</label>
				<input id="retypePassword" class="form-control" type="password" name="retypePassword" pattern=".{5,25}"  required placeholder="Retype password" onchange="checkPassword()">
				<button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
				<label id="message_password" style="color: red; visibility:hidden">Passwords do not match, please try again</label><br>
				<c:if test="${!isPasswordValid}"><label style="red">Passwords or email not valid, please check Your data</label></c:if> <br><br>
				<label id="message_email" for="inputEmail" style="color: red; visibility:hidden">Email is already in use, please type another email</label><br>
				<label id="message_nickname" for="inputNickName" style="color: red; visibility:hidden">Nickname is already in use, please type another nickname</label><br>
			</form>
		</div>
	</body>
</html>