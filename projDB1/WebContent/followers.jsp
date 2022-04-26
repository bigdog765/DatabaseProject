<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style><%@include file="cssScript.css"%></style>
<meta charset="ISO-8859-1">
<title>Followers</title>
</head>
<body>
	<h1><strong>Your Followers</strong></h1>
	

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
ResultSet resultSet = null;
%>
<h2 align="center"><font><strong>Followers</strong></font></h2>
<table align="center" cellpadding=auto cellspacing="5">

<tr bgcolor="#FFFAF0">

<td><b></b></td>
<td><b></b></td>
<td  width = '0px'><b></b></td>
<td style="width:0%"><b></b></td>

<%
try{ 
	connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testdb?" + "useSSL=false&user=john&password=pass1234");
	statement=connection.createStatement();
	String sql ="SELECT * FROM Follows WHERE followerEmail = \""+ name +"\"";
	
	resultSet = statement.executeQuery(sql);
	
	int[] array = new int[50];
	int i = 0;
		while(resultSet.next()){
			
		%>
		<tr bgcolor="#add8e6" height = "60px">
		
		<td><%=resultSet.getString("followeeEmail") %>
		<td width= "200px">
			<% array[i] = i;%>
			<form action="tipUser" method="get">
			<%out.print("<button type='submit' id= 'tip' name= 'tip' value='" + resultSet.getString("followeeEmail") + "'>Tip</button>");%>			
				
			</form>	
		</td>	
		</tr>
		<% i++;				
		}
					
}
catch (Exception e) {
	e.printStackTrace();
}
%>
	
</body>
</html>