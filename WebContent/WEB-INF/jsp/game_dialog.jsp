<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div id="yn_dialog_body">
	<p>${yesNoDialog}</p>	
	<button onclick="yesNoDialog(true)" class="btn btn-info">Да</button>
	<button onclick="yesNoDialog(false)" class="btn btn-info">Нет</button>	
</div>

