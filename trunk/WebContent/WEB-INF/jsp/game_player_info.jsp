<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div style="position: relative; height: 100%;">
	<c:forEach var="each" items="${players}">
		<div
			style="border: 2px solid ${each.getPlayerColor()}; margin-bottom: 20px; padding: 0px;">
			Player: ${each.getName()} <br> Money: ${each.getMoney()} <br>
			Position: ${cellsList.get(each.getPosition()).getName()} <br>
			Property: ${propertyManager.getPlayerProperties(each)}
		</div>
	</c:forEach>
</div>
