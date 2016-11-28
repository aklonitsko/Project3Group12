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
	public static List<Student> search(Student student) {
		List<Student> list = new ArrayList<Student>();
		if (mStudentDB == null) {
			mStudentDB = new StudentDB();
		}
		try {
			return mStudentDB.getStudents(student);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Adds a new client to the data.
	 * 
	 * @param client
	 * @return true if client was added successfully
	 */
	public static boolean add(Client client) {
		if (mClientDB == null) {
			mClientDB = new ClientDB();
		}

		String message = mClientDB.addClient(client);
		if (message.startsWith("Error adding client:")) {
			return false;
		}
		return true;
	}

	/**
	 * Modify the particular column of the client with the given data.
	 * 
	 * @param client
	 *            Client to modify
	 * @param column
	 *            the field in the table to modify
	 * @param data
	 *            the value for the field
	 * @return true if the client was updated successfully
	 */
	public static boolean update(Client client, String column, String data) {
		if (mClientDB == null) {
			mClientDB = new ClientDB();
		}
		String message = mClientDB.updateClient(client, column, data);
		if (message.startsWith("Error updating client: ")) {
			return false;
		}
		return true;
	}


	/**
	 * Return all clients in the list, null otherwise.
	 * 
	 * @return Client
	 */
	public static List<Client> getClients() {
		if (mClientDB == null) {
			mClientDB = new ClientDB();
		}
		try {
			return mClientDB.getClients();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
