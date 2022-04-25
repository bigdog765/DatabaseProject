<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<style><%@include file="cssScript.css"%></style>
<head>
<meta charset="ISO-8859-1">
<title>Compose a Comment</title>
</head>
<body>

	<form action="PostComment" method="get">			
	Write a comment: <input type="text" style="width:80%" placeholder="Enter" name="comment">
	
	<button class="PostComment" type = 'submit'>Post</button> <br>
	
	<a href="tweetInterface.jsp"> Back to Tweet </a>
	<h3 style = "text-align:center"> ${CommentResult}</h3>
	</form>

</body>
</html>