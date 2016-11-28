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
import student.Student;

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
	public String updateAcademicRecord(AcademicRecord record, String columnName, String data) {

		String sql = "UPDATE AcademicRecord SET `" + columnName + "` = ?  WHERE academicID = ?";
		// For debugging - System.out.println(sql);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = mConnection.prepareStatement(sql);
			preparedStatement.setString(1, data);
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
			preparedStatement.setInt(1, Integer.parseInt(record.getID()));
			preparedStatement.setString(2, record.getProgram());
			preparedStatement.setString(3, record.getDegreeLevel());
			preparedStatement.setString(4, record.getGraduationTerm());
			preparedStatement.setString(5, record.getUWEmail());
			preparedStatement.setString(6, record.getExternalEmail());
			preparedStatement.setDouble(7, record.getGPA());
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
	public AcademicRecord getAcademicRecord(Student student) throws SQLException {
		if (mConnection == null) {
			mConnection = DataConnection.getConnection();
		}
		Statement stmt = null;
		String query = "select * " + "from AcademicRecord where studentID = " + student.getID();
		AcademicRecord record = null;
		try {
			stmt = mConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt("academicID");
				String program = rs.getString("program");
				String degreeLevel = rs.getString("degreeLevel");
				String graduationTerm = rs.getString("graduationTerm");
				String graduationYear = rs.getString("graduationYear");
				String uwEmail = rs.getString("uwEmail");
				String externalEmail = rs.getString("externalEmail");
				double GPA = rs.getDouble("GPA");
				record = new AcademicRecord(program, degreeLevel,
						graduationTerm, graduationYear, uwEmail, externalEmail, GPA);
				record.setID(Integer.toString(id));
				List<TransferSchool> transferSchools = getTransferSchools(record);
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
//				int studentID = rs.getInt("studentID");
				String program = rs.getString("program");
				String degreeLevel = rs.getString("degreeLevel");
				String graduationTerm = rs.getString("graduationTerm");
				String graduationYear = rs.getString("graduationYear");
				String uwEmail = rs.getString("uwEmail");
				String externalEmail = rs.getString("externalEmail");
				double GPA = rs.getDouble("GPA");
				AcademicRecord record = new AcademicRecord(program, degreeLevel,
						graduationTerm, graduationYear, uwEmail, externalEmail, GPA);
				record.setID(Integer.toString(academicID));
				List<TransferSchool> transferSchools = getTransferSchools(record);
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
	 * Gets  the AcademicRecord for the student with the given student id from the 
	 * AcademicRecord and TransferSchool tables
	 * 
	 * @param studentID unique id of student who matches the academic record
	 * @return Returns an academic record for the student with the given student id
	 * @throws SQLException
	 */
	public List<TransferSchool> getTransferSchools(AcademicRecord record) throws SQLException {
		if (mConnection == null) {
			mConnection = DataConnection.getConnection();
		}
		Statement stmt = null;
		int academicID = Integer.parseInt(record.getID());
		String query = "select * " + "from TransferSchool where academicID = " + academicID;
		List<TransferSchool> schools = new ArrayList<TransferSchool>();
		try {
			stmt = mConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int transferID = rs.getInt("transferID");
				String name = rs.getString("name");
				double GPA = rs.getDouble("GPA");
				String degree = rs.getString("degreeEarned");
				TransferSchool school = new TransferSchool(name, GPA, degree);
				school.setAcademicID(record.getID());
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
		String query = "select * " + "from TransferSchool";
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
