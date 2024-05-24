/*
Create an array list of Students (with fields such as name, USN, Dept, section,
CGPA). Search for the students with CGPA > 8.5. Print the student details
belonging to ‘ISE’ department. Print students belonging to ‘C’ section. Use
Iterator to iterate through the list.
*/
import java.util.*;
class Stud{
	String name,usn,dept;
	char sec;
	double cgpa;
	Stud(String name,String usn,String dept,char sec,double cgpa){
		this.name=name;
		this.usn=usn;
		this.dept=dept;
		this.sec=sec;
		this.cgpa=cgpa;
	}
}
public class Student {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Stud> l=new ArrayList<Stud>();
		l.add(new Stud("Arjun","1mkk1","ise",'a',7.8));
		l.add(new Stud("John","1mkk2","cse",'a',8.7));
		l.add(new Stud("James","1mkk3","ise",'c',9));
		l.add(new Stud("Varun","1mkk4","ece",'b',9.7));
		System.out.println("Students with cgpa > 8.5 are:");
		for(Stud s:l) {
			if(s.cgpa>8.5) {
				System.out.println("Student name: "+s.name+" has cgpa "+s.cgpa);
			}
		}
		System.out.println("Students belonging to ise dept");
		for(Stud s:l) {
			if(s.dept.equalsIgnoreCase("ise")) {
				System.out.println("Name: "+s.name+" from "+s.sec+" and "+s.dept+" department has "+s.cgpa+"cgpa");
			}
		}
		System.out.println("Students of 'A' sec");
		for(Stud s:l) {
			if(s.sec=='a'|| s.sec=='A') {
				System.out.println("Name: "+s.name+" from "+s.sec+" and "+s.dept+" department has "+s.cgpa+"cgpa");
			}
		}
	
	System.out.println("\nIterating through the list using Iterator:"); 
		Iterator<Stud> iterator = studentList.iterator(); 
		while (iterator.hasNext()) { 
		Stud student = iterator.next(); 
		System.out.println(student.name + " - CGPA: " + student.cgpa + " bearing USN: " + student.usn); 
		} 
		} 
}
}
