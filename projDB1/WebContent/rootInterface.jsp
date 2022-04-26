<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<style><%@include file="cssScript.css"%></style>
<head>
<meta charset="ISO-8859-1">
<title>Root Interface</title>
</head>
<body>
	<h2 align="center"><font><strong>Reset Database</strong></font></h2>
	<form action="initialize" method="get">
		<button type="submit" value="Initialize Database">Initialize Database</button><br>
		
		<h3 style = "color: green"> ${ins}</h3>
	</form>
	<h2 align="center"><font><strong>Statistics of Users</strong></font></h2>
	
	<form action="bigInfluencers" method="get">
		<button type="submit" value="Big Influencers">List Big Influencers</button><br>
	</form>
	<form action="bigWhales" method="get">
		<button type="submit" value="Big Whales">List Big Whales</button><br>
	</form>
	<form action="frequentBuyers" method="get">
		<button type="submit" value="Frequent Buyers">List Frequent Buyers</button><br>
	</form>
	<form action="goodFollowers" method="get">
		<button type="submit" value="Good Followers">List Good Followers</button><br>
	</form>
	<form action="commonFollowers" method="get">
		<button type="submit" value="Common Followers">List Common Followers</button><br>
	</form>
	<form action="diamondHands" method="get">
		<button type="submit" value="Diamond Hands">List Diamond Hands</button><br>
	</form>
	<form action="paperHands" method="get">
		<button type="submit" value="Paper Hands">List Paper Hands</button><br>
	</form>
	<form action="goodInfluencers" method="get">
		<button type="submit" value="Good Influencers">List Good Influencers</button><br>
	</form>
	<form action="inactiveUsers" method="get">
		<button type="submit" value="Inactive Users">List Inactive Users</button><br>
	</form>
	<form action="statistics" method="get">
		<button type="submit" value="Statistics">List Statistics</button><br>
	</form>
	
	
	
	
</body>
</html>