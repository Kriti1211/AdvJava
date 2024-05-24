/*
Write Java program to create a front end using swings to. Accept login details such as
login id , name and password. On the click of the submit button add this to a database.
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class Prog7 extends JFrame implements ActionListener{
	JLabel jlb1=new JLabel("Login ID:");
	JTextField id=new JTextField(10);
	JLabel jlb2=new JLabel("Name:");
	JTextField name=new JTextField(10);
	JLabel jlb3=new JLabel("Password:");
	JTextField p=new JTextField(10);
	JButton btnclk=new JButton("Click to insert to DB");
	Prog7(String title){
		super(title);
		setLayout(new GridLayout(0,2));
		add(jlb1);   add(id);
		add(jlb2);	 add(name);
		add(jlb3);	 add(p);
		add(btnclk);
		btnclk.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String db="jdbc:mysql://localhost:3306/LDb";
			String root="root";
			String pass="";
			Connection c= (Connection)DriverManager.getConnection(db,root,pass);
			Statement stmt;
			stmt=c.createStatement();
			String v1=id.getText();
			String v2=name.getText();
			String v3=p.getText();
			String q="insert into login values("+Integer.parseInt(v1)+",'"+v2+"','"+v3+"');";
			stmt.executeUpdate(q);
			ResultSet rs=stmt.executeQuery("select * from login");
			while(rs.next()) {
				System.out.println(rs.getInt(1));
				System.out.println(rs.getString(2));
				System.out.println(rs.getString(3));
			}
		}
		catch(ClassNotFoundException ae) {
			ae.printStackTrace();
		}
		catch(SQLException be) {
			be.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Prog7 l=new Prog7("Details");
		l.setVisible(true);
		l.setSize(300,400);
	}

}
