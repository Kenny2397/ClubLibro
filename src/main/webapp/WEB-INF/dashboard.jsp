<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1>WELCOME, <c:out value="${user.getFirst_name()}"/></h1>
		<h3>Books for everyone´s shelves</h3>
		<a href="/logout" class="btn btn-danger">Logout</a>
		<a href="/books/new">+ Add a to my shelf!</a>
		
		<div>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Id</th>
						<th>Title</th>
						<th>Author Name</th>
						<th>Posted By</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="book" items="${books}">
						<tr>
							<td><c:out value="${book.getId()}"/></td>
							<td><a href="books/${book.getId()}"><c:out value="${book.getTitle()}"/></a></td>
							<td><c:out value="${book.getAuthor()}"/></td>
							<td><c:out value="${book.getPublisher().getFirst_name()}"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		
		</div>
	</div>
	
</body>
</html>