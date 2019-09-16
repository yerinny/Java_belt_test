<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html>
<html>
<head>
	<style>
		table, th, td {
 		 border: 1px solid black;
 		 border-collapse: collapse;
 		 text-align: center;
		}
	</style>
	<meta charset="UTF-8">
	<title>Welcome</title>
</head>
<body>
	<a href="/logout">Logout</a>
	<h1>Welcome, <c:out value="${user.firstName}" /> <c:out value="${user.lastName}" /></h1>
	
	<h2>Courses</h2>
	
	<table>
		<tr>
			<th>Idea</th>
			<th>Created by:</th>
		</tr>
		<c:forEach var="row" items="${userIdea}">
		<tr>
			<td><a href="/ideas/${row[1]}"><c:out value="${row[0]}"/></a></td>
			<td><c:out value="${row[2]}"/> <c:out value="${row[3]}"/></td>
		</tr>
		</c:forEach>
	</table>
	
	<p><a href="/ideas/new">Add an idea</a></p>
</body>
</html>