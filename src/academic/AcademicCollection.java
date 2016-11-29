package academic;

import data.*;

import java.sql.SQLException;
import java.util.List;

/**
 * AcademicCollection class represents a collection of academic information models.
 * 
 * @author Brian Lloyd
 */
public class AcademicCollection {

	private static AcademicDB mAcademicDB;

	/**
	 * Adds a new AcademicRecord to the data.
	 * 
	 * @param record academic record of the student.
	 * @return true or false
	 */
	public static boolean add(AcademicRecord record) {		
		if (mAcademicDB == null) {
			mAcademicDB = new AcademicDB();
		}

		String message = mAcademicDB.addAcademicRecord(record);
		
		if (message.startsWith("Error adding academic record:")) {
			return false;
		}
		return true;
	}
	
	/**
	 * Adds a new transfer school to the data.
	 * 
	 * @param school transfer school that will appear on a students academic record.
	 * @return true or false
	 */
	public static boolean add(TransferSchool school) {
		if (mAcademicDB == null) {
			mAcademicDB = new AcademicDB();
		}

		String message = mAcademicDB.addTransferSchool(school);
		if (message.startsWith("Error adding academic record:")) {
			return false;
		}
		return true;
	}

	/**
	 * Modify the particular column of the academic record with the given data.
	 * 
	 * @param record
	 *            AcademicRecord to modify
	 * @param column
	 *            the field in the table to modify
	 * @param data
	 *            the value for the field
	 * @return true or false
	 */
	public static boolean update(AcademicRecord record, String column, Object data) {
		if (mAcademicDB == null) {
			mAcademicDB = new AcademicDB();
		}
		String message = mAcademicDB.updateAcademicRecord(record, column, data);
		if (message.startsWith("Error updating academic record: ")) {
			return false;
		}
		return true;
	}
	
	/**
	 * Modify the particular column of the academic record with the given data.
	 * 
	 * @param school
	 *            Transfer school to modify
	 * @param column
	 *            the field in the table to modify
	 * @param data
	 *            the value for the field
	 * @return true or false
	 */
	public static boolean update(TransferSchool school, String column, Object data) {
		if (mAcademicDB == null) {
			mAcademicDB = new AcademicDB();
		}
		String message = mAcademicDB.updateTransferSchool(school, column, data);
		if (message.startsWith("Error updating academic record: ")) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * Return an academic record that matches the student id, null otherwise.
	 * 
	 * @param studentID
	 *            Id of the student that the academic record belongs to
	 * @return item
	 */
	public static AcademicRecord getAcademicRecord(String studentID) {
		if (mAcademicDB == null) {
			mAcademicDB = new AcademicDB();
		}
		try {
			return mAcademicDB.getAcademicRecord(studentID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * Return all academic records
	 * 
	 * @return item
	 */
	public static List<AcademicRecord> getAcademicRecord() {
		if (mAcademicDB == null) {
			mAcademicDB = new AcademicDB();
		}
		try {
			return mAcademicDB.getAcademicRecord();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * Return's a transfer school that matches the  id, null otherwise.
	 * 
	 * @param academicID
	 *            Id of the academic record that the transfer school belongs to
	 * @return list of transfer schools belonging to the given academic record
	 */
	public static List<TransferSchool> getTransferSchools(String academicID) {
		if (mAcademicDB == null) {
			mAcademicDB = new AcademicDB();
		}
		try {
			return mAcademicDB.getTransferSchools(academicID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * Return's a list of all transfer schools
	 *  
	 * @return list of transfer schools belonging to the given academic record
	 */
	public static List<TransferSchool> getTransferSchools() {
		if (mAcademicDB == null) {
			mAcademicDB = new AcademicDB();
		}
		try {
			return mAcademicDB.getTransferSchools();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * Return's a transfer school that matches the unique transfer school id, null otherwise.
	 * 
	 * @param transferID
	 *            Id of the transfer school
	 * @return Transfer school that matches the transfer school id
	 */
	public static TransferSchool getTransferSchool(String transferID) {
		if (mAcademicDB == null) {
			mAcademicDB = new AcademicDB();
		}
		try {
			return mAcademicDB.getTransferSchool(transferID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
