<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
	<style>
			.navbar {
			    margin-bottom:0;
  				background-color: #205081;
			}
			.setup {
				width: 250px;
			}
	</style>
		<meta charset="UTF-8">
		<title>Monopoly - index</title>
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
			<div class="container" align="center" role="form">
				<form class="setup" action="join_game.action" method="post">
					<select style="color: black;" id="playernumber" class="form-control" name="countPlayers" title="Количество ожидаемых игроков" required>
						<option value="" selected>Выберите количество игроков</option>
						<option value="2">В игру сыграют 2 игрока</option>
						<option value="3">В игру сыграют 3 игрока</option>
						<option value="4" >В игру сыграют 4 игрока</option>
						<option value="5">В игру сыграют 5 игроков</option>
					</select>
					<input class="form-control" type="text" name="startMoney"	value="${startMoney}" required placeholder="Укажите стартовый капитал">
					
					<input type="hidden" name="sessionStatusText" value="CREATING"> 
					<button class="btn btn-lg btn-primary btn-block" type="submit">
						Создать игру
				    </button>
				</form>
			</div>
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