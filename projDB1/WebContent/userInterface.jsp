<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<style><%@include file="cssScript.css"%></style>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Interface</title>
</head>
<body>
	<h1><strong>User Interface</strong></h1>
	
		
		
		<h2>Access Tweet Interface</h2>
			<a href="tweetInterface.jsp" style="text-decoration: none"; ><button>Tweet Interface</button></a>
		
		<%@page import="java.sql.DriverManager"%>
		<%@page import="java.sql.ResultSet"%>
		<%@page import="java.sql.Statement"%>
		<%@page import="java.sql.Connection"%>
		
		<%
		String name = (String)session.getAttribute("user"); 
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
		<h2 align="center"><font><strong>Your Transaction Activity</strong></font></h2>
		<table align="center" cellpadding="5" cellspacing="5" border="1">
		<tr>
		
		</tr>
		<tr bgcolor="#A52A2A">
		<td><b>Sender Email</b></td>
		<td><b>Receiver Email</b></td>
		<td><b>Time</b></td>
		<td><b>PPS Amount</b></td>
		<td><b>USD Amount</b></td>
		<td><b>Type of Transaction</b></td>
		<td><b>Transaction ID</b></td>
		</tr>
		<%
		try{ 
		connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testdb?"
		          + "useSSL=false&user=john&password=pass1234");
		statement=connection.createStatement();
		String sql ="SELECT * FROM transactions where SenderEmail = \""+ name +"\" OR ReceiverEmail = \""+name+"\"";
		
		resultSet = statement.executeQuery(sql);
		while(resultSet.next()){
		%>
		<tr bgcolor="#DEB887">
		
		<td><%=resultSet.getString("SenderEmail") %></td>
		<td><%=resultSet.getString("ReceiverEmail") %></td>
		<td><%=resultSet.getString("timeOfTrans") %></td>
		<td><%=resultSet.getString("PPSAmount") %></td>
		<td><%=resultSet.getString("USDAmount") %></td>
		<td><%=resultSet.getString("typeofTrans") %></td>
		<td><%=resultSet.getString("transactionID") %></td>
		
		</tr>
		
		<% 
		}
		
		} catch (Exception e) {
		e.printStackTrace();
		}
		%>
		</table>
		
			
	

	
	<form action="buyPPS" method="get">
		<h2>Buy or Sell</h2>
		<p>Value of PPS: $0.01 per share</p>
		
		Shares to Buy: <input type="text" placeholder="Enter Amount of PPS Shares" name="ppsShares"><br>
		<button type="submit" value="Buy">Buy PPS from Root</button><br>
		<h3 style = "color: red"> ${resultBuy}</h3>
		
	</form>
	<form action="sellPPS" method="get">
		Shares to Sell: <input type="text" placeholder="Enter Amount of PPS Shares" name="ppsSharesSell"><br>
		<button type="submit" value="Sell">Sell PPS to Root</button><br>
		<h3 style = "color: red"> ${resultSell}</h3><br>
	</form>
	
	
</body>
</html>