/*
Create a jsp page that accepts patient information in a hospital such as patient id, patient
name, and age, date of admission, cause of admission, doctor diagnosed, and treatment
proposed. Using servlet, place the details into a database. Allow options to insert, and view
the contents in the database.
*/
import java.io.*;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class PatientServlet
 */
public class P12 extends HttpServlet {
protected void doPost(HttpServletRequest request, HttpServletResponse
response)
throws ServletException, IOException {
// Retrieve form parameters
int pid = Integer.parseInt(request.getParameter("pid"));
String pname = request.getParameter("pname");
int age = Integer.parseInt(request.getParameter("age"));
String dateOf = request.getParameter("dateOf");
String causeOf = request.getParameter("causeOf");
String doc = request.getParameter("doc");
String treat = request.getParameter("treat");
try {
// Database connection details
String url = "jdbc:mysql://localhost:3306/A1";
String username = "root";
String password = "january@1";
Class.forName("com.mysql.cj.jdbc.Driver");
Connection connection = DriverManager.getConnection(url, username, password);
// Insert data into the database
PreparedStatement preparedStatement = connection.prepareStatement(
"INSERT INTO B1 VALUES (?, ?, ?, ?, ?, ?, ?)"
);
preparedStatement.setInt(1, pid);
preparedStatement.setString(2, pname);
preparedStatement.setInt(3, age);
preparedStatement.setString(4, dateOf);
preparedStatement.setString(5, causeOf);
preparedStatement.setString(6, doc);
preparedStatement.setString(7, treat);
preparedStatement.executeUpdate();
// Close resources
preparedStatement.close();
connection.close();
} catch (Exception e) {
e.printStackTrace();
}
// Redirect back to the JSP page
response.sendRedirect("pat.jsp");
}
}
/*
create table B1(pid int(5) primary key,pname varchar(20),age int(3),dateOf varchar(10),causeOf varchar(10),doc varchar(20),treat varchar(20));*/
