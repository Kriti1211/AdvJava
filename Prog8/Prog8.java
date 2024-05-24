/*
Write a program that uses Java Swing and JDBC to create a stand-alone application:
Create two tables namely, Representative (RepNo, RepName, State, Comission, Rate) and
Customer (CustNo, CustName, State, Credit_Limit, RepNo) in MySQL database.
Use appropriate Swing components to insert values in a form.
Display in frame those customers and representatives belonging to a particular state.
*/
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class Prog8 extends JFrame implements ActionListener{
	JTextField repNoTF,repNameTF,repStateTF,comTF,rateTF,custNoTF,custNameTF,custStateTF,credlimTF;
	JButton insertRepBtn,insertCustBtn,disBtn;
	JTextArea dis;
	Connection c;
	Prog8(){
		setTitle("Customer and Rep");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new GridLayout(0,2));
		add(new JLabel("Rep No:"));
		repNoTF=new JTextField(10);
		add(repNoTF);
		add(new JLabel("Rep Name:"));
		repNameTF=new JTextField(20);
		add(repNameTF);
		add(new JLabel("Rep State:"));
		repStateTF=new JTextField(20);
		add(repStateTF);
		add(new JLabel("Commision:"));
		comTF=new JTextField(20);
		add(comTF);
		add(new JLabel("Rate:"));
		rateTF=new JTextField(10);
		add(rateTF);
		add(new JLabel("Cust No:"));
		custNoTF=new JTextField(20);
		add(custNoTF);
		add(new JLabel("Cust Name:"));
		custNameTF=new JTextField(10);
		add(custNameTF);
		add(new JLabel("Credit limit:"));
		credlimTF=new JTextField(10);
		add(credlimTF);
		add(new JLabel("Cust State:"));
		custStateTF=new JTextField(10);
		add(custStateTF);
		insertRepBtn = new JButton("Insert Rep");
		insertCustBtn = new JButton("Insert Cust");
		disBtn = new JButton("Display by state");
		add(insertRepBtn);
		add(insertCustBtn);
		add(disBtn);
		
		insertRepBtn.addActionListener(this);
		insertCustBtn.addActionListener(this);
		disBtn.addActionListener(this);
		dis = new JTextArea();
		add(dis);
		c=connectDB();
		pack();
		setVisible(true);
	
	}
	Connection connectDB() {
		Connection c=null;
		try {
		String db="jdbc:mysql://localhost:3306/ReDb";
		String root="root";
		String pwd="january@1";
		c=DriverManager.getConnection(db,root,pwd);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return c;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Prog8();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==insertRepBtn) {
			insertRep(c, repNoTF.getText(), repNameTF.getText() ,repStateTF.getText(),comTF.getText(),rateTF.getText());
		}
		else if(e.getSource()==insertCustBtn) {
			insertCust(c,custNoTF.getText(),custNameTF.getText() , custStateTF.getText(),credlimTF.getText(),repNoTF.getText());
		}
		else if(e.getSource()==disBtn) {
			display(c,custStateTF.getText());
		}
	}
	void insertRep(Connection c,String repNo,String repName,String repState,String com,String rate){
		try {
			String q="insert into Rep values (?,?,?,?,?)";
			PreparedStatement ps=c.prepareStatement(q);
			ps.setString(1, repNo);
			ps.setString(2, repName);
			ps.setString(3, repState);
			ps.setString(4, com);
			ps.setString(5, rate);
			int x=ps.executeUpdate();
			if(x>0) {
				JOptionPane.showMessageDialog(this, "Rep insert success");
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Rep insert not success");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	void insertCust(Connection c,String custNo,String custName,String custState,String cred,String repNo) {
		try {
			String q="insert into Cust values(?,?,?,?,?)";
			PreparedStatement ps=c.prepareStatement(q);
			ps.setString(1, custNo);
			ps.setString(2, custName);
			ps.setString(3, custState);
			ps.setString(4, cred);
			ps.setString(5, repNo);
			int x=ps.executeUpdate();
			if(x>0) {
				JOptionPane.showMessageDialog(this, "Cust insert success");
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Cust insert not success");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	void display(Connection c,String state) {
		try {
			String q="select * from Cust where state =? union select * from Rep where state=?";
			PreparedStatement ps=c.prepareStatement(q);
			ps.setString(1, state);
			ps.setString(2, state);
			ResultSet rs=ps.executeQuery();
			dis.setText(null);
			while(rs.next()) {
				String info="ID:"+rs.getString("custNo")+" Name:"+rs.getString("custName")+
						" State:"+rs.getString("state")+" Rep No:"+rs.getString("repNo")+"\n";
				dis.append(info);
			}
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
/*
For database
create table Rep(repNo integer(10) primary key,repName varchar(20),state varchar(20),com varchar(20),rate integer(5));
create table Cust(custNo integer(10) primary key,custName varchar(20),state varchar(20),cred integer(5),repNo integer(10));
*/
