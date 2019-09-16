<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri= "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Ideas</title>
</head>
<body>
<h1>Add a new idea</h1>
 <form:errors path="ideaObj.*"/>
 
 <form:form action="/ideas/new" method="POST" modelAttribute="ideaObj">
    	<p>
            <form:label path="content">Content:</form:label>
            <form:input path="content"/>
        </p>
        <input type="submit" value="Create"/>
    </form:form>

</body>
</html>