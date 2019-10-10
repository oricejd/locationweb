<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Display locations</title>
</head>
<body>
	<h3>Locations</h3>
	<table>
		<tr>
			<th>id</th>
			<th>code</th>
			<th>name</th>
			<th>type</th>
		</tr>

		<c:forEach items="${locations}" var="location">
			<tr>
				<%-- 				<td><c:out value="${location.id}" /></td>
				<td><c:out value="${location.code}" /></td>
				<td><c:out value="${location.name}" /></td>
				<td><c:out value="${location.type}" /></td> --%> 

				<td>${location.id}</td>
				<td>${location.code}</td>
				<td>${location.name}</td>
				<td>${location.type}</td>
				<td><a href="deleteLocation?id=${location.id}">Delete</a></td>
				<td><a href="showEdit?id=${location.id}">Edit</a></td>
			</tr>
		</c:forEach>
	</table>
	<a href="showCreate">Add Location</a>
</body>
</html>