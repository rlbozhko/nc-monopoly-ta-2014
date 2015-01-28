<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Monopoly - index</title>
</head>
<body>

	<a href="signin.action?signout">Sign out [${email}] </a>

	<h1>Server is Full</h1>
	<p><a href="index.action">retry</a></p>
</body>
</html>