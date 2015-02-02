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

	<form>
		<input type=button value="Refresh" onClick="history.go()">
	</form>

	<h1>Game</h1>

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

	<div>
		<%-- <c:forEach items="${actions}" var="action">
			<p>${actions.contains(ActionsType.valueOf('WAIT').create)}</p>			
			<p>${ActionsType.valueOf('WAIT').create}</p>
			<p>Loop</p>
			<p>${strActions.contains('WAIT')}</p>
			<p>${strActions.contains('notcontains')}</p>
		</c:forEach>
		 --%>
		<p>${players}</p>

		<form action="test_game.action" method="get">
			<button type="submit" name="actionType" value="WAIT"
				<c:if test="${!strActions.contains('WAIT')}">disabled="disabled"</c:if>>Wait</button>
			<button type="submit" name="actionType" value="START_TURN"
				<c:if test="${!strActions.contains('START_TURN')}">disabled="disabled"</c:if>>Start
				turn</button>
			<button type="submit" name="actionType" value="DEAL"
				<c:if test="${!strActions.contains('DEAL')}">disabled="disabled"</c:if>>Deal</button>
		</form>

	</div>
	
	<p>selectPlayerRequest = ${selectPlayerRequest}</p>
	
	<c:if test="${selectPlayerRequest}">
		<div>
			<form action="test_game.action"  method="get">
				<select name="selectedPlayerName">
					<c:forEach items="${selectabelPlayers}" var="cursor">
						<option value="${cursor.getName()}">${cursor.getName()}</option>
					</c:forEach>
				</select>
				<button type="submit">Select</button>
			</form>
		</div>
	</c:if>
	
	<c:if test="${dealRequest}">
		<div>
			<form action="test_game.action"  method="get">				
				Ask money <input type="text" name="askMoney" value="0"><br>
				Give money <input type="text" name="giveMoney" value="0"><br>
				<p>Ask property</p>				
				<select name="askPropertiesIDs" multiple="multiple">
					<c:forEach items="${targetProperty}" var="cursor">
						<option value="${cursor.getPosition()}">${cursor.getName()}</option>
					</c:forEach>
				</select>
				<br>
				<p>Give property</p>
				<select name="givePropertiesIDs" multiple="multiple">
					<c:forEach items="${sourceProperty}" var="cursor">
						<option value="${cursor.getPosition()}">${cursor.getName()}</option>
					</c:forEach>
				</select>
				<br>					
				<button type="submit" name="dealTargetName" value="${targetPlayer}">Send Deal</button>
			</form>
		</div>
	</c:if>

</body>
</html>