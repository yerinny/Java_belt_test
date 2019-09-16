<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri= "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Edit idea</title>
</head>
<body>
	<h1>Edit <c:out value="${idea.content}"/> </h1>

	 <form:errors path="ideaObj.*"/>
	<form:form action="/ideas/${idea.id}/edit" method="POST" modelAttribute="idea">
    <input type="hidden" name="_method" value="put"/>    
    <p>
        <form:label path="content">Name</form:label>
        <form:input path="content"/>
    </p>  
    <input type="submit" value="Submit"/>
	</form:form>    
	
	<br>
	<form action="/ideas/${idea.id}/delete" method="post">
		<input type="hidden" name="_method" value="delete" />
		<input type="submit" Value="Delete"/>
	</form>
	
	

</body>
</html>