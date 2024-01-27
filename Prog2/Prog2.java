
import java.util.*;

class Contact {
    String num, name;

    Contact(String num, String name) {
        this.num = num;
        this.name = name;
    }

    String getNum() {
        return num;
    }

    public String toString() {
        return "name:" + this.name + " Phone number:" + this.num;
    }
}

public class Prog2 {

    static void missCall(ArrayList<Contact> a) {
        Iterator<Contact> itr = a.iterator();
        while (itr.hasNext()) {
            Contact c = itr.next();
            if (c.name == null || c.name.isEmpty()) {
                c.name = "Private caller";
            }
        }
        // Create a new iterator for the second loop
        Iterator<Contact> itr2 = a.iterator();
        System.out.println("Missed call details");
        while (itr2.hasNext()) {
            Contact c = itr2.next();
            System.out.println("Name:" + c.name + "\n Number:" + c.num);
        }
    }

    static void disordel(ArrayList<Contact> a) {

        Scanner sc = new Scanner(System.in);

        Iterator itr = a.iterator();
        while (itr.hasNext()) {
            Contact c = (Contact) itr.next();
            System.out.println("Enter delete or display");
            String ch = sc.nextLine();
            if (ch.equalsIgnoreCase("delete")) itr.remove();
            else if (ch.equalsIgnoreCase("display")) {
                System.out.println("Name: " + c.name + " Phone number:" + c.num);
                break;
            }
        }
    }

    static void startA(HashMap<String, Contact> hm) {
        for (Contact c : hm.values()) {
            if (c.name != null && c.name.toLowerCase().startsWith("a")) {
                System.out.println("Starting with 'A'");
                System.out.println("Name:" + c.name + " Phone number:" + c.num);
            }
        }
    }

    public static void main(String[] args) {
        HashMap<String, Contact> hm = new HashMap<>();
        ArrayList<Contact> a = new ArrayList<>();
        Contact c1 = new Contact("12345678890", "Riya");
        Contact c2 = new Contact("9900990099", "Arjun");
        Contact c3 = new Contact("8899889900", "");
        hm.put(c1.getNum(), c1);
        hm.put(c2.getNum(), c2);
        hm.put(c3.getNum(), c3);
        a.add(c2);
        a.add(c3);
        missCall(a);
        disordel(a);
        startA(hm);
    }
}
