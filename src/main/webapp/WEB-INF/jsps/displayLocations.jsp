<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Display Locations</title>
</head>
<body>
	<h2>Display Locations</h2>
	<table>
		<tr>
			<th>id</th>
			<th>name</th>
			<th>code</th>
			<th>type</th>
		</tr>
		<c:forEach items="${locations}" var="location">
			<tr>
				<td>${location.id}</td>
				<td>${location.code}</td>
				<td>${location.name}</td>
				<td>${location.type}</td>
				<td><a href="deleteLocation?id=${location.id}">Delete</a></td>
				<td><a href="showUpdate?id=${location.id}">Edit</a></td>
			</tr>
		</c:forEach>
	</table>
	<a href="showCreate">Add Location</a>
</body>
</html>