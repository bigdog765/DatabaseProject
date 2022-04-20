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
	
		<a href="tweet.jsp" style="text-decoration: none"; ><button>Post A Tweet</button></a>
		
		
			<form action="trans" method="get">
			
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
				<table align="center" cellpadding=auto cellspacing="5" border="1">
				<tr>
				
				</tr>
				<tr bgcolor="#FFFAF0">
				
				<td><b>Email</b></td>
				<td><b>Number of Likes</b></td>
				<td><b>Content</b></td>
				<td style="width:40%"><b></b></td></td>
				</tr>
				<%
				try{ 
				connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testdb?" + "useSSL=false&user=john&password=pass1234");
				statement=connection.createStatement();
				String sql ="SELECT * FROM Post";
				
				resultSet = statement.executeQuery(sql);
				while(resultSet.next()){
				%>
				<tr bgcolor="#DEB887">
				
				<td><%=resultSet.getString("Email") %></td>
				<td><%=resultSet.getString("numOfLikes") %></td>
				<td><%=resultSet.getString("content") %></td>
				<td><button class="Like">Like</button></td>

				</tr>
				
				<% 
				}
				
				} catch (Exception e) {
				e.printStackTrace();
				}
				%>
				</table>	
		
		
		
		
		
		
		<a href="userInterface.jsp"> Back to User Interface </a>
		
</body>
</html>