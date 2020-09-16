package hw4;

public class Enrollment {
	private int studentId;
	private int courseId;
	private double grade;
	private String letterGrade;
	
	public Enrollment(int studentId, int courseId, double grade, String letterGrade) {
		this.studentId = studentId;
		this.courseId = courseId;
		this.grade = grade;
		this.letterGrade = letterGrade;
	}
	
	public int getStudentId() {
		return studentId;
	}
	
	public int getCourseId() {
		return courseId;
	}
	
	public double getGrade() {
		return grade;
	}
	
	public void setGrade(double grade) {
		this.grade = grade;
	}
	
	public String getLetterGrade() {
		return letterGrade;
	}
	
	public void setLetterGrade(String letterGrade) {
		this.letterGrade = letterGrade;
	}
}
