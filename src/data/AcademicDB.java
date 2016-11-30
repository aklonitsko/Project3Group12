package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import academic.AcademicRecord;
import academic.TransferSchool;

/**
 * This class contains methods to access the AcademicRecord and 
 * TransferSchool tables data.
 * 
 * @author Brian Lloyd
 *
 */

public class AcademicDB {

	private Connection mConnection;
	
	/**
	 * Modifies the data on an academic record
	 * 
	 * @param record
	 * @param columnName
	 * @param data
	 * @return Returns a message with success or failure.
	 */
	public String updateAcademicRecord(AcademicRecord record, String columnName, Object data) {

		String sql = "UPDATE AcademicRecord SET `" + columnName + "` = ?  WHERE academicID = ?";
		// For debugging - System.out.println(sql);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = mConnection.prepareStatement(sql);
			if (data instanceof String){
				preparedStatement.setString(1, (String)data);
			} else if (data instanceof Double){
				//Handle GPA
				preparedStatement.setDouble(1, (double)data);
			}
			preparedStatement.setInt(2, Integer.parseInt(record.getID())); // for academicID
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
			return "Error updating academic record: " + e.getMessage();
		}
		return "Updated Academic Record Successfully";
	}
	
	/**
	 * Adds a new academic record to the AcademicRecord table.
	 * 
	 * @param record academic record to be added
	 * @return Returns "Added Student Successfully" or "Error adding student: " with
	 *         the sql exception.
	 */
	public String addAcademicRecord(AcademicRecord record) {
		String sql = "INSERT INTO AcademicRecord(studentID, program, degreeLevel,"
				+ " graduationTerm, graduationYear, uwEmail, externalEmail,"
				+ " GPA) values "
				+ "(?, ?, ?, ?, ?, ?, ?, ?); ";

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
			preparedStatement.setInt(1, Integer.parseInt(record.getStudentID()));
			preparedStatement.setString(2, record.getProgram());
			preparedStatement.setString(3, record.getDegreeLevel());
			preparedStatement.setString(4, record.getGraduationTerm());
			preparedStatement.setString(5, record.getGraduationYear());
			preparedStatement.setString(6, record.getUWEmail());
			preparedStatement.setString(7, record.getExternalEmail());
			preparedStatement.setDouble(8, record.getGPA());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error adding academic record: " + e.getMessage();
		}
		return "Added Academic Record Successfully";
	}

	/**
	 * Gets the AcademicRecord for the student with the given student id from the 
	 * AcademicRecord and TransferSchool tables
	 * 
	 * @param studentID unique id of student who matches the academic record
	 * @return Returns an academic record for the student with the given student id
	 * @throws SQLException
	 */
	public AcademicRecord getAcademicRecord(String studentID) throws SQLException {
		if (mConnection == null) {
			mConnection = DataConnection.getConnection();
		}
		PreparedStatement stmt = null;
		String query = "select * " + "from AcademicRecord where studentID = ?";
		AcademicRecord record = null;
		try {
			stmt = mConnection.prepareStatement(query);
			stmt.setInt(1, Integer.parseInt(studentID));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("academicID");
				int resultStudentID = rs.getInt("studentID");
				String stringStudentID = Integer.toString(resultStudentID);
				String program = rs.getString("program");
				String degreeLevel = rs.getString("degreeLevel");
				String graduationTerm = rs.getString("graduationTerm");
				String graduationYear = rs.getString("graduationYear");
				String uwEmail = rs.getString("uwEmail");
				String externalEmail = rs.getString("externalEmail");
				double GPA = rs.getDouble("GPA");
				record = new AcademicRecord(stringStudentID, program, degreeLevel,
						graduationTerm, graduationYear, uwEmail, externalEmail, GPA);
				record.setID(Integer.toString(id));
				List<TransferSchool> transferSchools = getTransferSchools(record.getID());
				record.setTransferSchools(transferSchools);
				return record;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		
		//Returned null if no records found
		return record;
	}
	
	/**
	 * Gets  all academic records from the AcademicRecord and TransferSchool tables
	 * 
	 * @return Returns list of all students
	 * @throws SQLException
	 */
	public List<AcademicRecord> getAcademicRecord() throws SQLException {
		if (mConnection == null) {
			mConnection = DataConnection.getConnection();
		}
		Statement stmt = null;
		String query = "select * " + "from AcademicRecord";

		List<AcademicRecord> records = new ArrayList<AcademicRecord>();
		try {
			stmt = mConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int academicID = rs.getInt("academicID");
				int studentID = rs.getInt("studentID");
				String stringStudentID = Integer.toString(studentID);
				String program = rs.getString("program");
				String degreeLevel = rs.getString("degreeLevel");
				String graduationTerm = rs.getString("graduationTerm");
				String graduationYear = rs.getString("graduationYear");
				String uwEmail = rs.getString("uwEmail");
				String externalEmail = rs.getString("externalEmail");
				double GPA = rs.getDouble("GPA");
				AcademicRecord record = new AcademicRecord(stringStudentID, program, degreeLevel,
						graduationTerm, graduationYear, uwEmail, externalEmail, GPA);
				record.setID(Integer.toString(academicID));
				List<TransferSchool> transferSchools = getTransferSchools(record.getID());
				record.setTransferSchools(transferSchools);
				
				records.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		
		return records;
	}
	
	
	/**
	 * Modifies the data on an transfer school
	 * 
	 * @param school Transfer school student previously attended
	 * @param columnName
	 * @param data
	 * @return Returns a message with success or failure.
	 */
	public String updateTransferSchool(TransferSchool school, String columnName, Object data) {

		String sql = "UPDATE TransferSchool SET `" + columnName + "` = ?  WHERE transferID = ?";
		// For debugging - System.out.println(sql);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = mConnection.prepareStatement(sql);
			if (data instanceof String){
				preparedStatement.setString(1, (String)data);
			} else if (data instanceof Double){
				//Handle GPA
				preparedStatement.setDouble(1, (double)data);
			}
			preparedStatement.setInt(2, Integer.parseInt(school.getID())); // for transferID
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
			return "Error updating transfer school: " + e.getMessage();
		}
		return "Updated Transfer School Successfully";
	}
	
	/**
	 * Gets the transfer school that matches the unique transfer school ID.
	 * 
	 * @param transferID Unique id of the transfer school.
	 * @return Returns a transfer school that correspond to the given transfer school id
	 * @throws SQLException
	 */
	public TransferSchool getTransferSchool(String transferID) throws SQLException {
		if (mConnection == null) {
			mConnection = DataConnection.getConnection();
		}
		PreparedStatement stmt = null;
		int intTransferID = Integer.parseInt(transferID);
		String query = "SELECT * FROM TransferSchool WHERE transferID = ?";
		TransferSchool school = null;
		try {
			stmt = mConnection.prepareStatement(query);
			stmt.setInt(1, intTransferID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int returnTransferID = rs.getInt("transferID");
				int academicID = rs.getInt("academicID");
				String name = rs.getString("name");
				double GPA = rs.getDouble("GPA");
				String degree = rs.getString("degreeEarned");
				school = new TransferSchool(name, GPA, degree);
				school.setAcademicID(Integer.toString(academicID));
				school.setID(Integer.toString(returnTransferID));
				return school;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return school;
	}
	
	/**
	 * Gets  the transfer school for the academic record with the given academic record id from
	 *  the TransferSchool tables
	 * 
	 * @param recordID unique id of the student's academic record
	 * @return Returns a list of transfer schools that correspond to the given academic record id
	 * @throws SQLException
	 */
	public List<TransferSchool> getTransferSchools(String recordID) throws SQLException {
		if (mConnection == null) {
			mConnection = DataConnection.getConnection();
		}
		PreparedStatement preparedStmt = null;
		int academicID = Integer.parseInt(recordID);
		String query = "SELECT * FROM TransferSchool WHERE academicID = ?";
		List<TransferSchool> schools = new ArrayList<TransferSchool>();
		try {
			preparedStmt = mConnection.prepareStatement(query);
			preparedStmt.setInt(1, academicID);
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				int transferID = rs.getInt("transferID");
				academicID = rs.getInt("academicID");
				String name = rs.getString("name");
				double GPA = rs.getDouble("GPA");
				String degree = rs.getString("degreeEarned");
				TransferSchool school = new TransferSchool(name, GPA, degree);
				school.setAcademicID(Integer.toString(academicID));
				school.setID(Integer.toString(transferID));
				schools.add(school);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e);
		} finally {
			if (preparedStmt != null) {
				preparedStmt.close();
			}
		}
		
		//Returned null if no records found
		return schools;
	}
	
	/**
	 * Gets  the AcademicRecord for the student with the given student id from the 
	 * AcademicRecord and TransferSchool tables
	 * 
	 * @param studentID unique id of student who matches the academic record
	 * @return Returns an academic record for the student with the given student id
	 * @throws SQLException
	 */
	public List<TransferSchool> getTransferSchools() throws SQLException {
		if (mConnection == null) {
			mConnection = DataConnection.getConnection();
		}
		Statement stmt = null;
		String query = "SELECT * FROM TransferSchool";
		List<TransferSchool> schools = new ArrayList<TransferSchool>();
		try {
			stmt = mConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int transferID = rs.getInt("transferID");
				int academicID = rs.getInt("academicID");
				String name = rs.getString("name");
				double GPA = rs.getDouble("GPA");
				String degree = rs.getString("degreeEarned");
				TransferSchool school = new TransferSchool(name, GPA, degree);
				school.setAcademicID(Integer.toString(academicID));
				school.setID(Integer.toString(transferID));
				schools.add(school);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		
		//Returned null if no records found
		return schools;
	}
	
	/**
	 * Adds a new transfer school to the TransferSchool table.
	 * 
	 * @param school transfer school to be added
	 * @return Returns "Added TransferSchool Successfully" or "Error adding transfer school: " with
	 *         the sql exception.
	 */
	public String addTransferSchool(TransferSchool school) {
		String sql = "INSERT INTO TransferSchool(academicID, `name`, GPA,"
				+ " degreeEarned) values "
				+ "(?, ?, ?, ?); ";

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
			preparedStatement.setInt(1, Integer.parseInt(school.getAcademicID()));
			preparedStatement.setString(2, school.getName());
			preparedStatement.setDouble(3, school.getGPA());
			preparedStatement.setString(4, school.getDegreeEarned());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error adding transfer school: " + e.getMessage();
		}
		return "Added Transfer School Successfully";
	}
	
	/**
	 * Deletes a transfer school within the TransferSchool table.
	 * 
	 * @param school transfer school to be deleted
	 * @return Returns "Deleted TransferSchool Successfully" or "Error deleteing transfer school: " with
	 *         the sql exception.
	 */
	public String deleteTransferSchool(TransferSchool school) {
		String sql = "DELETE FROM TransferSchool WHERE transferID = " + Integer.parseInt(school.getID());

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
			preparedStatement.setInt(1, Integer.parseInt(school.getAcademicID()));
			preparedStatement.setString(2, school.getName());
			preparedStatement.setDouble(3, school.getGPA());
			preparedStatement.setString(4, school.getDegreeEarned());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error deleting transfer school: " + e.getMessage();
		}
		return "Deleted Transfer School Successfully";
	}
	
}
