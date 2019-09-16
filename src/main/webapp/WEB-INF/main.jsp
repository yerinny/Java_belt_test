<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body>

	<div class="error">
	 <p><form:errors path="user.*"/></p>
     <p><c:out value="${success}" /></p>
     <p><c:out value="${error}" /></p>
   
	</div>
	
	<div class="register">
	 <div class="col-md-2">
    	<h1>Register</h1>
    
    	<form:form method="POST" action="/register" modelAttribute="user">
    		<p>
            	<form:label path="firstName">First Name:</form:label>
            	<form:input path="firstName"/>
        	</p>
        	<p>
            	<form:label path="lastName">Last Name:</form:label>
            	<form:input path="lastName"/>
        	</p>
        	<p>
            	<form:label path="email">Email:</form:label>
            	<form:input path="email"/>
        	</p>
        	<p>
            	<form:label path="password">Password:</form:label>
            	<form:password path="password"/>
        	</p>
       		<p>
            	<form:label path="passwordConfirmation">Password Confirmation:</form:label>
            	<form:password path="passwordConfirmation"/>
       		</p>
        		<input type="submit" value="Register"/>
    	</form:form>
    
     </div>
    </div>
    
    
  	<div class="login">
  		<div class="col-md-2">
   			 <h1>Login</h1>

    <form method="post" action="/login">
        <p>
            <label for="email">Email:</label>
            <input type="text" id="email" name="email"/>
        </p>
        <p>
            <label for="password">Password</label>
            <input type="password" id="password" name="password"/>
        </p>
        <input type="submit" value="Login"/>
    </form>    

    </div>
    </div>
    
</body>
</html>