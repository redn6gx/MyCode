package hw4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import hw4.Course;
import hw4.School;
import hw4.Student;

public class Test8 {
	
	//original test: works if a student can have multiple grades for the same course
	@Test
	public void test8_v1() {
		School SCD = new School("SCD");
		Student student1;
		SCD.readData("test1.txt");

		SCD.addStudent(5555, "Chris Watson", 205, 50, "F");
		SCD.addStudent(9999, "Mike Watson", 205, 100.0, "A");
		SCD.addStudent(8888, "Bob Otter", 205, 50, "F");
		SCD.addStudent(7777, "Alice Otter", 205, 100, "A");
		SCD.addStudent(7777, "Alice Otter", 306, 100, "A");

		Course course = SCD.getCourse(205);
		assertEquals(75.0, course.getAverage());


		student1 = SCD.getStudentInfo(7777);
		//assertEquals(96.88, student1.getAverage());

		boolean rc = SCD.graduateStudent(7777);
		assertTrue(rc);
		Student student2 = SCD.getStudentInfo(7777);
		assertNotNull(student2);
		course = SCD.getCourse(205);
		assertEquals( 68.75 , course.getAverage());

		System.out.println("\n===== Detailed Course Info 6 =====");
		SCD.courseInfo(205);

		System.out.println("\n===== Good Job! Bye! =====");
	}
	
	//works when you can not have more than one score per course and can also not override it
	@Test
	public void test8_v2() {
		School SCD = new School("SCD");
		Student student1;
		SCD.readData("test1.txt");

		SCD.addStudent(5555, "Chris Watson", 205, 50, "F");
		SCD.addStudent(9999, "Mike Watson", 205, 100.0, "A");
		SCD.addStudent(8888, "Bob Otter", 205, 50, "F");
		SCD.addStudent(7777, "Alice Otter", 205, 100, "A");
		SCD.addStudent(7777, "Alice Otter", 306, 100, "A");

		Course course = SCD.getCourse(205);
		assertEquals(81.25, course.getAverage());
		student1 = SCD.getStudentInfo(7777);
		assertEquals(95.83, student1.getAverage());

		boolean rc = SCD.graduateStudent(7777);
		assertTrue(rc);
		Student student2 = SCD.getStudentInfo(7777);
		assertNotNull(student2);
		
		course = SCD.getCourse(205);
		assertEquals( 75.00 , course.getAverage());

		System.out.println("\n===== Detailed Course Info 6 =====");
		SCD.courseInfo(205);

		System.out.println("\n===== Good Job! Bye! =====");
	}
	
	//works when you can only have one score, but you can override it
	@Test
	public void test8_v3() {

        //make a school
        School SCD = new School("SCD");
        Student student1;
        //load in information into the school class
        SCD.readData("test1.txt");

        //add in more students manually
        SCD.addStudent(5555, "Chris Watson", 205, 50, "F");
        SCD.addStudent(9999, "Mike Watson", 205, 100.0, "A");
        SCD.addStudent(8888, "Bob Otter", 205, 50, "F");
        SCD.addStudent(7777, "Alice Otter", 205, 100, "A");
        SCD.addStudent(7777, "Alice Otter", 306, 100, "A");

        //make sure the class average is 75%
        Course course = SCD.getCourse(205);
        double average = course.getAverage();
        assertEquals(75.0, average);

        //make sure the student average is 96.88%
        student1 = SCD.getStudentInfo(7777);
        average = student1.getAverage();
        assertEquals(96.5, average);

        //graduate the student
        boolean rc = SCD.graduateStudent(7777);
        assertTrue(rc);
        //make sure the student still has SOME info
        Student student2 = SCD.getStudentInfo(7777);
        assertNotNull(student2);

        //now get the class average again
        course = SCD.getCourse(205);
        average = course.getAverage();
        assertEquals( 66.67 , average);

        System.out.println("\n===== Detailed Course Info 6 =====");
        SCD.courseInfo(205);
        System.out.println("\n===== Good Job! Bye! =====");
	}
}
