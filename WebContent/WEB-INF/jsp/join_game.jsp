<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Join game</title>
		<style>
			.navbar {
			    margin-bottom:0;
  				background-color: #205081;
			}
			.setup {
				width: 250px;
			}
		</style>
		<link href="webjars/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet">
		<link href="webjars/bootstrapvalidator/0.5.3/css/bootstrapValidator.min.css" rel="stylesheet">
<!-- 		<link href="css/main.css" rel="stylesheet"> -->
		<script src="webjars/jquery/2.1.1/jquery.min.js"></script>
		<script src="webjars/bootstrapvalidator/0.5.3/js/bootstrapValidator.min.js"></script>
		<script src="webjars/bootstrap/3.3.1/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/ajax.js"></script>
	</head>
	<body>
		<div id="navtop">
  			<div class="navbar navbar-custom navbar-static-top">
    			<div class="container">
     				<div class="navbar-collapse collapse">        
       					<ul class="nav pull-right navbar-nav">
       						<li>
			            		<form class="navbar-form" action="signin.action" method="get">
			            			<input type="hidden" name="signout" value="true">
			            			<button type="submit" class="btn btn-primary">Выход [${email}]</button>
			            		</form>
       						</li>
        				</ul>
      				</div>		
    			</div>
  			</div>
		</div>
	
		<c:forEach var="entry" items="${usersIO}">
			${entry.getValue().getOwner().getName()}
		</c:forEach>
		
		<div class="container" align="center" role="form">
			<form class="setup" action="join_game.action" method="get">
				<select id="player_color" class="form-control" name="playerColor" title="playerColor" title="Выберите цвет вашей фишки" required>
					<option style="color: black;" value="" selected>Выберите цвет вашей фишки</option>
					<option style="color: aqua;" value="aqua">Aqua</option>
					<option style="color: black;" value="black">Black</option>
					<option style="color: blue;" value="blue">Blue</option>
					<option style="color: fuchsia;" value="fuchsia">Fuchsia</option>
					<option style="color: gray;" value="gray">Gray</option>
					<option style="color: green;" value="green">Green</option>
					<option style="color: lime;" value="lime">Lime</option>
					<option style="color: maroon;" value="maroon">Maroon</option>
					<option style="color: navy;" value="navy">Navy</option>
					<option style="color: olive;" value="olive">Olive</option>
					<option style="color: orange;" value="orange">Orange</option>
					<option style="color: purple;" value="purple">Purple</option>
					<option style="color: red;" value="red">Red</option>
					<option style="color: silver;" value="silver">Silver</option>
					<option style="color: teal;" value="teal">Teal</option>
					<option style="color: yellow;" value="yellow">Yellow</option>
				</select>
				<input type="hidden" name="isJoinToGame" value="true">
				<button class="btn btn-lg btn-primary btn-block" type="submit">
					Присоединиться к игре
				</button>
			</form>
		</div>
	</body>
	<script type="text/javascript">
		function done() {
			updateJoinedPlayers();
			checkJoinStatus();
			setTimeout(function() {
				done();// call done() again
			}, 100);// refresh it accordingly
		}

		$(document).ready(function() {
			done();
		});
	</script>
</html>