<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Interface</title>
</head>
<body>
	<h1><strong>User Interface</strong></h1>
	<form action="trans" method="get">
	<h2>Your Transaction Activity</h2>
		<button type="submit" value="Trans">Show your transactions</button><br>

			<% 
			if(session.getAttribute("numOfTrans") == null){}
			else{
				%><table border="1">
					<tr>
					    <th width = "200px">From</th>
					    <th width = "200px">To</th>
					    <th width = "200px">When</th>
					    <th width = "200px">ID</th>
					    <th width = "100px">PPS Amount</th>
					    <th width = "100px">USD Amount</th>
					</tr><%
				for(int i = 0; i < (int)session.getAttribute("numOfTrans"); i++){
					String tF = (String)session.getAttribute("tranFrom["+ Integer.toString(i) +"]");
				String tT = (String)session.getAttribute("tranTo["+ Integer.toString(i) +"]");
				String tW = (String)session.getAttribute("tranWhen["+ Integer.toString(i) +"]");
				String tI = (String)session.getAttribute("tranID["+ Integer.toString(i) +"]");
				Double tP = (Double)session.getAttribute("tranpps["+ Integer.toString(i) +"]");
				Double tU = (Double)session.getAttribute("tranusd["+ Integer.toString(i) +"]");
				 %>
				 <tr>
					<td><% out.print(tF);%></td>
					<td><% out.print(tT);%></td>
					<td><% out.print(tW);%></td>
					<td><% out.print(tI);%></td>
					<td><% out.print(tP);%></td>
					<td><% out.print(tU);%></td>
				</tr>
				<%
	
				}
			}

			%></table>
			
		
	</form>
	
	<form action="buyPPS" method="get">
		<h2>Buy or Sell</h2>
		<p>Value of PPS: $0.01</p>
		Shares to Buy: <input type="text" placeholder="Enter Amount of PPS Shares" name="ppsShares"> <br>
		<button type="submit" value="Buy">Buy PPS from Root</button><br>
		<h3 style = "color: red"> ${result}</h3><br>
		
	</form>
	<form action="sellPPS" method="get">
		<button type="submit" value=Sell">Sell PPS to Root</button><br>
	</form>
</body>
</html>