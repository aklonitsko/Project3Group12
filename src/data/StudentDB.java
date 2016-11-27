package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import client.Client;

/**
 * This class contains methods to access the Student tables data.
 * 
 * @author mabraham
 * @author Brian Lloyd
 *
 */

public class StudentDB {

	private Connection mConnection;
	private List<Client> mStudentList;
	
	/**
	 * Modifies the data on a student
	 * 
	 * @param student
	 * @param columnName
	 * @param data
	 * @return Returns a message with success or failure.
	 */
	public String updateStudent(Student student, String columnName, String data) {

		int id = Integer.parseInt(student.getId());
		String sql = "UPDATE Student SET `" + columnName + "` = ?  WHERE firstName = ? AND lastName = ? ";
		// For debugging - System.out.println(sql);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = mConnection.prepareStatement(sql);
			preparedStatement.setString(1, data);
			preparedStatement.setString(2, student.getFirstName); // for firtName=
			preparedStatement.setString(3, student.getLastName);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
			return "Error updating student: " + e.getMessage();
		}
		return "Updated Student Successfully";
	}
	
	/**
	 * Adds a new student to the Student table.
	 * 
	 * @param student
	 * @return Returns "Added Student Successfully" or "Error adding student: " with
	 *         the sql exception.
	 */
	public String addStudent(Student student) {
		String sql = "INSERT INTO Student(firstName, lastName) values "
				+ "(?, ?); ";

		if (mConnection == null) {
			try {
				mConnection = DataConnection.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = mConnection.prepareStatement(sql);
			preparedStatement.setString(1, student.getFirstName());
			preparedStatement.setString(2, student.getLastName());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error adding student: " + e.getMessage();
		}
		return "Added Student Successfully";
	}

	/**
	 * Gets  all Students from the student tables
	 * 
	 * @return Returns list of all students
	 * @throws SQLException
	 */
	public List<Student> getStudents() throws SQLException {
		if (mConnection == null) {
			mConnection = DataConnection.getConnection();
		}
		Statement stmt = null;
		String query = "select * " + "from Student";

		mStudentList = new ArrayList<Student>();
		try {
			stmt = mConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt("studentID");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				Student student = new Student(firstName, lastName);
				AcademicRecord = AcademicCollection.getAcademicRecord(id);
				student.setAcademicRecord(AcademicCollection.getAcademicRecord(id));
				List<Employer> employers = EmployerCollection.getEmployers(id);
				student.setEmployers(employers);
				
				mStudentList.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return mStudentList;
	}

//	/**
//	 * Gets the data on all clients who have a field that contains the keyword.
//	 * 
//	 * @param keyword  the search term that will be found in any of the clients fields excluding Id
//	 * @return list of Clients whose field contains the keyword
//	 * @throws SQLException 
//	 */
//	public List<Client> getClients(String keyword) throws SQLException {
//		List<Client> filterList = new ArrayList<Client>();
//		if (keyword != null) {
//			if (mConnection == null) {
//				mConnection = DataConnection.getConnection();
//			}
//			String sql = "SELECT * FROM Client WHERE (lastName LIKE CONCAT('%',? ,'%')) " 
//					+ "OR (firstName LIKE CONCAT('%',? ,'%')) " + "OR (middleInitial LIKE CONCAT('%',? ,'%')) " 
//					+ "OR (streetAddress LIKE CONCAT('%',? ,'%')) " + "OR (city LIKE CONCAT('%',? ,'%')) "
//					+ "OR (state LIKE CONCAT('%',? ,'%')) " + "OR (zipcode LIKE CONCAT('%',? ,'%'))";
//
//			//For debugging - System.out.println(sql);
//			PreparedStatement preparedStatement = null;
//			try {
//				preparedStatement = mConnection.prepareStatement(sql);
//				preparedStatement.setString(1, keyword);
//				preparedStatement.setString(2, keyword);
//				preparedStatement.setString(3, keyword);
//				preparedStatement.setString(4, keyword);
//				preparedStatement.setString(5, keyword);
//				preparedStatement.setString(6, keyword);
//				preparedStatement.setString(7, keyword);
//				ResultSet rs = preparedStatement.executeQuery();
//				while (rs.next()) {
//					int id = rs.getInt("clientId");
//					String firstName = rs.getString("firstName");
//					String lastName = rs.getString("lastName");
//					String middleInitial = rs.getString("middleInitial");
//					String street = rs.getString("streetAddress");
//					String city = rs.getString("city");
//					String state = rs.getString("state");
//					String zipCode = rs.getString("zipcode");
//					Client client = null;
//					if (middleInitial == null) {
//						client = new Client(firstName, lastName, street, city, state, zipCode);
//						client.setId(Integer.toString(id));
//					} else {
//						client = new Client(firstName, middleInitial, lastName, street, city, state, zipCode);
//						client.setId(Integer.toString(id));
//					}
//					filterList.add(client);
//				}
//			} catch (SQLException e) {
//				System.out.println(e);
//				e.printStackTrace();
//			}
//		}
//		return filterList;
//	}
}
