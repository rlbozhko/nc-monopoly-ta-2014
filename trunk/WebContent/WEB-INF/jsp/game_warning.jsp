<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div id="warning_body">
	<p style="color: red;">${warningMessage}</p>	
	<button onclick="okWarning()" class="btn btn-danger">Ok</button>	
</div>