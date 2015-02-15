<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Monopoly - index</title>
		<link href="webjars/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet">
		<link href="webjars/bootstrapvalidator/0.5.3/css/bootstrapValidator.min.css" rel="stylesheet">
		<link href="css/main.css" rel="stylesheet">
		<script src="webjars/jquery/2.1.1/jquery.min.js"></script>
		<script src="webjars/bootstrapvalidator/0.5.3/js/bootstrapValidator.min.js"></script>
		<script src="webjars/bootstrap/3.3.1/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/ajax.js"></script>
</head>
	<body>
		<div align="right">
			<a href="signin.action?signout">Sign out [${email}] </a>
		</div>	
			<form action="join_game.action" method="post">
				<div id="setup">
					<label>Enter number of players.</label>
					 <select id="playernumber" name="countPlayers" title="countPlayers">
						<option value="2" selected>2</option>
						<option value="3">3</option>
						<option value="4" >4</option>
						<option value="5">5</option>
					</select>
				</div>
				<input type="hidden" name="sessionStatusText" value="CREATING"> 
				Start Money<input type="text" name="startMoney"	value="${startMoney}" required>
	
			    <input type="submit" value="Create game">
		</form>
	</body>
	<script type="text/javascript">
	function done() {
		checkIndexStatus();		
		setTimeout(function() {
			done();// call done() again
		}, 100);// refresh it accordingly
	}

	$(document).ready(function() {
		done();
	});
</script>
</html>