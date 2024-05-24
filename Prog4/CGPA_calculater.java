/*
Create a desktop java application using swings to enable a user to enter student
information such as name, usn, age, address, sgpa of 4 semesters, category. Perform
validations on age(>18 and <=30) and sgpa(range 0 to 10). Display appropriate messages
in pop up boxes to indicate wrong entries, on clicking of the “compute” button. Also find
the cgpa based on the obtained sgpa. On clicking of the “done” button, place the student
details in a hashmap with unique student id as key and student information as value. A
click on the “display” button should display the collection in a textarea in a separate
frame.
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
public class CGPA_calculater extends JFrame {
    private JTextField nameField, usnField, ageField, addressField, sgpaField1, sgpaField2, sgpaField3, sgpaField4;
    private JComboBox<String> categoryComboBox;
    private JTextArea displayArea;
    private HashMap<Integer, String> studentDetails = new HashMap<>();
    private int studentID = 1;
    public CGPA_calculater() {
        setTitle("Student Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600); 
       setLayout(new GridLayout(0,2));
        add(new JLabel("Name:"));
        nameField = new JTextField(20);
       add(nameField);
       add(new JLabel("USN:"));
        usnField = new JTextField(20);
        add(usnField);
        add(new JLabel("Age:"));
        ageField = new JTextField(20);
        add(ageField);
        add(new JLabel("Address:"));
        addressField = new JTextField(20);
        add(addressField);
        add(new JLabel("SGPA Semester 1:"));
        sgpaField1 = new JTextField(20);
       add(sgpaField1);
        add(new JLabel("SGPA Semester 2:"));
        sgpaField2 = new JTextField(20);
       add(sgpaField2);
        add(new JLabel("SGPA Semester 3:"));
        sgpaField3 = new JTextField(20);
        add(sgpaField3);
       add(new JLabel("SGPA Semester 4:"));
        sgpaField4 = new JTextField(20);
       add(sgpaField4);
         add(new JLabel("Category:"));
         String[] categories = {"Category A", "Category B", "Category C"};
         categoryComboBox = new JComboBox<>(categories);
        add(categoryComboBox);
        JButton computeButton = new JButton("Compute");
        computeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                computeButtonClicked();
            }
        });
        add(computeButton);
        JButton doneButton = new JButton("Done");
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doneButtonClicked();
            }
        });
        add(doneButton);
        JButton displayButton = new JButton("Display");
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayButtonClicked();
            }
        });
       add(displayButton);
        displayArea = new JTextArea(10, 20);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane);
        setVisible(true);
    }
   private void computeButtonClicked() {
        // Validation logic for age and SGPA
        int age;
        double sgpa1, sgpa2, sgpa3, sgpa4;
        try {
            age = Integer.parseInt(ageField.getText());
            sgpa1 = Double.parseDouble(sgpaField1.getText());
            sgpa2 = Double.parseDouble(sgpaField2.getText());
            sgpa3 = Double.parseDouble(sgpaField3.getText());
            sgpa4 = Double.parseDouble(sgpaField4.getText());

            if (age <= 18 || age > 30 || sgpa1 < 0 || sgpa1 > 10 || sgpa2 < 0 || sgpa2 > 10 || sgpa3 < 0 || sgpa3 > 10 || sgpa4 < 0 || sgpa4 > 10) {
                JOptionPane.showMessageDialog(this, "Invalid age or SGPA values!");
            } else {
                double cgpa = (sgpa1 + sgpa2 + sgpa3 + sgpa4) / 4;
                JOptionPane.showMessageDialog(this, "CGPA: " + cgpa);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values!");
        }
    }
    private void doneButtonClicked() {
        String studentInfo = nameField.getText() + ", " + usnField.getText() + ", " + ageField.getText() + ", " +
                addressField.getText() + ", " + sgpaField1.getText() + ", " + sgpaField2.getText() + ", " +
                sgpaField3.getText() + ", " + sgpaField4.getText() + "," +categoryComboBox.getSelectedItem().toString();
                studentDetails.put(studentID++, studentInfo);
        JOptionPane.showMessageDialog(this, "Student information added to the database! ");     
    }
    private void displayButtonClicked() {
        displayArea.setText(""); // Clear previous results
        for (HashMap.Entry<Integer, String> entry : studentDetails.entrySet()) {
            displayArea.append("Student ID: " + entry.getKey() + "\n");
            displayArea.append(entry.getValue() + "\n\n");
        }
    }  
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CGPA_calculater();
            }
        });
    }
}
