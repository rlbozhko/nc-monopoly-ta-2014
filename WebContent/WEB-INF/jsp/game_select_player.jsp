<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div id="select_player_body" class="form-group" style="float: left;">
	<select class="form-control" name="selectedPlayerName">
		<c:forEach items="${selectabelPlayers}" var="each">
			<option value="" selected disabled>Выберите игрока</option>
			<option value="${each.getName()}">${each.getName()}</option>
		</c:forEach>
	</select>
</div>
<button onclick="selectPlayer(true)" class="btn btn-primary">Выбрать</button>
	<button onclick="selectPlayer(false)" class="btn btn-primary">Отмена</button>


