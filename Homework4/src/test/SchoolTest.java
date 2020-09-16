package test;

import static org.junit.jupiter.api.Assertions.*;

import hw4.*;

import org.junit.jupiter.api.Test;

class SchoolTest {

	@Test
	// test assign and unassign instructor to a course
	void test() {
		School school = new School("SCD");
		//  tom teaches CST338
		boolean rc = school.addInstructor(1,  "tom", "tom@csumb.edu", "813-111-1212");
		assertTrue(rc);
		
		rc = school.addCourse(338, "CST338 Software Design", 1, "BIT105"); 
		assertTrue(rc);
		Course c = school.getCourse(338);
		assertEquals(1, c.getInstructorId());
		
		// unassign Tom from 338.  338 should have instructor_id of 0.
		rc = school.unassign(1,  338);
		assertTrue(rc);
		assertEquals(0, c.getInstructorId());
		
		// unassign fails because Tom is not teaching 338.
		rc = school.unassign(1, 338);
		assertFalse(rc);
		
		// invalid instructor id should fail
		rc = school.unassign(9, 338);
		assertFalse(rc);
		
		// invalid course id should fail
		rc = school.unassign(1,  444);
		assertFalse(rc); 
		
		// assign Ray to 338.
		rc =school.addInstructor(2, "ray", "ray@csumb.edu", "813-212-1212");
		assertTrue(rc);
		rc = school.assign(2,  338);
		assertTrue(rc);
		assertEquals(2, c.getInstructorId());
	}
	
	@Test
	// test register, grade and drop student in a course
	void test2() {
		School school = new School("SCD");
		school.addInstructor(1,  "tom", "tom@csumb.edu", "813-111-1212");
		school.addCourse(338, "CST338 Software Design", 1, "BIT105"); 
		school.addCourse(438,  "Software Engineering", 1,  "BIT104");
		school.addStudent(1001, "Grace", 338, 68, "D"); 
		
		// drop student from enrolled class
		boolean rc = school.drop(1001,  338);
		assertTrue(rc);
		
		// drop fails.  student is not enrolled. 
		rc = school.drop(1001, 338);
		assertFalse(rc);
		
		// cannot drop student that does not exist
		rc = school.drop(2001,  338);
		assertFalse(rc);
		
		// cannot drop class that does not exist
		rc = school.drop(1001,  339);
		assertFalse(rc);
		
		// Grace enrolls in 338 again
		rc = school.register(1001,  338);
		assertTrue(rc);
		// Grace does not have a grade. So student average should be 0.0
		Student grace = school.getStudentInfo(1001);
		double gpa = grace.getAverage();
		assertEquals( 0.0, gpa);
		//  course average should also be 0.0
		Course c338 = school.getCourse(338);
		double course_avg = c338.getAverage();
		assertEquals( 0.0, course_avg);
		// Grace complete course with good grade
		school.grade(1001,  338,  92.0,  "A");
		gpa = grace.getAverage();
		assertEquals(92.0, gpa);
		course_avg = c338.getAverage();
		assertEquals(92.0, course_avg);
		
		// grace now enrolls in 438
		school.register(1001,  438);
		// but she has not finished course and does not have a grade.
		// her gpa should still be 92.0
		gpa = grace.getAverage();
		assertEquals(92.0, gpa);
		// course average for 438 should be 0
		Course c438 = school.getCourse(438);
		course_avg = c438.getAverage();
		assertEquals(0.0, course_avg);
		
	}
	
	@Test
	// test graduate
	void test3() {
		School school = new School("SCD");
		school.addInstructor(1,  "Bob",  "bob@csumb.edu",  "813-211-1212");
		school.addCourse(301,  "CST301 - Python",  1,  "BIT104");
		school.addStudent(1001,  "Alice",  301,  92.0,  "A");
		
		// Alice graduates 
		boolean rc = school.graduateStudent(1001);
		assertTrue(rc);
		
		// after graduation, student still exists 
		Student s = school.getStudentInfo(1001);
		assertNotNull(s);
		
		String str = s.toString();
		// graduated student toString should return string
		// with student id and message "No student information."
		assertNotEquals(-1, str.indexOf("1001"));
		assertNotEquals(-1, str.indexOf("No student information."));
		
		// bad student id
		rc = school.graduateStudent(1002);
		assertFalse(rc);
		
		// cannot graduate twice
		rc = school.graduateStudent(1001);
		assertFalse(rc);
		
	}
	
