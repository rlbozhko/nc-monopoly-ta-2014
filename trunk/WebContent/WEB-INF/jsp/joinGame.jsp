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

	${sessionStatus} ${isJoinToGame}

	<c:forEach var="each" items="${usersList}">
		${each.getName()}
	</c:forEach>

	<form action="joinGame.action" method="get">
		<input type="text" name="playerName" value="${playerName}" autofocus required> 
		<input type="hidden" name="isJoinToGame" value="true"> 
		<input type="submit" value="Join to game">`
	</form>
</body>
</html>