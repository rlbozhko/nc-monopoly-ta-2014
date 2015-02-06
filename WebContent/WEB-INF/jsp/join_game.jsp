<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Join game</title>
</head>
<body>
	<a href="signin.action?signout">Sign out [${email}] </a>

	<c:forEach var="entry" items="${usersIO}">
		${entry.getValue().getOwner().getName()}
	</c:forEach>

	<form action="join_game.action" method="get">
		<input type="hidden" name="isJoinToGame" value="true">
			Select your color:
			<select id="player_color" name="playerColor" title="playerColor">
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
				<option selected="selected" style="color: yellow;" value="yellow">Yellow</option>
			</select>
		<input type="submit" value="Join to game">`
	</form>

	<form><input type=button value="Refresh" onClick="window.location.reload()"></form> 
</body>
</html>