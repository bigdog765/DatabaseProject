<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tip this user</title>
<style><%@include file="cssScript.css"%></style>
</head>
<body>
<form action="Tip" method="get">
		<h2>Tip this user some PPS</h2>
		
		Enter the amount you wish to tip: <input type="text" placeholder="Enter PPS" name="tipAmount"><br>
		<button type="submit" value="Complete">Complete</button><br>
		<h3 style = "color: red"> ${resultTip}</h3>
		
</form>
</body>
</html>