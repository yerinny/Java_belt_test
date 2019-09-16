<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri= "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>   
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
	<title>Courses</title>
</head>
<body>
	<a href="/home">Main page</a>
	
	<h1><c:out value="${idea.content}" /></h1>
	
	<h3>Created By:</h3>
	<p><c:out value="${userIdea[0][2]}" /> <c:out value="${userIdea[0][3]}" /></p>
	<a href="/ideas/${idea.id}/edit">Edit</a>
	<br>
	<br>
	
</body>
</html>