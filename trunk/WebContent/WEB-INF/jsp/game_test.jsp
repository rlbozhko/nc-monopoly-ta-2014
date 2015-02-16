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
			.navbar {
			    margin-bottom:0;
  				background-color: #205081;
			}
			.form-group {
				 width: 200px;
			}
		</style>
		<script type="text/javascript" src="js/ajax.js"></script>
		<link href="webjars/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet">
		<link href="webjars/bootstrapvalidator/0.5.3/css/bootstrapValidator.min.css" rel="stylesheet">
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
						<div id="player_info"></div>					
					</td>
					<td style="width: 55%; background-color: #F5FFFA;">
						<div id="board"></div>
					</td>
					<td style="vertical-align: top; background-color: #FFFACD;">
						<div id="chatbox"></div>
						<div id="timer"></div>					
						<div id="buttons"></div>
						<div id="yn_dialog"></div>
						<div id="warning"></div>
						<div id="select_player"></div>
						<div id="deal"></div>
						<div id="select_property"></div>
						<div id="test"></div>					
					</td>
				</tr>
			</tbody>
		</table>
	</body>
	
	<script type="text/javascript">
		function done() {
			checkGameStatus();
			updatePlayerInfo();
			updateBoard();
			updateChat();
			updateTimer()
			updateButtons();		
			updateYesNoDialog();
			updateWarning();
			updateSelectProperty();
			updateDeal();
			updateSelectPlayer();
			setTimeout(function() {
				done();// call done() again
			}, 200);// refresh it accordingly
		}
	
		$(document).ready(function() {
			done();
		});
	</script>
</html>