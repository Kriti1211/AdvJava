import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Prog6 extends JFrame{
	JTextField name,age,fam,addr;
	JRadioButton male,female;
	JComboBox<String> payScales;
	
	Prog6(){
		setTitle("Employee info");
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
		JLabel n=new JLabel("Name:");
		name=new JTextField(20);
	
		JLabel a=new JLabel("Age:");
		age=new JTextField(20);
		
		JLabel f=new JLabel("Family Members:");
		fam=new JTextField(20);
		
		JLabel ad=new JLabel("Address:");
		addr=new JTextField(20);
		
		JLabel gender=new JLabel("Gender:");
		ButtonGroup bg=new ButtonGroup();
		male=new JRadioButton("Male");
		female=new JRadioButton("Female");
		bg.add(male);
		bg.add(female);
		
		JLabel pay=new JLabel("PayScales:");
		String p[]= {"Low","medium","high"};
		payScales=new JComboBox<>(p);
		
		JButton sub=new JButton("Submit");
		sub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				done();
			}
		});
		
		add(n); add(name);
		add(a); add(age);
		add(f); add(fam);
		add(ad); add(addr);
		add(gender);  add(male); add(female);
		add(pay);  add(payScales);
		add(sub);
		setVisible(true);
	}
	void done() {
		try {
		String x=name.getText();
		int p=Integer.parseInt(age.getText());
		int q=Integer.parseInt(fam.getText());
		String y=addr.getText();
		String z=male.isSelected()?"male":"female";
		String w=payScales.getSelectedItem().toString();
		
		if(p>22 && p<60) {
			String msg= "Name:"+x+" Age:"+p+" Family members:"+q+" Address:"+y+" of gender:"+z+
					" has payscale:"+w;
			JOptionPane.showMessageDialog(this, msg,"Employee Data",JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			JOptionPane.showMessageDialog(this, "Invalid age");
		}
		}
		catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Please enter numerical values");
		}
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Prog6();
			}
		});
	}
}
