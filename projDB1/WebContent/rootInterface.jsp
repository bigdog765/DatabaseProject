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
<%@page import="java.sql.DriverManager"%>
	<%@page import="java.sql.ResultSet"%>
	<%@page import="java.sql.Statement"%>
	<%@page import="java.sql.Connection"%>
	
	<%
	String driverName = "com.mysql.jdbc.Driver";
	String name = (String)session.getAttribute("user"); 
	
	try {
	Class.forName(driverName);
	} catch (ClassNotFoundException e) {
	e.printStackTrace();
	}
	
	Connection connection = null;
	Statement statement = null;
	ResultSet rs1 = null;
	ResultSet rs2 = null;
	
	
	try{ 
	connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testdb?" + "useSSL=false&user=john&password=pass1234");
	statement=connection.createStatement();
	String sql ="SELECT Email FROM user";
	
	rs1 = statement.executeQuery(sql);
	
	
	%>
	
	<h2 align="center"><font><strong>Reset Database</strong></font></h2>
	<form action="initialize" method="get">
		<button type="submit" value="Initialize Database">Initialize Database</button><br>
		
		<h3 style = "color: green"> ${ins}</h3>
	</form>
	<h2 align="center"><font><strong>Statistics of Users</strong></font></h2>
	
	
	
	
	<form action="bigInfluencers" method="get">
		<button type="submit" value="Big Influencers">List Big Influencers</button><br>
		<h3 style = "color: green"> ${bI}</h3>
	</form>
	<form action="bigWhales" method="get">
		<button type="submit" value="Big Whales">List Big Whales</button><br>
		<h3 style = "color: green"> ${bW}</h3>	
	</form>
	<form action="frequentBuyers" method="get">
		<button type="submit" value="Frequent Buyers">List Frequent Buyers</button><br>
		<h3 style = "color: green"> ${fB}</h3>	
	</form>
	<form action="goodFollowers" method="get">
		<button type="submit" value="Good Followers">List Good Followers</button><br>
		<h3 style = "color: green"> ${gF}</h3>	
	</form>
	<form action="commonFollowers" method="get">
		<button type="submit" value="Common Followers">List Common Followers</button><br>
		<center>
		<p>Select Users:
		<select>
		
		<%
		while(rs1.next())
		{
			String u1 = rs1.getString("Email"); %>
			<option name= dd1 value="<%=u1 %>"><%=u1 %></option><%	

		}
		rs1.close();rs2 = statement.executeQuery(sql);
		%>
		</select>
		<select>
		
		<%
		while(rs2.next())
		{
			String u2 = rs2.getString("Email"); 
			%>
			<option name= dd2 value="<%=u2 %>"><%=u2 %></option>		
			
			<%
		}
		%>
		</select>
		</p>
		</center>

	</form>
	<form action="diamondHands" method="get">
		<button type="submit" value="Diamond Hands">List Diamond Hands</button><br>
		<h3 style = "color: green"> ${dH}</h3>
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
	<% 
	} catch (Exception e) {
		e.printStackTrace();
				}%>
	
	
	
	
</body>
</html>