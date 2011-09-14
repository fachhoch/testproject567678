<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	
	<%
		
	 	HashMap<String,String> map= (HashMap)request.getAttribute("linksMap");
	%>
	<table>
	<tr>
		<th>
			Name
		</th>
		<th>
			Link
		</th>
	</tr>
	<%
		for(String  string :map.keySet()){
			
		
	%>
		<tr>
			<td>
				<%=string %>
			</td>
			<td>
				<a href="<%=map.get(string)%>"><%=map.get(string)%></a>
			</td>
		</tr>
		
	<%} %>
	</table>
</body>
</html>