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
	
	<form action="index.action" method="get">
		<input type="hidden" name="playerName" value="${playerName}"> 
		<input type="hidden" name="isJoinToGame" value="true"> 
		<input type="submit" value="Refresh">
	</form>

	<h1>Waiting room</h1>
	
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

</body>
</html>