	@Test 
	// test add and delete Course
	void test4() {
		School school = new School("CSUMB");
		school.addInstructor(1,  "Bob",  "bob@csumb.edu",  "813-211-1212");
		school.addCourse(301,  "CST301 - Python",  1,  "BIT104");
		// delete course with no enrollments
		boolean rc = school.deleteCourse(301);
		assertTrue(rc);
		
		school.addCourse(301,  "CST301 - Python",  1,  "BIT104");
		school.addStudent(1001,  "Alice",  301,  92.0,  "A");
		// delete course with enrolled student should fail
		rc = school.deleteCourse(301);
		assertFalse(rc);
		
		// when student drops course, course now has no enrollment
		rc = school.drop(1001,  301);
		assertTrue(rc);
		rc = school.deleteCourse(301);
		assertTrue(rc);
		
		// student registers for course, but then graduates
		rc = school.addCourse(301,  "CST301 - Python",  1,  "BIT104");
		assertTrue(rc);
		rc = school.register(1001, 301);
		assertTrue(rc);
		rc = school.grade(1001,  301,  92.0,  "A");
		assertTrue(rc);
		rc = school.graduateStudent(1001);
		assertTrue(rc);
		rc = school.deleteCourse(301);
		assertTrue(rc);
	
	}
	
	@Test
	// add instructor
	public void test5() {
		School school = new School("CSUMB");
		boolean rc = school.addInstructor(1,  "tom",  "tom@csumb.edu",  "813-111-1011");
		assertTrue(rc);
		
		// duplicate instructor should fail
		rc = school.addInstructor(1,  "alice",  "alice@csumb.edu", "813-888-1313");
		assertFalse(rc);
	}
	
	@Test 
	// add Student
	public void test6() {
		School school = new School("CSUMB");
		school.addInstructor(1,  "Bob",  "bob@csumb.edu",  "813-211-1212");
		school.addCourse(301,  "CST301 - Python",  1,  "BIT104");
		school.addCourse(338,  "CST338 Software Design",  1,  "BIT105");
		boolean rc = school.addStudent(1001,  "Alice",  301,  92.0,  "A");
		assertTrue(rc);
		
		// student with same id but different name should fail
		rc = school.addStudent(1001,  "Alison",  301,  85, "B");
		assertFalse(rc);
		
		// student with same id and name, different course
		rc = school.addStudent(1001,  "Alice", 338, 85, "B");
		assertTrue(rc);
		
		Student alice = school.getStudentInfo(1001);
		double gpa = alice.getAverage();
		assertEquals(88.5, gpa);
	}
	
	@Test
	public void test7() {
		School SCD = new School("SCD");
		Course course1;
		SCD.readData("test1.txt");

		boolean rc = SCD.addInstructor(700, "E. Tao", "tao@csumb.edu", "777-777-1234");
		assertTrue(rc);
		
		// addCourse should fail. invalid instructor id.
		rc = SCD.addCourse(300, "CST300 – ProSem", 70, "BIT 110");
		assertFalse(rc);
		
		// invalid instructor id
		rc = SCD.addCourse(499, "CST499 – iOS Dev", 15, "BIT 104");
		assertFalse(rc);

		// duplicate courseid and invalid instructor id
		rc = SCD.addCourse(306, "CST306 – GUI Dev", 25, "BIT 120");
		assertFalse(rc);

		course1 = SCD.getCourse(205);
		course1.updateLocation("Library 104");
		assertEquals("Library 104", course1.getLocation());

		// cannot delete a course with students
		rc = SCD.deleteCourse(306);
		assertFalse(rc);
		
		rc = SCD.deleteCourse(338);
		assertFalse(rc);
	}
	
	@Test
	public void test8() {
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
}
