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
	<form action="trans" method="get">
	<h2>Your Transaction Activity</h2>
		<button type="submit" value="Trans">Show your transactions</button><br>
			
<% 
				 
				 
			if((int)session.getAttribute("tuples") == 0){
				for(int i = 0; i < (int)session.getAttribute("numOfTrans"); i++){
					String tF = (String)session.getAttribute("tranFrom["+ Integer.toString(i) +"]");
				String tT = (String)session.getAttribute("tranTo["+ Integer.toString(i) +"]");
				String tW = (String)session.getAttribute("tranWhen["+ Integer.toString(i) +"]");
				String tI = (String)session.getAttribute("tranID["+ Integer.toString(i) +"]");
				Double tP = (Double)session.getAttribute("tranpps["+ Integer.toString(i) +"]");
				Double tU = (Double)session.getAttribute("tranusd["+ Integer.toString(i) +"]");
				String tG = (String)session.getAttribute("tranType["+ Integer.toString(i) +"]");
				 out.print(tF + "\t");
				 out.print(tT + "\t");
				 out.print(tW + "\t");
				 out.print(tI + "\t");
				 out.print(tP + "\t");
				 out.print(tU + "\t");
				 out.print(tG + "\t");
				 %> <br> <%
				}
				session.setAttribute("tuples", 0);
			}
			
					
			
%>		
	</form>
	
	<form action="buyPPS" method="get">
		<h2>Buy or Sell</h2>
		<p>Value of PPS: $0.01</p>
		Shares to Buy: <input type="text" placeholder="Enter Amount of PPS Shares" name="ppsShares"> <br>
		<button type="submit" value="Buy">Buy PPS from Root</button><br>
		<h3 style = "color: red"> ${resultBuy}</h3>
		
	</form>
	<form action="sellPPS" method="get">
		Shares to Sell: <input type="text" placeholder="Enter Amount of PPS Shares" name="ppsSharesSell"> <br>
		<button type="submit" value="Sell">Sell PPS to Root</button><br>
		<h3 style = "color: red"> ${resultSell}</h3><br>
	</form>
	
	<h2>Access Tweet Interface</h2>
			<a href="tweetInterface.jsp" style="text-decoration: none"; ><button>Tweet Interface</button></a>
</body>
</html>