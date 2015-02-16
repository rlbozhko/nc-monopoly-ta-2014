<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div id="deal_body">
	<div class="form-group">
	<p class="nav nav-bar">Сделка с ${targetPlayer}</p>	
	<label class="control-label">Требовать деньги </label><input class="form-control" type="text" name="askMoney" value="0">
	<label class="control-label">Предложить деньги </label> <input class="form-control" type="text" name="giveMoney" value="0">
	</div>
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
	<button onclick="createDeal(true,'${targetPlayer}')" class="btn btn-info">Отправить Сделу</button>
	<button onclick="createDeal(false,'${targetPlayer}')" class="btn btn-info">Отмена</button>
</div>
