<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Monopoly - index</title>
		<style type="text/css">
			#chatbox {
				text-align: left;
				margin: 0 auto;
				margin-bottom: 25px;
				padding: 10px;
				background: #fff;
				height: 170px;
				border: 1px solid black;
				overflow: auto;
			}
		</style>
	</head>
	<body>
		<a href="signin.action?signout">Sign out [${email}] </a>
	
		<form>
			<input type=button value="Refresh" onClick="history.go()">
		</form>
	
		<h1>Game</h1>
	
		<table>
			<tbody>
				<tr>
					<td style="width: 10%; border: 1px solid black; vertical-align: top;">
						<div style="position:relative; height:100%;">
							<c:forEach var="each" items="${players}">
								<div style="border: 1px solid black; margin-bottom: 20px; padding: 0px;">
										Player: ${each.getName()} <br>
										Money: ${each.getMoney()} <br>
										Position: ${cellsList.get(each.getPosition()).getName()} <br>
										Property: ${propertyManager.getPlayerProperties(each)}
								</div>		
							</c:forEach>
						</div>
				   	</td>
				   	<td style="width: 57%; border: 1px solid black;">
				   		<c:forEach var="each" items="${cellsList}">
							<div	style="height: 100px; width: 109px; float: left; overflow: hidden; border: 1px solid black; margin-bottom: 10px; margin-left: 10px" align="center">
								<div style="border: 1px solid black;">${each.getName()}</div>
								<div>${each.getDescription()}</div>
							</div>
						</c:forEach>
				   	</td>
					<td style="vertical-align: top;">
						<div id="chatbox">
							<c:forEach var="each" items="${messageQueue}">${each}<br></c:forEach>
						</div>

						<div>
							<form action="game.action" method="get">
								<button type="submit" name="actionType" value="WAIT"
									<c:if test="${!strActions.contains('WAIT')}">disabled="disabled"</c:if>>Wait</button>
								<button type="submit" name="actionType" value="START_TURN"
									<c:if test="${!strActions.contains('START_TURN')}">disabled="disabled"</c:if>>Start	turn</button>
								<button type="submit" name="actionType" value="END_TURN"
									<c:if test="${!strActions.contains('END_TURN')}">disabled="disabled"</c:if>>End turn</button>
								<button type="submit" name="actionType" value="BUY_PROPERTY"
									<c:if test="${!strActions.contains('BUY_PROPERTY')}">disabled="disabled"</c:if>>Buy</button>		
								<button type="submit" name="actionType" value="DEAL"
									<c:if test="${!strActions.contains('DEAL')}">disabled="disabled"</c:if>>Deal</button>
								<button type="submit" name="actionType" value="FINISH_GAME"
									<c:if test="${!strActions.contains('FINISH_GAME')}">disabled="disabled"</c:if>>FINISH_GAME</button>
							</form>
						</div>
			<%-- 		<p>selectPlayerRequest = ${selectPlayerRequest}</p> --%>
			
						<c:if test="${selectPlayerRequest}">
							<div>
								<form action="game.action"  method="get">
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
								<form action="game.action"  method="get">				
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
					</td>
				</tr>
			</tbody>
		</table>
	</body>
</html>