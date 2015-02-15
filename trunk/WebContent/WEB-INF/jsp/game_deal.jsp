<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div id="select_property_body">
	<p>Сделка с ${targetPlayer}</p>	
	Требовать деньги <input type="text" name="askMoney" value="0"><br>
	Предложить деньги <input type="text" name="giveMoney" value="0"><br>
	<p>Ask property</p>
	<select name="askPropertiesIDs" multiple="multiple">
		<c:forEach items="${targetProperty}" var="each">
			<option value="${each.getPosition()}">${each.getName()}</option>
		</c:forEach>
	</select> <br>
	<p>Give property</p>
	<select name="givePropertiesIDs" multiple="multiple">
		<c:forEach items="${sourceProperty}" var="each">
			<option value="${each.getPosition()}">${each.getName()}</option>
		</c:forEach>
	</select> <br>	
	<button onclick="createDeal(true,'${targetPlayer}')">Отправить Сделу</button>
	<button onclick="createDeal(false,'${targetPlayer}')">Отмена</button>
</div>
