<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.*,java.sql.*" %>
<%!
int genOrderNo(Connection c) throws SQLException{
	int orderNo=100;
	String q="select max(orderNo) from X1;";
	try(
			Statement stmt=c.createStatement();
			ResultSet rs=stmt.executeQuery(q)
			){
		if(rs.next()){
			int maxNo=rs.getInt(1);
			if (!rs.wasNull()) {
                orderNo = maxNo + 1;
		}
	}
}
	return orderNo;
}


%> 
<%
String array[]=request.getParameterValues("acc");
String tag=request.getParameter("tagline");
String pock=request.getParameter("pocket");
String col=request.getParameter("color");
String a=(array!=null)?String.join(",",array):"";
try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    String db = "jdbc:mysql://localhost:3306/Y1";
    try (Connection connection = DriverManager.getConnection(db, "root", "")) {
        // Insert data into TShirts table
        String insertQuery = "INSERT INTO X1 VALUES (?, ?, ?, ?, ?);";
        try (PreparedStatement ps = connection.prepareStatement(insertQuery)) {
            // Generate and set the OrderNo
            int orderNo = genOrderNo(connection);
            ps.setString(1, a);
            ps.setString(2, tag);
            ps.setString(3, pock);
            ps.setString(4, col);
            ps.setInt(5, orderNo);
            ps.executeUpdate();
        }

        // Display all records in tabular form
        String selectAllQuery = "SELECT * FROM X1;";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectAllQuery)) {
%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Order Details</h2>
    <table border="1">
        <tr>
            <th>Accessories</th>
            <th>Tagline</th>
            <th>Pocket Option</th>
            <th>Color</th>
            <th>OrderNo</th>
        </tr>
      <% while (resultSet.next()) { %>
            <tr>
                <td><%= resultSet.getString("Accessories") %></td>
                <td><%= resultSet.getString("Tagline") %></td>
                <td><%= resultSet.getString("PocketOption") %></td>
                <td><%= resultSet.getString("Color") %></td>
                <td><%= resultSet.getInt("OrderNo") %></td>
            </tr>
        <% } %>
         </table>
</body>
</html>
<%
            }
        }
    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
    }
%>
/*
create table X1(Accessories varchar(20),Tagline varchar(50),PocketOption varchar(20),Color varchar(20),OrderNo int(5) primary Key);
*/
