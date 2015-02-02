<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.monopoly.action.ActionType" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Game</title>

<style type="text/css">
body{
	margin:0;
	padding:0
}
div.table{
	height:0;
}
div.tr{
	height:0;
}

div.table[class] {height:auto; display: table;}
div.tr[class] { height:auto; display: table-row;}
div.td[class] {float:none; display: table-cell;}

div.td{
	height: 100%; 
 	float: left; 
}
#main{
	width:100%;
}
#first{
	position:relative;
	z-index:1;
	width:200px;
	background:#ccf;
}
#second{
	width:100%;
	background:#cfc;
	margin:0 -30% 0 -200px; 
	padding:0 30% 0 200px;
}

#second[id]{
	width:auto;
	margin:0;
	padding:0;
}
#third{
	width:30%;
	background:#fcc;
}
</style>


</head>
<body>

	<a href="signin.action?signout">Sign out [${email}] </a>	
	
	<h1>Game</h1>
<div id="main" class="table">
	<div class="tr">
		<div id="first" class="td">
			<c:forEach var="each" items="${players}">
				<div style="border: 1px solid black; margin-bottom: 20px; margin-left: 10px; margin-right: 20px;">
					Player: ${each.getName()} <br>
					Money: ${each.getMoney()} <br>
					Property: ${propertyManager.getPlayerProperties(each)}
				</div>		
			</c:forEach>
		</div>
		<div id="second" class="td">			
	        	<c:forEach var="each" items="${cellsList}">
						<div style="height: 100px; width: 109px; float: left; margin-bottom: 20px; margin-left: 10px; border: 1px solid black;">
						<div style="border: 1px solid black;">${each.getName()}</div>
						<div>${each.getDescription()}</div>
				</div>
			</c:forEach>
		</div>
		<div id="third" class="td">
			<input type="submit" value="Start turn"  <c:if test="${actionTypes.contains('START_TURN')}">disabled</c:if>>
			
		</div>
	</div>
</div>
</body>
</html>