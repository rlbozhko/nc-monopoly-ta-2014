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
	        .sign-up {
				margin-top: 20px;
			}
			.navbar {
			    margin-bottom:0;
  				background-color: #205081;
			}
			
		</style>
			 <link href="webjars/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet">
			 <link href="webjars/bootstrapvalidator/0.5.3/css/bootstrapValidator.min.css" rel="stylesheet">
<!-- 			 <link href="css/main.css" rel="stylesheet"> -->
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
			            			<button type="submit" class="btn btn-primary">Вход</button>
			            		</form>
       						</li>
       						<li>
			            		<form class="navbar-form" action="signup.action" method="get">
			              			<button type="submit" class="btn btn-primary">Регистрация</button>
			            		</form>
        					</li>
        				</ul>
      				</div>		
    			</div>
  			</div>
		</div>	  	  	
		<div class="container" align="center" role="form">
			<form class="form-signin" action="signin.action" method="post">
				<h2 class="form-signin-heading">Please sign in</h2>
				<label class="sr-only" for="inputEmail">Email address</label>
				<input id="inputEmail" class="form-control" type="email" name="email" autofocus required placeholder="Email address">
				<label class="sr-only" for="inputPassword">Password</label>
				<input id="inputPassword" class="form-control" type="password" name="password" required placeholder="Password">
				<div class="buttons-container">
					<div class="buttons">
						<button class="btn btn-lg btn-primary btn-block" type="submit">Вход</button>
					</div>
				</div>
			</form>
				<div class="sign-up">
        			<a href="signup.action">Необходим аккаунт? Зарегистрироваться бесплатно.</a>
        		</div>
		</div>
	</body>
</html>