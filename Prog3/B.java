/*
Write a Java program using user-defined storage classes to create a book database and
store it into a List. Each entry in the list should include title, author, publisher and price
of the book. Sort the books in ascending order of price and print them. Maintain the book
details with respect to a unique book id. Prompt for an author name and list all the books
with the same author’s name. Create a new list holding all the book details with price
greater than a user specified price.
Additional: Create a front-end using swings to accept book details
*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class Bookss{
	static int nextId=1;
	int id;
	String title;
	String author;
	String pub;
	double price;
	Bookss(String title,String author,String pub,double price){
		this.id=nextId++;
		this.title=title;
		this.author=author;
		this.pub=pub;
		this.price=price;
	}
	int getId() {
		return id;
	}
	String getTitle() {
		return title;
	}
	String getAuthor() {
		return author;
	}
	String getPub() {
		return pub;
	}
	double getPrice() {
		return price;
	}
	public String toString() {
		return "Book Id:"+id+" Title:"+title+" Author:"+author+" Publisher:"+pub+" Price:"+price;
	}
}
class BookDatabases{
	List<Bookss> b;
	BookDatabases(){
		this.b=new ArrayList<>();
	}
	void addBook(Bookss n) {
		b.add(n);
		}
	void sortBookss() {
		Collections.sort(b,(b1,b2)->Double.compare(b1.getPrice(), b2.getPrice()));
		System.out.println("Bookss sorted by price");
		for(Bookss x:b) {
			System.out.println(x);
		}
	}
	void disAuth(String a) {
		System.out.println("Displaying by author:"+a);
		for(Bookss x:b) {
			if(x.getAuthor().equalsIgnoreCase(a)) {
				System.out.println(x);
			}
		}
	}
	List<Bookss> abvpr(double m) {
		System.out.println("Displaying Bookss of price greater than:"+m);
		List<Bookss> res=new ArrayList<>();
		for(Bookss x:b) {
			if(x.getPrice()>m) {
				res.add(x);
			}
		}
		return res;
	}
}
class BFrame extends JFrame{
	JTextField titleF,authF,pubF,priceF;
	JButton addBut,sortBut,authBut,priceBut;
	BookDatabases db;
	BFrame(){
		System.out.print("a");
		setTitle("Book Database");
		setSize(400,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		titleF=new JTextField(20);
		authF=new JTextField(20);
		pubF=new JTextField(20);
		priceF=new JTextField(20);
		addBut=new JButton("Add Book");
		sortBut=new JButton("Sort Bookss");
		authBut=new JButton("By author");
		priceBut=new JButton("By price");
		db=new BookDatabases();
		addBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				addBook();
			}
		});
		sortBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db.sortBookss();
			}
		});
		authBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String x=JOptionPane.showInputDialog("Enter author name");
				db.disAuth(x);
			}
		});
		priceBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double x=Double.parseDouble(JOptionPane.showInputDialog("Enter minimum price"));
				List<Bookss> res=db.abvpr(x);
				System.out.println("Bookss greater than price "+x);
				for(Bookss y:res) {
					System.out.println(y);
				}
			}
		});
		setLayout(new GridLayout(6,2));
		add(new JLabel("Title:"));
		add(titleF);
		add(new JLabel("Author:"));
		add(authF);
		add(new JLabel("Publisher:"));
		add(pubF);
		add(new JLabel("Price:"));
		add(priceF);
		add(addBut);
		add(sortBut);
		add(authBut);
		add(priceBut);	
	}
	void addBook() {
		String t=titleF.getText();
		String a=authF.getText();
		String p=pubF.getText();
		double m=Double.parseDouble(priceF.getText());
		Bookss nb=new Bookss(t,a,p,m);
		db.addBook(nb);
		JOptionPane.showMessageDialog(this,"Book inserted successfully");
		
	}
}
public class B {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				new BFrame().setVisible(true);
			}
		});

	}

}

