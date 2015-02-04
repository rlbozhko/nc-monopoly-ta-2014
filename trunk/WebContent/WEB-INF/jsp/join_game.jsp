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
		<input type="submit" value="Join to game">`
	</form>
	<form><input type=button value="Refresh" onClick="window.location.reload()"></form> 
</body>
</html>