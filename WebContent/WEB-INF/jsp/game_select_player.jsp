<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div id="select_player_body">
	<p>Выберите Игрока</p>
	<select name="selectedPlayerName">
		<c:forEach items="${selectabelPlayers}" var="each">
			<option value="${each.getName()}">${each.getName()}</option>
		</c:forEach>
	</select>
	<button onclick="selectPlayer(true)">Выбрать</button>
	<button onclick="selectPlayer(false)">Отмена</button>
</div>



