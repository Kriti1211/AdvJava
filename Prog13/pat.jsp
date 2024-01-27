  <%@page import="java.sql.*,java.util.*" %>
<!DOCTYPE html>
<html>
<head>

<title>Insert title here</title>
</head>
<body>
<form action = "P12" method="post">
Patient ID: <input type="text" name="pid"><br>
Patient Name: <input type="text" name="pname"><br>
Age: <input type="text" name="age"><br>
Date of Admission: <input type="text" name="dateOf"><br>
Cause of Admission: <input type="text" name="causeOf"><br>
Doctor Diagnosed: <input type="text" name="doc"><br>
Treatment Proposed: <input type="text" name="treat"><br>
<input type="submit" value="Click">
<h2>Existing records</h2>
<table border="1">
<tr>
<th>Patient ID</th>
<th>Patient name</th>
<th>Age</th>
<th>date of ad</th>
<th> cause of ad</th>
<th> doctor</th>
<th>treatment</th>

</tr>
<%
try {
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection c=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/A1","root","january@1");
	Statement stmt=c.createStatement();
	ResultSet rs=stmt.executeQuery("select * from B1");
	while(rs.next()){
		out.println("<tr>");
		out.println("<td>"+rs.getInt("pid")+"</td>");
		out.println("<td>"+rs.getString("pname")+"</td>");
		out.println("<td>"+rs.getInt("age")+"</td>");
		out.println("<td>"+rs.getString("dateOf")+"</td>");
		out.println("<td>"+rs.getString("causeOf")+"</td>");
		out.println("<td>"+rs.getString("doc")+"</td>");
		out.println("<td>"+rs.getString("treat")+"</td>");
		out.println("</tr>"); 
	}
	rs.close();
	c.close();
	stmt.close();
}
catch(Exception e){
	e.printStackTrace();
}
%>
</table>
</form>
</body>
</html>
