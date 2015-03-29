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
				background: #FFFFF0;
				height: 170px;
				border: 1px solid black;
				overflow: auto;
			}
			.navbar {
			    margin-bottom:0;
  				background-color: #205081;
			}
		</style>
		<link href="webjars/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet">
		<link href="webjars/bootstrapvalidator/0.5.3/css/bootstrapValidator.min.css" rel="stylesheet">
<!-- 		<link href="css/main.css" rel="stylesheet"> -->
		<script src="webjars/jquery/2.1.1/jquery.min.js"></script>
		<script src="webjars/bootstrapvalidator/0.5.3/js/bootstrapValidator.min.js"></script>
		<script src="webjars/bootstrap/3.3.1/js/bootstrap.min.js"></script>
	</head>
	<body style="background-color: #FFFAF0;">
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
	
		<h1>Game</h1>
		<table>
			<tbody>
				<tr>
					<td style="width: 10%; vertical-align: top; background-color: #FFFACD;">
						<div style="position:relative; height:100%;">
							<c:forEach var="each" items="${players}">
								<div style="border: 3px solid ${each.getPlayerColor()}; margin-bottom: 20px;">
										<p>Игрок: ${each.getName()}</p>
										<p>Состояние: ${each.getMoney()}</p>
										<p>Местонахождение: ${cellsList.get(each.getPosition()).getName()}</p>
										<p>Собственность: ${propertyManager.getPlayerProperties(each)}</p>
								</div>		
							</c:forEach>
						</div>
				   	</td>
				   	<td style="width: 55%; background-color: #F5FFFA;">
				   		<c:forEach var="eachCell" items="${cellsList}">
							 <div	style="height: 100px; width: 109px; float: left; overflow: hidden; border: 1px solid black; margin-bottom: 10px; margin-left: 10px" align="center">
								<div style="border: 1px solid black;">${eachCell.getName()}</div>
									<div>${eachCell.getDescription()}</div>
									<c:forEach var="eachPlayer" items="${players}">
										<c:choose>
											<c:when test="${eachCell.getPosition() == eachPlayer.getPosition()}">
												<div style="background: ${eachPlayer.getPlayerColor()}; width: 20%; float: left; margin-left: 5px;">&nbsp;</div>
											</c:when>
										</c:choose>	
									</c:forEach>
							</div>
						</c:forEach>
				   	</td>
					<td style="vertical-align: top; background-color: #FFFACD;">
						<div id="chatbox">
							<c:forEach var="each" items="${messageQueue}">${each}<br></c:forEach>
						</div>

						<div>
							<form action="action_type.action" method="get">
								<button type="submit" name="actionType" value="WAIT"
									<c:if test="${!strActions.contains('WAIT')}">disabled="disabled"</c:if>>Wait</button>
								<button type="submit" name="actionType" value="START_TURN"
									<c:if test="${!strActions.contains('START_TURN')}">disabled="disabled"</c:if>>Start	turn</button>
								<button type="submit" name="actionType" value="END_TURN"
									<c:if test="${!strActions.contains('END_TURN')}">" disabled="disabled"</c:if>>End turn</button>
								<button type="submit" name="actionType" value="BUY_PROPERTY"
									<c:if test="${!strActions.contains('BUY_PROPERTY')}">disabled="disabled"</c:if>>Buy</button>		
								<button type="submit" name="actionType" value="DEAL"
									<c:if test="${!strActions.contains('DEAL')}">disabled="disabled"</c:if>>Deal</button>
								<button type="submit" name="actionType" value="FINISH_GAME"
									<c:if test="${!strActions.contains('FINISH_GAME')}">disabled="disabled"</c:if>>FINISH_GAME</button>
								<button type="submit" name="actionType" value="PAY_RENT"
									<c:if test="${!strActions.contains('PAY_RENT')}">disabled="disabled"</c:if>>PAY_RENT</button>
								<button type="submit" name="actionType" value="PLEDGE_PROPERTY"
									<c:if test="${!strActions.contains('PLEDGE_PROPERTY')}">disabled="disabled"</c:if>>PLEDGE_PROPERTY</button>
								<button type="submit" name="actionType" value="PAY_BACK"
									<c:if test="${!strActions.contains('PAY_BACK')}">disabled="disabled"</c:if>>PAY_BACK</button>
								<button type="submit" name="actionType" value="ESCAPE"
									<c:if test="${!strActions.contains('ESCAPE')}">disabled="disabled"</c:if>>ESCAPE</button>
								<button type="submit" name="actionType" value="PAY_BAIL"
									<c:if test="${!strActions.contains('PAY_BAIL')}">disabled="disabled"</c:if>>PAY_BAIL</button>
								<button type="submit" name="actionType" value="SERVE_JAIL_TERM"
									<c:if test="${!strActions.contains('SERVE_JAIL_TERM')}">disabled="disabled"</c:if>>SERVE_JAIL_TERM</button>
								<button type="submit" name="actionType" value="BUILD"
									<c:if test="${!strActions.contains('BUILD')}">disabled="disabled"</c:if>>BUILD</button>
								<button type="submit" name="actionType" value="UPGRADE_BUILDING"
									<c:if test="${!strActions.contains('UPGRADE_BUILDING')}">disabled="disabled"</c:if>>UPGRADE_BUILDING</button>
								<button type="submit" name="actionType" value="SELL_BUILDING"
									<c:if test="${!strActions.contains('SELL_BUILDING')}">disabled="disabled"</c:if>>SELL_BUILDING</button>
								<button type="submit" name="actionType" value="BETRAYAL"
									<c:if test="${!strActions.contains('BETRAYAL')}">disabled="disabled"</c:if>>BETRAYAL</button>	
										
							</form>
							<c:if test="${activePlayers.size() == 0 }">
								<form action="game_over.action" method="get">
									<input type="submit" value="GAME_OVER">
								</form>
							 </c:if>
							 <c:if test="${hasYesNoDialog == true}">
							 	<form id='dialog' name='dialog' action='dialog.action' method='GET'>
            						<input id="isAnswer" type="hidden" name='isAnswer' value="${hasYesNoDialog}">
        						</form>
        					 </c:if>
						</div>
			
						<c:if test="${selectPlayerRequest}">
							<div>
								<form action="select_player.action"  method="get">
									<select name="selectedPlayerName">
										<c:forEach items="${selectabelPlayers}" var="each">
											<option value="${each.getName()}">${each.getName()}</option>
										</c:forEach>
									</select>
									<button type="submit">Select</button>
								</form>
							</div>
						</c:if>
			
						<c:if test="${dealRequest}">
							<div>
								<form action="deal_target.action"  method="get">				
									Ask money <input type="text" name="askMoney" value="0"><br>
									Give money <input type="text" name="giveMoney" value="0"><br>
									<p>Ask property</p>				
									<select name="askPropertiesIDs" multiple="multiple">
										<c:forEach items="${targetProperty}" var="each">
											<option value="${each.getPosition()}">${each.getName()}</option>
										</c:forEach>
									</select>
									<br>
									<p>Give property</p>
									<select name="givePropertiesIDs" multiple="multiple">
										<c:forEach items="${sourceProperty}" var="each">
											<option value="${each.getPosition()}">${each.getName()}</option>
										</c:forEach>
									</select>
									<br>					
									<button type="submit" name="dealTargetName" value="${targetPlayer}">Send Deal</button>
								</form>
							</div>
						</c:if>		
						
						<c:if test="${hasSelectPropertyRequest}">
							<div>
								<form action="pledge_property.action"  method="get">				
									<p>Your properties</p>				
									<select name="propertyId">
										<c:set var="count" value="0"/>
										<c:forEach items="${propertyList}" var="each">
											<option value="${count}">${each.getName()}</option>
											<c:set var="count" value="${count + 1}" />
										</c:forEach>
									</select>
									<button type="submit">Select</button>
								</form>
							</div>
						</c:if>
					</td>
				</tr>
			</tbody>
		</table>
	</body>
	<script type="text/javascript">
	$( document ).ready(function() {
		
		if ($("#isAnswer").val() == 'true') {
			if(confirm('${yesNoDialog}')) {
		    	$("#dialog").submit();
		    } else {
		    	$("#isAnswer").val("false");
		    	$("#dialog").submit();
		    }
		}
		
		var isWarring = ${isWarning};
		if (isWarring == true) {
			alert('${warningMessage}');
		}
		
		alert('${strActions}');
	});
	
   		document.getElementById("chatbox").scrollTop = document.getElementById("chatbox").scrollHeight
 	</script>
</html>