<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<style><%@include file="cssScript.css"%></style>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tweet Interface</title>
</head>
<body>


		
		
	<h1><strong>Tweet Interface</strong></h1>
	
		
		<div id="outer">
        	<div class="inner"><a href="tweet.jsp"><button class="Post">Post A Tweet</button></a></div>
        	<div class="inner"><a href="followers.jsp"><button class="SeeFollowers">See Your Followers</button></a></div>
        	
    	</div> 

				<%@page import="java.sql.DriverManager"%>
				<%@page import="java.sql.ResultSet"%>
				<%@page import="java.sql.Statement"%>
				<%@page import="java.sql.Connection"%>
				
				<%
				String driverName = "com.mysql.jdbc.Driver";
				
				try {
				Class.forName(driverName);
				} catch (ClassNotFoundException e) {
				e.printStackTrace();
				}
				
				Connection connection = null;
				Statement statement = null;
				ResultSet resultSet = null;
				%>
				<h2 align="center"><font><strong>Tweets</strong></font></h2>
				<table align="center" cellpadding=auto cellspacing="5">

				<tr bgcolor="#FFFAF0">

				<td><b></b></td>
				<td><b></b></td>
				<td  width = '80px'><b></b></td>
				<td style="width:40%"><b></b></td>
				
				<%
				try{ 
				connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testdb?" + "useSSL=false&user=john&password=pass1234");
				statement=connection.createStatement();
				String sql ="SELECT * FROM Post";
				
				resultSet = statement.executeQuery(sql);
				while(resultSet.next()){
				%>
				<tr bgcolor="#add8e6" height = "100px">
				
				<td><%=resultSet.getString("Email") %><button class="Follow">Follow this user</button></td>
				
				<td><%=resultSet.getString("content") %></td>
				<td><%=resultSet.getString("numOfLikes") %> Likes </td>
				
				
				
				<td><button id= "Like">Like</button><button class="Comment" >Comment</button></td>

				</tr>
				
				<% 
				}
				
				} catch (Exception e) {
				e.printStackTrace();
				}
				%>

		<a href="userInterface.jsp"> Back to User Interface </a>
		
		
</body>
</html>