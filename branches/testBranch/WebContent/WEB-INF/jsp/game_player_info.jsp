<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div style="position: relative; height: 100%;">
	<c:forEach var="each" items="${players}">
		<div style="border: 3px solid ${each.getPlayerColor()}; margin-bottom: 20px;">
			<p align="center" style="font-weight: 700;">${each.getName()}</p>
			<p>Состояние: ${each.getMoney()}</p>
			<p>Местонахождение: ${cellsList.get(each.getPosition()).getName()}</p>
			<p>Собственность: ${propertyManager.getPlayerProperties(each)}</p>
		</div>
	</c:forEach>
</div>
