<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NEW BOOK</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1>Add a Books to your shelf!</h1>
		<a href="/dashboard" class="btn btn-success">back to the shelves</a>
		<div class="row">
			
				<form:form method="post" action="/books/create" modelAttribute="newBook">
					<form:hidden path="publisher" value="${user_session.getId()}"/>
					<div class="form-group">
						<form:label path="title">title:</form:label>
						<form:input path="title" class="form-control"/>
						<form:errors path="title" class="text-danger"/>
					</div>
					
					<div class="form-group">
						<form:label path="author">author:</form:label>
						<form:input type="text" path="author" class="form-control"/>
						<form:errors path="author" class="text-danger"/>
					</div>
					
					<div class="form-group">
						<form:label path="my_thoughts">my_thoughts:</form:label>
						<form:textarea cols="35" rows="15" path="my_thoughts" class="form-control"/>
						<form:errors path="my_thoughts" class="text-danger"/>
					</div>
										
					<input type="submit" value="Submit" class="btn btn-primary">
				</form:form>
			</div>
			
	</div>
	
</body>
</html>