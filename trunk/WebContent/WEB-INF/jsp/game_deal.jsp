<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div id="deal_body">
	<div class="form-group">
		<p><b>Сделка с ${targetPlayer}</b></p>	
		<label class="control-label">Требовать деньги </label><input class="form-control" type="number" min="0" name="askMoney" value="0">
		<label class="control-label">Предложить деньги </label> <input class="form-control" type="number" min="0" name="giveMoney" value="0">
	</div>
	<c:if test="${targetProperty.size() > 0}">
		<div class="form-group">
			<p><b>Требовать собственность</b></p>
			<select class="form-control" name="askPropertiesIDs" multiple="multiple">
				<c:forEach items="${targetProperty}" var="each">
					<option value="${each.getPosition()}">${each.getName()}</option>
				</c:forEach>
			</select>
		</div>
	</c:if>
	<c:if test="${sourceProperty.size() > 0}">
		<div class="form-group">
			<p><b>Предложить собственность</b></p>
			<select class="form-control" name="givePropertiesIDs" multiple="multiple">
				<c:forEach items="${sourceProperty}" var="each">
					<option value="${each.getPosition()}">${each.getName()}</option>
				</c:forEach>
			</select>
		</div>
	</c:if>
	<button onclick="createDeal(true,'${targetPlayer}')" class="btn btn-info">Отправить Сделу</button>
	<button onclick="createDeal(false,'${targetPlayer}')" class="btn btn-info">Отмена</button>
</div>
