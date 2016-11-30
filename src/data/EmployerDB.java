package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import academic.AcademicRecord;
import academic.TransferSchool;
import employment.Employer;
import employment.Skill;

/**
 * This class contains methods to access the Employer and 
 * Skill tables data.
 * 
 * @author Brian Lloyd
 *
 */

public class EmployerDB {

	private Connection mConnection;
	
	/**
	 * Modifies the data on an Employer
	 * 
	 * @param Employer
	 * @param columnName
	 * @param data
	 * @return Returns a message with success or failure.
	 */
	public String updateEmployer(Employer employer, String columnName, Object data) {

		String sql = "UPDATE Employer SET `" + columnName + "` = ?  WHERE employerID = ?";
		// For debugging - System.out.println(sql);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = mConnection.prepareStatement(sql);
			if (data instanceof String){
				preparedStatement.setString(1, (String)data);
			} else if (data instanceof Date){
				//Handle GPA
				preparedStatement.setDate(1, (java.sql.Date) data);
			}
			preparedStatement.setInt(2, Integer.parseInt(employer.getID())); // for academicID
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
			return "Error updating employer: " + e.getMessage();
		}
		return "Updated Employer Successfully";
	}
	
	/**
	 * Adds a new employer to the Employer table.
	 * 
	 * @param employer Employer to be added
	 * @return Returns "Added Employer Successfully" or "Error adding employer: " with
	 *         the sql exception.
	 */
	public String addEmployer(Employer employer) {
		String sql = "INSERT INTO Employer(studentID, startDate, position) "
				+ "VALUES (?, ?, ?);";

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
			preparedStatement.setInt(1, Integer.parseInt(employer.getStudentID()));
			preparedStatement.setDate(2, employer.getStartDate());
			preparedStatement.setString(3, employer.getPosition());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error adding employer: " + e.getMessage();
		}
		return "Added Employer Successfully";
	}

	/**
	 * Gets the employer for the student with the given student id from the 
	 * employer and skills tables
	 * 
	 * @param studentID unique id of student who matches the employer
	 * @return Returns all employers for the student with the given student id
	 * @throws SQLException
	 */
	public List<Employer> getEmployers(String studentID) throws SQLException {
		if (mConnection == null) {
			mConnection = DataConnection.getConnection();
		}
		List<Employer> employers = new ArrayList<Employer>();
		PreparedStatement stmt = null;
		String query = "SELECT * from Employer where studentID = ?";
		Employer employer = null;
		try {
			stmt = mConnection.prepareStatement(query);
			stmt.setInt(1, Integer.parseInt(studentID));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("employerID");
				int resultStudentID = rs.getInt("studentID");
				String stringStudentID = Integer.toString(resultStudentID);
				Date startDate = rs.getDate("startDate");
				String position = rs.getString("position");
				employer = new Employer (stringStudentID, startDate, position);
				employer.setID(Integer.toString(id));
				List<Skill> skills = getSkills(employer.getID());
				employer.setSkills(skills);
				employers.add(employer);
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
		return employers;
	}
	
	/**
	 * Gets the employer that has the given employer id;
	 * 
	 * @param employerID Unique ID of the employer
	 * @return Returns an employer with the given employerID
	 * @throws SQLException
	 */
	public Employer getEmployer(String employerID) throws SQLException {
		if (mConnection == null) {
			mConnection = DataConnection.getConnection();
		}
		Employer employer = null;
		PreparedStatement stmt = null;
		String query = "SELECT * from Employer where employerID = ?";
		try {
			stmt = mConnection.prepareStatement(query);
			stmt.setInt(1, Integer.parseInt(employerID));
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("employerID");
				int resultStudentID = rs.getInt("studentID");
				String stringStudentID = Integer.toString(resultStudentID);
				Date startDate = rs.getDate("startDate");
				String position = rs.getString("position");
				employer = new Employer (stringStudentID, startDate, position);
				employer.setID(Integer.toString(id));
				List<Skill> skills = getSkills(employer.getID());
				employer.setSkills(skills);
				return employer;
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
		return employer;
	}
	
	/**
	 * Gets all employers from the Employer table.
	 * 
	 * @return Returns all employers from the Employer table.
	 * @throws SQLException
	 */
	public List<Employer> getEmployers() throws SQLException {
		if (mConnection == null) {
			mConnection = DataConnection.getConnection();
		}
		Statement stmt = null;
		String query = "SELECT * from Employer";
		List<Employer> employers = new ArrayList<Employer>();
		try {
			stmt = mConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt("employerID");
				int resultStudentID = rs.getInt("studentID");
				String stringStudentID = Integer.toString(resultStudentID);
				Date startDate = rs.getDate("startDate");
				String position = rs.getString("position");
				Employer employer = new Employer (stringStudentID, startDate, position);
				employer.setID(Integer.toString(id));
				List<Skill> skills = getSkills(employer.getID());
				employer.setSkills(skills);
				employers.add(employer);
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
		return employers;
	}
	
	
	/**
	 * Modifies the data on a Skill
	 * 
	 * @param skill Skill used while employed by an employer
	 * @param columnName
	 * @param data
	 * @return Returns a message with success or failure.
	 */
	public String updateSkill(Skill skill, String name) {

		String sql = "UPDATE Skill SET `name` = ?  WHERE skillID = ?";
		// For debugging - System.out.println(sql);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = mConnection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, Integer.parseInt(skill.getID())); // for skillID
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
			return "Error updating skill: " + e.getMessage();
		}
		return "Updated Skill Successfully";
	}
	
	/**
	 * Gets the skill that matches the unique skill ID.
	 * 
	 * @param skillID Unique id of the Skill.
	 * @return Returns a skill that correspond to the given skill id
	 * @throws SQLException
	 */
	public Skill getSkill(String skillID) throws SQLException {
		if (mConnection == null) {
			mConnection = DataConnection.getConnection();
		}
		PreparedStatement preparedStmt = null;
		int intskillID = Integer.parseInt(skillID);
		String query = "SELECT * FROM Skill WHERE skillID = ?";
		Skill skill = null;
		try {
			preparedStmt = mConnection.prepareStatement(query);
			preparedStmt.setInt(1, intskillID);
			ResultSet rs = preparedStmt.executeQuery();
			if (rs.next()) {
				int returnSkillID = rs.getInt("skillID");
				int employerID = rs.getInt("employerID");
				String name = rs.getString("name");
				skill = new Skill(Integer.toString(employerID), name);
				skill.setID = Integer.toString(returnSkillID);
				return skill;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e);
		} finally {
			if (preparedStmt != null) {
				preparedStmt.close();
			}
		}
		return skill;
	}
	
	/**
	 * Gets  the skills for the employer with the given employer id from
	 *  the skills tables
	 * 
	 * @param employerID unique id of the employer where the skill was used
	 * @return Returns a list of skills that correspond to the given employer id
	 * @throws SQLException
	 */
	public List<Skill> getSkills(String employerID) throws SQLException {
		if (mConnection == null) {
			mConnection = DataConnection.getConnection();
		}
		PreparedStatement preparedStmt = null;
		int intEmployerID = Integer.parseInt(employerID);
		String query = "SELECT * FROM Skill WHERE employerID = ?";
		List<Skill> skills = new ArrayList<Skill>();
		try {
			preparedStmt = mConnection.prepareStatement(query);
			preparedStmt.setInt(1, intEmployerID);
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				int skillID = rs.getInt("skillID");
				intEmployerID = rs.getInt("employerID");
				String name = rs.getString("name");
				Skill skill = new Skill(Integer.toString(intEmployerID), name);
				skill.setID(Integer.toString(skillID));
				skills.add(skill);
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
		return skills;
	}
	
	/**
	 * Gets  all skills in the Skill table.
	 * 
	 * @return Returns a list of skills
	 * @throws SQLException
	 */
	public List<Skill> getSkills() throws SQLException {
		if (mConnection == null) {
			mConnection = DataConnection.getConnection();
		}
		Statement stmt = null;
		String query = "SELECT * FROM Skill";
		List<Skill> skills = new ArrayList<Skill>();
		try {
			stmt = mConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int skillID = rs.getInt("skillID");
				int employerID = rs.getInt("employerID");
				String name = rs.getString("name");
				Skill skill = new Skill(Integer.toString(employerID), name);
				skill.setID(Integer.toString(skillID));
				skills.add(skill);
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
		return skills;
	}
	
	/**
	 * Adds a new skill to the Skill table.
	 * 
	 * @param skill Skill to be added to the database
	 * @return Returns "Added Skill Successfully" or "Error adding skill: " with
	 *         the sql exception.
	 */
	public String addSkill(Skill skill) {
		String sql = "INSERT INTO Skill(employerID, `name`) VALUES "
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
			preparedStatement.setInt(1, Integer.parseInt(skill.getEmployerID()));
			preparedStatement.setString(2, skill.getName());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error adding skill: " + e.getMessage();
		}
		return "Added Skill Successfully";
	}
	
	/**
	 * Deletes a skill within the Skill table.
	 * 
	 * @param skill Skill to be deleted.
	 * @return Returns "Deleted Skill Successfully" or "Error deleting skill: " with
	 *         the sql exception.
	 */
	public String deleteSkill(Skill skill) {
		String sql = "DELETE FROM Skill WHERE skillID = ?");

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
			preparedStatement.setInt(1, Integer.parseInt(skill.getID()));
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error deleting skill: " + e.getMessage();
		}
		return "Deleted Skill Successfully";
	}
	
	/**
	 * Deletes an employer from the Employer table.
	 * 
	 * @param employer Employer to be deleted.
	 * @return Returns "Deleted Employer Successfully" or "Error deleting employer: " with
	 *         the sql exception.
	 */
	public String deleteEmployer(Employer employer) {
		String sql = "DELETE FROM Employer WHERE employerID = ?";

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
			preparedStatement.setInt(1, Integer.parseInt(employer.getID()));
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error deleting employer: " + e.getMessage();
		}
		return "Deleted Employer Successfully";
	}
	
}
