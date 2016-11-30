/**
 * 
 */
package student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.StudentDB;

/**
 * @author Andrew,Brandon,Brian
 *
 */
public class StudentCollection {

	private static StudentDB mStudentDB;

	/**
	 * Return a list of students that have the first name and last name.
	 * 
	 * @param firstName
	 *            	first name of student
	 * @param lastName
	 * 				last name of student
	 * @return a list of students that match
	 */
	public static List<Student> searchByName(String firstName, String lastName) {
		List<Student> list = new ArrayList<Student>();
		if (mStudentDB == null) {
			mStudentDB = new StudentDB();
		}
		try {
			return mStudentDB.getStudents(firstName, lastName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * Return a student that has the supplied UW Email.
	 * 
	 * @param uwEmail Unique UW email address of the student.
	 * @return a student that has the given UW email.
	 */
	public static Student searchByEmail(String uwEmail) {
		Student student = null;
		if (mStudentDB == null) {
			mStudentDB = new StudentDB();
		}
		try {
			return mStudentDB.getStudent(uwEmail);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}

	/**
	 * Adds a new student to the data.
	 * 
	 * @param student
	 * @return true if student was added successfully
	 */
	public static boolean add(Student student) {
		if (mStudentDB == null) {
			mStudentDB = new StudentDB();
		}

		String message = mStudentDB.addStudent(student);
		if (message.startsWith("Error adding student:")) {
			return false;
		}
		return true;
	}

	/**
	 * Modify the particular column of the student with the given data.
	 * 
	 * @param student
	 *            Student to modify
	 * @param column
	 *            the field in the table to modify
	 * @param data
	 *            the value for the field
	 * @return true if the student was updated successfully
	 */
	public static boolean update(Student student, String column, String data) {
		if (mStudentDB == null) {
			mStudentDB = new StudentDB();
		}
		String message = mStudentDB.updateStudent(student, column, data);
		if (message.startsWith("Error updating student: ")) {
			return false;
		}
		return true;
	}


	/**
	 * Return all students in the DB, null otherwise.
	 * 
	 * @return Student
	 */
	public static List<Student> getStudents() {
		if (mStudentDB == null) {
			mStudentDB = new StudentDB();
		}
		try {
			return mStudentDB.getStudents();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
