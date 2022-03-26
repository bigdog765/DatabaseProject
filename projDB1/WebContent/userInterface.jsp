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
	<h1>user interface</h1>
	<form action="trans" method="get">
	<h2>Your Transaction Activity</h2>
		<button type="submit" value="Trans">Show your transactions</button><br>
		<ol>
			<li>
			<% String tF = (String)session.getAttribute("tranFrom");
			String tT = (String)session.getAttribute("tranTo");
			String tW = (String)session.getAttribute("tranWhen");
			String tI = (String)session.getAttribute("tranID");
			Double tP = (Double)session.getAttribute("tranpps");
			Double tU = (Double)session.getAttribute("tranusd");
			out.println(tF + ", "+ tT + ", "+ tW + ", " +tI + ", " +tP + ", " +tU);
			
			
			%>
			</li>
		</ol>
	</form>
</body>
</html>