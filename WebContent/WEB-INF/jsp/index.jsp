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

	<c:if test="${sessionStatus eq 'NOT_EXISTS'}">
		<form action="index.action" method="get">
			<div id="setup">
				Enter number of players. <select id="playernumber"
					name="countPlayers" title="countPlayers">
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4" selected>4</option>
					<option value="5">5</option>
				</select>
			</div>
			${isJoinToGame} <input type="hidden" name="sessionStatusText"
				value="CREATING"> <input type="hidden" name="isCreator"
				value="true"> <input type="hidden" name="isJoinToGame"
				value="true"> <input type="text" name="playerName"
				value="${playerName}" autofocus required> <input
				type="submit" value="Create game">
		</form>
		<div>
			<c:forEach var="each" items="${players}">
					${each.getName()}
				</c:forEach>
		</div>
		<div style="border: 1px solid black;" align="center">
			<div style="width: 60%; border: 2px solid black;" align="center">
				<c:forEach var="each" items="${cellsList}">
					<div
						style="height: 100px; width: 109px; float: left; overflow: hidden; border: 1px solid black;">
						<div style="border: 1px solid black;">${each.getName()}</div>
						<div>${each.getDescription()}</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</c:if>
	<c:if test="${sessionStatus eq 'CREATING'}">
		${sessionStatus}
		${isJoinToGame}
		
		<c:forEach var="each" items="${usersList}">
			${each.getName()}
		</c:forEach>
		<div style="border: 1px solid black;" align="center">
			<div style="width: 60%; border: 2px solid black;" align="center">
				<c:forEach var="each" items="${cellsList}">
					<div
						style="height: 100px; width: 109px; float: left; overflow: hidden; border: 1px solid black;">
						<div style="border: 1px solid black;">${each.getName()}</div>
						<div>${each.getDescription()}</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<c:if test="${!isJoinToGame}">
			<form action="index.action" method="get">
				<input type="text" name="playerName" value="${playerName}" autofocus
					required> <input type="hidden" name="isJoinToGame"
					value="true"> <input type="submit" value="Join to game">`
			</form>
		</c:if>
	</c:if>

</body>
</html>