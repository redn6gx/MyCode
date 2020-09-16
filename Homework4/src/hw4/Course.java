package hw4;

import java.text.DecimalFormat;
import java.util.*;

public class Course {
	// TODO complete instance variables
	private int id;
	private String title;
	private int instructor_id;
	private String location;
	private static DecimalFormat df2 = new DecimalFormat("##.##");
	
	public Course(int id, String title, int instructor_id, String location) {
		// TO DO complete constructor
		this.id = id;
		this.title = title;
		this.instructor_id = instructor_id;
		this.location = location;
	}

	// TO DO complete other methods for Course class

	public String getTitle() {
		return title;
	}
	
	
	public double getAverage() {
		// TODO complete this method
		
		double result = 0.0;
		int count= 0;
		
		for (Enrollment e : School.enrollmentList) {
			if(e.getCourseId() == this.id) {
				result += e.getGrade();
				count++;
			}
		}
		
		result = result/count;
//		String temp = df2.format(result);
//		result = Double.parseDouble((temp.substring(0, 3)));
		
		//result = Math.round(result * 100.0) / 100.0;
		 
		return result;
	}
	
	public int getId() {
		return id;
	}
	
	public int getInstructorId() {
		return instructor_id;
	}

	public void setInstructorId(int id) {
		this.instructor_id = id;
	}
	
	public String getLocation() {
		return location;
	}

	public void updateLocation(String location) {
		this.location = location;
	}

}
