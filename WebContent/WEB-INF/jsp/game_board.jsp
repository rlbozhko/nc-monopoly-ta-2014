<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:forEach var="eachCell" items="${cellsList}">
	<div
		style="height: 150px; width: 109px; float: left; overflow: hidden; border: 1px solid black; margin-bottom: 10px; margin-left: 10px;"
		align="center">
		<div style="border: 1px solid black; background: ${eachCell.getColor()}; font-weight: 700;">${eachCell.getName()}</div>
		<div>${eachCell.getDescription()}</div>
		<c:forEach var="eachPlayer" items="${activePlayers}">
			<c:choose>
				<c:when test="${eachCell.getPosition() == eachPlayer.getPosition()}">
					<div
						style="background: ${eachPlayer.getPlayerColor()};  width: 20%; float: left; margin-left: 5px;">&nbsp</div>
				</c:when>
			</c:choose>
		</c:forEach>
	</div>
</c:forEach>
