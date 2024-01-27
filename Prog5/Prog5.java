import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Prog5 implements ActionListener{
	JLabel l1=new JLabel("Customer Phone");
	JTextField ph=new JTextField(10);
	JLabel l2=new JLabel("Enter Customer id");
	JTextField cid=new JTextField(10);
	JButton checkcid=new JButton("Check cid");
	JLabel l3=new JLabel("#1 Apple 150rs/kg  #2 Orange 60rs/kg");
	JLabel l4=new JLabel("Enter item number u want to purchase:");
	JTextField item_no=new JTextField(2);
	JLabel l5=new JLabel("Quantity in KGS");
	JTextField kg=new JTextField(3);
	JRadioButton rd1=new JRadioButton("Discount");
	JButton bill=new JButton("Bill");
	JTextField am=new JTextField(10);
	JTextArea jt1=new JTextArea("");
	HashMap<String,Integer> hm=new HashMap<>();
	HashMap<Integer,Double> item=new HashMap<>();
	static int nextId=124;
	void add_coll() {
		hm.put("9988776655", 121);
		hm.put("1234567890", 122);
		hm.put("8877665544", 123);
		
	}
	void add_item() {
		item.put(1, 150.0);
		item.put(2,60.0);
	}
	Prog5(){
		JFrame f1=new JFrame("Customer Info");
		add_coll();
		add_item();
			f1.setSize(800,600);
			f1.setVisible(true);
			f1.setLayout(new FlowLayout());
			f1.add(l1);
			f1.add(ph);
			f1.add(checkcid);
			f1.add(l2);
			f1.add(cid);
			checkcid.addActionListener(this);
			f1.add(l3);
			f1.add(jt1);
			jt1.setEditable(false);
			f1.add(l4);
			f1.add(item_no);
			f1.add(l5);
			f1.add(kg);
			f1.add(rd1);
			f1.add(bill);
			bill.addActionListener(this);
			f1.add(am);
	}
	public static void main(String[] args) {
		new Prog5();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==checkcid) {
			String p=ph.getText();
			if(hm.containsKey(p)) {
				JOptionPane.showMessageDialog(null, "Your old customer id:"+hm.get(p));
				cid.setText(Integer.toString(hm.get(p)));
				
			}
			else
			{
				hm.put(p, nextId++);
				JOptionPane.showMessageDialog(null,"Your new customer id:"+(nextId-1));
				cid.setText(Integer.toString(hm.get(p)));
				
			}
		}
		else if(e.getSource()==bill) {
			double total;
			int i=Integer.parseInt(item_no.getText());
			if(!item.containsKey(i)) {
				double p=Double.parseDouble(JOptionPane.showInputDialog("Enter price per kg"));
				item.put(i, p);
				jt1.append("#"+i+" "+p+" per kg");
			}
			else
			{
				double amt=item.get(Integer.parseInt(item_no.getText()));
				int q=Integer.parseInt(kg.getText());
				if(!rd1.isSelected()) {
					total=amt*q;
				}
				else
				{
					double dis=Double.parseDouble(JOptionPane.showInputDialog(null,"Enter discount"))/100;
					total=amt*q-amt*q*dis;
				}
				am.setText("Total cost:"+total);
				JOptionPane.showMessageDialog(null, "Item number:"+item_no.getText()+" and price:"+total);
			}
		}
	}
}
