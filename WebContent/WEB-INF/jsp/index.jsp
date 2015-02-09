<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Monopoly - index</title>
</head>
<body>

	<a href="signin.action?signout">Sign out [${email}] </a>
		
		<form action="join_game.action" method="post">
			<div id="setup">
				Enter number of players.
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
</html>