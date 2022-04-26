<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<style><%@include file="cssScript.css"%></style>
<html>
<head>
<meta charset="ISO-8859-1">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1><strong>Post a Tweet</strong></h1>
    <p>
         Tweet: <input type="text" placeholder="Write your Tweet here" name="Tweet"> <br>
    </p>
	<form action="Post" method="get">
			<button type="submit" value="Post">Post</button><br>
			<a href="tweetInterface.jsp"> Back to Tweet Interface </a>
			<h3 style = "text-align:center"> ${TweetResult}</h3>
	</form>
</body>
</html>