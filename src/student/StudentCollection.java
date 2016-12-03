/**
 * 
 */
package student;

<<<<<<< HEAD
=======
<<<<<<< HEAD
import java.util.ArrayList;
=======
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
>>>>>>> 9840fc700c75e6cb811d47af837db4ee3655df33

import data.StudentDB;

>>>>>>> branch 'master' of https://github.com/gibbbran/Project3Group12
/**
 * @author Andrew,Brandon,Brian
 *
 */
public class StudentCollection {

<<<<<<< HEAD
=======
<<<<<<< HEAD
	private StudentDB myStudentDB;
	private ArrayList<Student> myStudentList;
	
	public StudentCollection() {
		
		myStudentDB = new StudentDB();
		myStudentList = new ArrayList<Student>();
	}
	
	public boolean addStudent(String theName) {
		
		boolean flag = false;
		
		try {
			
		} catch(Exception e) {
			
		}
	}
	
	
=======
	private static StudentDB mStudentDB;
>>>>>>> branch 'master' of https://github.com/gibbbran/Project3Group12

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

>>>>>>> 9840fc700c75e6cb811d47af837db4ee3655df33

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
