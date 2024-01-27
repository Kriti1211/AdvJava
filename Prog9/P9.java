import java.util.*;
class Boo{
	String title,auth,pub;
	int id;
	double price;
	Boo(int id,String title,String auth,String pub,double price){
		this.id=id;
		this.title=title;
		this.auth=auth;
		this.pub=pub;
		this.price=price;
	}
	int getId() {
		return id;
	}
	String getTitle() {
		return title;
		
	}
	String getAuth() {
		return auth;
	}
	String getPub() {
		return pub;
	}
	double getPrice() {
		return price;
	}
	void setPub(String pub) {
		this.pub=pub;
	}
}
class BooDb{
	Map<Integer,Boo> bm;
	BooDb(){
		this.bm=new HashMap<>();
	}
	void addBoo(Boo x) {
		bm.put(x.getId(), x);
	}
	List<Boo> byTitle(String a){
	
		List<Boo> res=new ArrayList<>();
		for(Boo x:bm.values()) {
			if(x.getTitle().toLowerCase().contains(a.toLowerCase())) {
				res.add(x);
			}
		}
		return res;
	}
	List<Boo> byPub(String a){
		
		List<Boo> res=new ArrayList<>();
		for(Boo x:bm.values()) {
			if(x.getPub().equalsIgnoreCase(a)) {
				res.add(x);
			}
		}
		return res;
	}
	void updatePub(String t,String p) {
		for(Boo x:bm.values()) {
			if(x.getTitle().equalsIgnoreCase(t)) {
				x.setPub(p);
			}
		}
	}
	Collection<Boo> getAll(){
		return bm.values();
	}
}
public class P9 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		BooDb db=new BooDb();
		while(true) {
			System.out.println("1.Add Boo " +"\n"+
					"2.search by title "+"\n"+
					"3.search by pub "+"\n"+
					"4.update pub"+"\n"+
					"5.exit");
			int c=sc.nextInt();
			sc.nextLine();
			switch(c) {
			case 1:addBoo(sc,db);
				   break;
			case 2:byTitle(sc,db);
			break;
			case 3:byPub(sc,db);
			break;
			case 4:updatePub(sc,db);
			break;
			case 5:System.exit(0);
			}
		}
	}
	static void addBoo(Scanner sc,BooDb db) {
			System.out.println("Enter title:");
			String t=sc.nextLine();
			System.out.println("Enter author:");
			String a=sc.nextLine();
			System.out.println("Enter pub:");
			String p=sc.nextLine();
			System.out.println("Enter price:");
			double m=sc.nextDouble();
			int id=db.getAll().size()+1;
			Boo nb=new Boo(id,t,a,p,m);
			db.addBoo(nb);
			
			System.out.println("Success");
		}
	static void byTitle(Scanner sc,BooDb db) {
		System.out.println("Enter title");
		String t=sc.nextLine();
		List<Boo> res=db.byTitle(t);
		
		if(!res.isEmpty()) {
			for(Boo b:res) {
			System.out.println("Title:"+b.getTitle()+"\n"+
			"Author:"+b.getAuth()+"\n"+
					"Publisher:"+b.getPub()+"\n"+
			"Price:"+b.getPrice());
			}
		}
		else
		{
			System.out.println("No match");
		}
	}

	static void byPub(Scanner sc,BooDb db) {
		System.out.println("Enter pub");
		String t=sc.nextLine();
		List<Boo> res=db.byPub(t);
		
		if(!res.isEmpty()) {
			for(Boo b:res) {
			System.out.println("Title:"+b.getTitle()+"\n"+
			"Author:"+b.getAuth()+"\n"+
					"Publisher:"+b.getPub()+"\n"+
			"Price:"+b.getPrice());
			}
		}
		else
		{
			System.out.println("No match");
		}
	}
	static void updatePub(Scanner sc,BooDb db) {
		System.out.println("Enter title of the book to change:");
		String t=sc.nextLine();
		System.out.println("Enter new publisher");
		String p=sc.nextLine();
		db.updatePub(t, p);
		System.out.println("Update success");
	}


}
