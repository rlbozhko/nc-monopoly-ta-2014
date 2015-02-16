<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div id="select_property_body" class="form-group" style="float: left;">	
	<select id="select_property_choice" name="propertiesIDs" class="form-control" style="margin-bottom: 5px;">
		<option value="" disabled selected> Выберите Собственность</option>
		<c:forEach items="${propertyList}" var="each">
			<option value="${each.getPosition()}">${each.getName()} <c:if test="${each.isPledged()}">Заложена</c:if></option>
		</c:forEach>
	</select>
	<button onclick="selectProperty(true)" class="btn btn-info">Выбрать</button>
	<button onclick="selectProperty(false)" class="btn btn-info">Отмена</button>
</div>