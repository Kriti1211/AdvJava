/*
Write a program that uses Java Swing and JDBC to create a stand-alone application:
Create two tables namely, Patient and Medicine in MySQL database.
Use appropriate Swing components to insert values in a form for patient and medicine.
List the patient details taking a particular type of medicine and the type of treatment.
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class P10 extends JFrame implements ActionListener{
	JTextField pName,MType,Treat;
	JButton insertPat,insertMed,byMedBtn,byTreatBtn;
	Connection c;
	P10(){
		setTitle("Patient Medicine");
		add(new JLabel("Patient Name:"));
		pName=new JTextField(20);
		add(pName);
		
		add(new JLabel("Medicine type:"));
		MType=new JTextField(20);
		add(MType);
		
		add(new JLabel("Treatment:"));
		Treat=new JTextField(20);
		add(Treat);
		insertPat=new JButton("Insert patient");
		insertMed=new JButton("Insert medicine");
		byMedBtn=new JButton("By medicine");
		byTreatBtn=new JButton("By Treatment");
		
		insertPat.addActionListener(this);
		insertMed.addActionListener(this);
		byMedBtn.addActionListener(this);
		byTreatBtn.addActionListener(this);
		add(insertPat);
		add(insertMed);
		add(byMedBtn);
		add(byTreatBtn);
		setLayout(new GridLayout(0,2));
		setSize(400,300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		connectDb();
	}
	void connectDb() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c=DriverManager.getConnection("jdbc:mysql://localhost:3306/patDb","root","");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==insertPat) {
			inPat();
		}
		else if(e.getSource()==insertMed) {
			inMed();
		}
		else if(e.getSource()==byMedBtn) {
			getMed();
		}
		else if(e.getSource()==byTreatBtn) {
			getTreat();
		}
	}
	void inPat() {
		String n=pName.getText();
		String m=MType.getText();
		String t=Treat.getText();
		try {
		PreparedStatement ps=c.prepareStatement("insert into patient (name,type,treat) values (?,?,?)");
		ps.setString(1,n);
		ps.setString(2, m);
		ps.setString(3, t);
		ps.executeUpdate();
		JOptionPane.showMessageDialog(this, "patient success");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	void inMed() {
		String n=JOptionPane.showInputDialog(this,"Enter medicine name");
		
		try {
		PreparedStatement ps=c.prepareStatement("insert into Medicine (mname) values (?)");
		ps.setString(1,n);
		ps.executeUpdate();
		JOptionPane.showMessageDialog(this, "Medicine success");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	void getMed() {
		String x=JOptionPane.showInputDialog(this,"Enter medicine type:");
		try {
			PreparedStatement ps=c.prepareStatement("select * from patient where type=?");
			ps.setString(1, x);
			ResultSet rs=ps.executeQuery();
			System.out.println("By medicine:");
			while(rs.next()) {
				System.out.println("Patient ID:"+rs.getString("pid")+" patient Name:"+rs.getString("name")+
						" Medicine type:"+rs.getString("type")+
						" Treatment:"+rs.getString("treat"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	void getTreat() {
		String x=JOptionPane.showInputDialog(this,"Enter treatment type:");
		try {
			PreparedStatement ps=c.prepareStatement("select * from patient where treat=?");
			ps.setString(1, x);
			ResultSet rs=ps.executeQuery();
			System.out.println("By treatment:");
			while(rs.next()) {
				System.out.println("Patient ID:"+rs.getString("pid")+" patient Name:"+rs.getString("name")+
						" Medicine type:"+rs.getString("type")+
						" Treatment:"+rs.getString("treat"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new P10();
	}

}
/*
create table patient(pid int auto_increment primary key,name varchar(255),type varchar(255),treat varchar(255));
create table Medicine(mid int auto_increment primary key,mname varchar(255));
*/
