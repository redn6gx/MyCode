package hw4;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.*;

public class School {

	// TODO complete instance variables
	private ArrayList<Instructor> faculty;
	private ArrayList<Course> catalog;
	private ArrayList<Student> students;
	public static ArrayList<Enrollment> enrollmentList;
	private String name;
	
	public School(String name) {
		this.name = name;
		// TODO complete method
		faculty = new ArrayList<>();
		catalog = new ArrayList<>();
		students = new ArrayList<>();
		enrollmentList = new ArrayList<>();
	}

	public void readData(String filename) {
		//TODO complete readData method
		try (Scanner infile = new Scanner( new File(filename));){
			int countInstructors = infile.nextInt();
			infile.nextLine();  // DISCARD END OF LINE character
			for (int i=0; i < countInstructors; i++) {
				String line = infile.nextLine();
				StringTokenizer str = new StringTokenizer(line, ",");
				int iid = Integer.parseInt(str.nextToken());
				String name = str.nextToken();
				String email = str.nextToken();
				String phone = str.nextToken();
				boolean rc = addInstructor(iid, name, email, phone);
//				if (rc) {
//					System.out.println("Instructor "+ iid +" added successfully");
//				} else {
//					System.out.println("Error:  duplicate employee id "+iid);
//				}
			}
			int countCourses = infile.nextInt();
			infile.nextLine();  // DISCARD END OF LINE character
			for (int i=0; i < countCourses; i++) {
				String line = infile.nextLine();
				StringTokenizer str = new StringTokenizer(line, ",");
				int courseId = Integer.parseInt(str.nextToken());
				String title = str.nextToken();
				int iid = Integer.parseInt(str.nextToken());
				String location = str.nextToken();
				boolean rc = addCourse(courseId, title, iid, location);
//				if (rc) {
//					System.out.println("Course "+courseId+" added.");
//				} else {
//					System.out.println("Course not valid");
//				}
			}
			
			int countStudents = infile.nextInt();
			infile.nextLine();  // DISCARD END OF LINE character
			for (int i=0; i < countStudents; i++) {
				String line = infile.nextLine();
				StringTokenizer str = new StringTokenizer(line, ",");
				int studentId = Integer.parseInt(str.nextToken());
				String name = str.nextToken();
				int courseId = Integer.parseInt(str.nextToken());
				double grade = Double.parseDouble(str.nextToken());
				String letter = str.nextToken();
				boolean rc = addStudent(studentId, name, courseId, grade, letter);
//				if (rc) {
//					System.out.println("Student "+studentId+" processed");
//				} else {
//					System.out.println("Student "+studentId+" error.");
//				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Done.");
			
	}
	
	public String getNameById(int id) {
		for(Student s: students) {
			if(s.getId() == id) {
				return s.getName();
			}
		}
		return "";
	}
	
	public String getTitleById(int id) {
		for(Course c: catalog) {
			if(c.getId() == id) {
				return c.getTitle();
			}
		}
		return "";
	}
	
	public void schoolInfo() {

		// TODO Complete this method
		System.out.println("School Name: " + name);
		
		System.out.println("Instructor Information");
		for(Instructor i : faculty) {
			System.out.println(i.getName());
		}
		System.out.println("Course Information");
		for(Course i : catalog) {
			System.out.println(i.getTitle());
		}
		System.out.println("Student Information");
		for(Enrollment e: enrollmentList) {
		System.out.println(getNameById(e.getStudentId()) + ": " + getTitleById(e.getCourseId()));
		}
	}
	
	//check for duplicate instructor id
	public boolean validInstructorId(int id) {
		for (int i=0; i<faculty.size(); i++) {
			if (faculty.get(i).getId() == id) {
				return false;
			}
		}
		return true;
	}
	
	public boolean addInstructor(int id, String name, String email, String phone) {
		// TO DO complete this method
		//check if instructor already exists
		if(validInstructorId(id)) {
			Instructor currentInstructor = new Instructor(id, name, email, phone);
			faculty.add(currentInstructor);
			return true;
		}
		System.out.println("Instructor addition failed - Employee number already used.");
		return false;
	}
	
	public boolean instructorExists(int id) {
		for (int i=0; i<faculty.size(); i++) {
			if (faculty.get(i).getId() == id) {
				return true;
			}
		}
		return false;
	}
	
	//check for duplicate course id
	public boolean validCourseId(int id) {
		for (int i=0; i<catalog.size(); i++) {
			if (catalog.get(i).getId() == id) {
				return false;
			}
		}
		return true;
	}
	
	public boolean addCourse(int id, String title, int instructor_id, String location) {
		// TODO complete this method
		//check that instructor_id is valid
		//check that course id is not duplicate
		if((instructorExists(instructor_id) == true) && (validCourseId(id) == true)) {
			Course currentCourse = new Course(id, title, instructor_id, location);
				catalog.add(currentCourse);
				return true;
			}
			if(validCourseId(id) == false) {
				System.out.println("Course addition failed - Course number already used.");
			}
			if(instructorExists(instructor_id) == false) {
				System.out.println("Course addition failed - Non-existing instructor.");
			}
			return false;
		}

	public String getInstructorById(int id){
		for(Instructor i: faculty) {
			if(i.getId() == id) {
				return i.getName();
			}
		}
		return "";
	}
	
	// return the number of students enrolled in the course
	public int numOfEnrolled(int course_id) {
		int count = 0;
		for(Enrollment e: enrollmentList) {
			if(course_id == e.getCourseId()) {
				count++;
			}
		}
		return count;
	}
	
	public double getCourseAverage(int course_id) {
		int count = 0;
		double total = 0.0;
		double average = 0.0;
		for(Enrollment e: enrollmentList) {
			if(course_id == e.getCourseId()) {
				total += e.getGrade();
				count++;
			}
		}
		average = total/count;
		return average;
	}
	
	public void courseInfo() {
		// TODO complete this method
		System.out.println("Number of courses: " + catalog.size());
		for(Course c: catalog) {
			System.out.println(c.getId() + ": " + numOfEnrolled(c.getId()) + " enrolled");
		}
	}

	public void courseInfo(int id) {
		// TODO complete method
		for(Course c: catalog) {
			if(id == c.getId()) {
				System.out.println("Course number: " + id);
				System.out.println("Instructor: " + getInstructorById(c.getInstructorId()));
				System.out.println("Course Title: " + c.getTitle());
				System.out.println("Room: " + c.getLocation());
				System.out.println("Total Enrolled: " + numOfEnrolled(id));
				System.out.println("Course Average: " + getCourseAverage(id));
			}
		}
	}
	
	// get the number of students enrolled in a course
//	public int studentCount(Course ) {
//		
//	}
	
	public Course getCourse(int courseId) {
		// TODO complete method
		for(Course c: catalog) {
			if(c.getId() == courseId) {
				return c;
			}
		}
		return null;
	}

	public boolean deleteCourse(int id) {
		// TODO complete method
		//iterate through course list
			//if id matches
				//check if course has students enrolled
					//iterate through enrollmentList
						//if id matches any in enrollmentList
							//can't delete course - display corresponding error
						//if id doesn't match any then it's okay to delete the course
		for(Course c : catalog) {
			if(id == c.getId()) {
				if(numOfEnrolled(id) == 0) {
					catalog.remove(c);
					return true;
				}
			}
		}
		System.out.print("Course deletion failed - Enrolled student(s) in the class");
		return false;
	}

	public boolean studentExists(int id) {
		for(Student s: students) {
			if(s.getId() == id) {
				return true;
			}
		}
		return false;
	}
	
	// check if student is already enrolled in a course
	public boolean alreadyEnrolled(int id, int course_id) {
		for(Enrollment e: enrollmentList) {
			if((e.getStudentId() == id) && (e.getCourseId() == course_id)) {
				return true; // student is already enrolled in that course
			}
		}
		return false; 
	}
	
	// checks to see if id is already taken
	public boolean isIdAvailable(int id, String name) {
		for(Student s: students) {
			if((s.getId() == id) && (s.getName() != name)) {
				return false; // a different name is already associated with that student id
			}
		}
		return true;
	}
	
	public boolean addStudent(int id, String name, int course_id, double grade, String letter_grade) {
		// TODO complete method
		if(isIdAvailable(id, name) == true) {		
			if(studentExists(id) == false) {
//				Student currentStudent = new Student(id, name, course_id, grade, letter_grade);
				Student currentStudent = new Student(id, name);
				students.add(currentStudent);
	 		}
			// TODO enroll student in course
				// check if student is already enrolled in class
					// if no enroll them
					// if yes then indicate corresponding error
				// check that the course we are trying to enroll student in exists
			if((alreadyEnrolled(id, course_id) == false) && (validCourseId(course_id) == false)) { // validCourseId will return false if course already exists
				Enrollment currentEnroll = new Enrollment(id, course_id, grade, letter_grade);
				enrollmentList.add(currentEnroll);
				return true;
			}	
		}
		if((alreadyEnrolled(id, course_id) == false) && (validCourseId(course_id) == false)) { // validCourseId will return false if course already exists
			Enrollment currentEnroll = new Enrollment(id, course_id, grade, letter_grade);
			enrollmentList.add(currentEnroll);
			return true;
		}
		if(validCourseId(course_id) == true) {
			System.out.println("Student info addition failed - Non-existing course number.");
		}
		else {
			System.out.println("Student info addition failed - Student ID number already used.");
		}
	
		return false; // id was already taken (id existed but name was different)
	}

	public Student getStudentInfo(int id) {
		// TODO complete method
		for(Student s : students) {
			if(id == s.getId()) {
				return s;
			}
		}
		return null;
	}

	public boolean graduateStudent(int id) {
		// TODO complete method
		//should eliminate students enrollment records
		if(studentExists(id) && getStudentInfo(id).getGraduationStatus() ==  false) {
			for(int i=0; i<enrollmentList.size(); i++) {
				if(enrollmentList.get(i).getStudentId() == id) {
					enrollmentList.remove(i);
				}
			}
			getStudentInfo(id).setGraduationStatus(true);
			return true;
		}
		System.out.println("Student graduation failed - Non-existing student.");
		return false;
	}

	public boolean register(int studentId, int courseId) {
		// TODO
		if(alreadyEnrolled(studentId, courseId) == false && studentExists(studentId) == true && validCourseId(courseId) == false) {
			Enrollment e = new Enrollment(studentId, courseId, 0, "");
			enrollmentList.add(e);
			return true;
		}
		return false;
	}

	public boolean grade(int studentId, int courseId, double grade, String letter_grade) {
		// TODO
		for(Enrollment e : enrollmentList) {
			if(studentId == e.getStudentId() && courseId == e.getCourseId()) {
				e.setGrade(grade);
				e.setLetterGrade(letter_grade);
				return true;
			}
		}
		return false;
	}

	public boolean drop(int studentId, int courseId) {
		// TODO
		if(alreadyEnrolled(studentId, courseId) == true && studentExists(studentId) == true && validCourseId(courseId) == false) {
			for(Enrollment e : enrollmentList) {
				if(e.getStudentId() == studentId && e.getCourseId() == courseId) {
					enrollmentList.remove(e);
					return true;
				}
			}
		}
		return false;
	}

	public boolean assign(int instructorId, int courseId) {
		// TODO
		if(instructorExists(instructorId) == true && validCourseId(courseId) == false) {
			getCourse(courseId).setInstructorId(instructorId);
			return true;
		}
		return false;
	}
	
	public boolean unassign(int instructorId, int courseId) {
		// TODO
		boolean result = false;
		for(Course c : catalog) {
			if(instructorId == c.getInstructorId() && courseId == c.getId()) {
				result = true;
				break;
			}
		}
		
		if(instructorExists(instructorId) == true && validCourseId(courseId) == false && result == true) {
			getCourse(courseId).setInstructorId(0);
			return true;
		}
		return false;
	}

}
