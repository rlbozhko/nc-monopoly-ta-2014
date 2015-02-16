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
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script type="text/javascript" src="js/ajax.js"></script>
</head>
<body>
	<a href="signin.action?signout">Sign out [${email}] </a>
		
	<h1>Game</h1>
	<table>
		<tbody>
			<tr>
				<td style="width: 10%; vertical-align: top;">
					<div id="player_info"></div>					
				</td>
				<td style="width: 55%;">
					<div id="board"></div>
				</td>
				<td style="vertical-align: top;">
					<div id="chatbox"></div>					
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
		updateButtons();
		updateChat();
		updateYesNoDialog();
		updateWarning();
		updateSelectProperty();
		updateDeal();
		updateSelectPlayer();
		setTimeout(function() {
			done();// call done() again
		}, 100);// refresh it accordingly
	}

	$(document).ready(function() {
		done();
	});
</script>
</html>