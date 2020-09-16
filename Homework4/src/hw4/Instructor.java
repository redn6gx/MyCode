package hw4;

public class Instructor {
	
	// TODO complete instance variables
	private int id;
	private String name;
	private String email;
	private String phone;
	
	public Instructor(int id, String name, String email, String phone) {
		// TODO complete constructor method
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}
	
   // TO DO complete other methods
	public int getId() {return id;}
	
	public String getName() {return name;}
	
	public String toString() {
		return id + " " + name + " " + email + " " + phone;
	}
	
}
