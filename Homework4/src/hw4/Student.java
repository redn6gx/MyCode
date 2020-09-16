package hw4;

import java.text.DecimalFormat;

public class Student {
	
	// TODO complete instance variables
	private int id;
	private String name;
	private boolean graduationStatus;
	private static DecimalFormat df2 = new DecimalFormat("##.##");
	
	public Student(int id, String name) {
	// TO DO complete this method
		this.id = id;
		this.name = name;
		this.graduationStatus = false; // student has not graduated yet
	}
	
	// TODO complete other method
	
	public boolean getGraduationStatus() {
		return graduationStatus;
	}
	
	public void setGraduationStatus(boolean s) {
		this.graduationStatus = s;
	}
	
	public int getId( ) {
		return id;
	}
	
	public String getName( ) {
		return name;
	}
	
	public double getAverage() {
		// TODO complete method to 
		// calculate and return GPA for student
		double result = 0;
		int count = 0;
		
		for(Enrollment e : School.enrollmentList) {
			if(this.id == e.getStudentId()) {
				result += e.getGrade();
				if(e.getGrade() != 0) {
					count++; //only count classes that student has finished and has a grade for
				}
			}
		}
		//check if any classes are done
			//if not return an average of 0.0
		if(result == 0) {
			return 0.0;
		}
		
		//otherwise there is at least one course completed
		result = result/count;
		String temp = df2.format(result);
		if(temp.length() > 4) {
			result = Double.parseDouble((temp.substring(0, 5)));
		}
		return result;
	}
	
	@Override
	public String toString() {
	    // TODO complete this method
		String result = ("Student Number: " + this.id);
//		boolean x = false;
//		
//		for(Enrollment e : School.enrollmentList) {
//			if(this.id == e.getStudentId()) {
//				x = true;
//				break;
//			}
//		}
		 
//		if(x) {
		if(graduationStatus == false) {
			result += ("\nName: " + this.name);
			result += ("\nCourses Enrolled:");
			for(Enrollment e : School.enrollmentList) {
				if(this.id == e.getStudentId()) {
					result += ("\n" + e.getCourseId() + ": " + e.getGrade() + " (" + e.getLetterGrade() + ")");
				}
			}
			result += ("\nCourse Average: " + getAverage());
		} else {
			result += "\nNo student information.";
		}
		return result;
	}

}
