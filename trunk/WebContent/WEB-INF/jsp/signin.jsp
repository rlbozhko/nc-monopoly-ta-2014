<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Monopoly - sign in</title>
		<style>
			.form-signin {
	                width: 300px;
	            		}
		</style>
			 <link href="webjars/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet">
			 <link href="webjars/bootstrapvalidator/0.5.3/css/bootstrapValidator.min.css" rel="stylesheet">
			 <link href="css/main.css" rel="stylesheet">
			 <script src="webjars/jquery/2.1.1/jquery.min.js"></script>
			 <script src="webjars/bootstrapvalidator/0.5.3/js/bootstrapValidator.min.js"></script>
			 <script src="webjars/bootstrap/3.3.1/js/bootstrap.min.js"></script>
	</head>
	<body>
	
	<div class="container" align="center" role="form">
		<form class="form-signin" action="signin.action" method="post">
			<h2 class="form-signin-heading">Please sign in</h2>
			<label class="sr-only" for="inputEmail">Email address</label>
			<input id="inputEmail" class="form-control" type="email" name="email" autofocus required placeholder="Email address">
			<label class="sr-only" for="inputPassword">Password</label>
			<input id="inputPassword" class="form-control" type="password" name="password" required placeholder="Password">
<%-- 			<c:if test="${!isVerificationUser}"><font color="red">You type wrong email or password, please try again</font></c:if> <br><br> --%>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
		</form>
	</div>
	</body>
</html>