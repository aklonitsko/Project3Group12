/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import student.Student;
import student.StudentCollection;

/**
 * @author Andrew,Brandon,Brian
 *
 */
public class StudentCollectionTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for
	 * {@link student.StudentCollection#searchByName(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testSearchByName() {
		String testFirstName = "FirstTest";
		String testLastName = "LastTest";
		List<Student> students = StudentCollection.searchByName("FirstTest", "LastTest");
		for (Student student : students) {
			System.out.println(student.getID() + " " + student.getFirstName() + " " + student.getLastName());
		}

		// Test Students Returned
		if (students == null || students.size() == 0) {
			fail("No students returned");
		}

		// Test correct students Returned
		for (Student student : students) {
			if (!student.getFirstName().equals(testFirstName) || !student.getLastName().equals(testLastName)) {
				fail("Students returned name doesn't match");
			}
		}
	}

	/**
	 * Test method for
	 * {@link student.StudentCollection#searchByEmail(java.lang.String)}.
	 */
	@Test
	public void testSearchByEmail() {
		String testUWEmail = "uwTest";
		Student student = StudentCollection.searchByEmail(testUWEmail);

		// Test Students Returned
		if (student == null) {
			fail("No students returned");
		}
		
		// Debug
		System.out.println(student.getID() + " " + student.getFirstName() + " " + student.getLastName());

		if (!student.getAcademicRecord().getUWEmail().equals(testUWEmail)) {
			fail("Students returned name doesn't match");
		}
	}

	/**
	 * Test method for {@link student.StudentCollection#add(student.Student)}.
	 */
	@Test
	public void testAdd() {
		assertTrue(StudentCollection.add(new Student("autoTestFirst1", "autoTestLast1")));
	}

	/**
	 * Test method for
	 * {@link student.StudentCollection#update(student.Student, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testUpdate() {
		String originalFirstName = "NewStudentFirst";
		String newFirstName = "ChangedStudentFirst";
		Student student = new Student(originalFirstName, "LastName");
		List<Student> students = StudentCollection.searchByName(originalFirstName, "LastName");
		
		//Ensure test student exists
		if (students == null || students.size() == 0){
			StudentCollection.add(student);
			students = StudentCollection.searchByName(originalFirstName, "LastName");
		}
		
		//Get fully qualified Student (Has ID)
		student = students.get(0);
		
		StudentCollection.update(student, "firstName", newFirstName);
		students = StudentCollection.searchByName(newFirstName, "LastName");
		assertTrue(students.size() > 0);
	}

	/**
	 * Test method for {@link student.StudentCollection#getStudents()}.
	 */
	@Test
	public void testGetStudents() {
		List<Student> students = StudentCollection.getStudents();
		for (Student student : students) {
			System.out.println(student.getID() + " " + student.getFirstName() + " " + student.getLastName());
		}
		assertNotNull(students);
	}

}
