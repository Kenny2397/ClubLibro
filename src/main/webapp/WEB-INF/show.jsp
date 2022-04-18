<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SHOW</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1><c:out value="${book.getTitle()}"/> </h1>
		<a href="/dashboard" class="btn btn-success">back to the shelves</a>
		
		<div class="form-group">
			<p>
				<i> <c:out value="${book.getPublisher().getFirst_name()}"/> </i> read 
				<c:out value="${book.getTitle()}"/> by 
				<c:out value="${book.getAuthor()}"/>
			</p>
		</div>
		
		<div>
			<p>
				Here  are
				<c:out value="${book.getPublisher().getFirst_name()}"/>´s
				thought:
				
			</p>
		</div>
		<hr>
			<p>
				<c:out value="${book.getMy_thoughts()}"/>
			</p>
		<hr>
		
		<a href="/books/${book.getId()}/edit" class="btn btn-danger">Edit</a>
	
	</div>
	

</body>
</html>