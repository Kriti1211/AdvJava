<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
	pageEncoding="ISO-8859-1"%>

<%@page import="java.sql.DriverManager" %>
<%@page import="java.sql.*,java.util.*" %>
<%
String first_name=request.getParameter("fname");
String last_name=request.getParameter("lname");
String ageParam = request.getParameter("age");
int age = 0; // Default value, you can change this to suit your requirements

if (ageParam != null && !ageParam.isEmpty()) {
    try {
        age = Integer.parseInt(ageParam);
    } catch (NumberFormatException e) {
        // Handle the case where the parameter cannot be parsed as an integer
        e.printStackTrace(); // Log the exception or take appropriate action
    }
}

String city_name=request.getParameter("cname");
String email=request.getParameter("email");
try
{
Class.forName("com.mysql.cj.jdbc.Driver");
Connection conn =(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/person", "root","");
PreparedStatement st=conn.prepareStatement("insert into personal values(?,?,?,?,?)");
st.setString(1,first_name);
st.setString(2,last_name);
st.setInt(3,age);
st.setString(4,city_name);
st.setString(5,email);
int i=st.executeUpdate();
Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery("select * from personal;");
while(rs.next())
{
System.out.println(rs.getString(1));
System.out.println(rs.getString(2));
System.out.println(rs.getInt(3));
System.out.println(rs.getString(4));
System.out.println(rs.getString(5));
}
out.println("Data is successfully inserted!");
}
catch(Exception e)
{
System.out.print(e);
e.printStackTrace();
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form method="post" action= "Process.jsp">
First Name:<br>
<input type="text" name="fname">
<br>
Last Name:<br>
<input type="text" name="lname">
<br>
Age:<br>
<input type="text" name="age">
<br>
City Name:<br>
<input type="text" name="cname">
<br>
Email id:<br>
<input type="text" name="email">
<br>
<input type="submit" value="submit">
</form>
</body>
</html>
/*
create table personal( fname varchar(20),lname varchar(20), age int(2), cname varchar(20),email varchar(20));
*/
