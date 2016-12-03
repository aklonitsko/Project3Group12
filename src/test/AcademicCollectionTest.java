/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import academic.AcademicCollection;
import academic.AcademicRecord;
import academic.TransferSchool;

/**
 * @author Andrew,Brandon,Brian
 *
 */
public class AcademicCollectionTest {

	private AcademicRecord mRecord;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mRecord = new AcademicRecord("1","autoTestProgram","autoTestDegree","autoTestTerm",
				"1999","autoTest@uw.edu", "autotest@autotext.com", 2.01);
	}

	/**
	 * Test method for {@link academic.AcademicCollection#add(academic.AcademicRecord)}.
	 */
	@Ignore
	@Test
	public void testAddAcademicRecord() {
		assertTrue(AcademicCollection.add(mRecord));
	}

	/**
	 * Test method for {@link academic.AcademicCollection#add(academic.TransferSchool)}.
	 */
	@Test
	public void testAddTransferSchool() {
		TransferSchool school = new TransferSchool("autoTest", 1.0, "NA");
		school.setAcademicID("1");
		assertTrue(AcademicCollection.add(school));
	}

	/**
	 * Test method for {@link academic.AcademicCollection#update(academic.AcademicRecord, java.lang.String, java.lang.Object)}.
	 */
	@Test
	public void testUpdateAcademicRecordStringObject() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link academic.AcademicCollection#update(academic.TransferSchool, java.lang.String, java.lang.Object)}.
	 */
	@Test
	public void testUpdateTransferSchoolStringObject() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link academic.AcademicCollection#getAcademicRecord(java.lang.String)}.
	 */
	@Test
	public void testGetAcademicRecordString() {
		AcademicRecord record = AcademicCollection.getAcademicRecord("1");
		System.out.println(record.getID() + " " + record.getStudentID() + " " + record.getProgram() + " " + record.getGPA() + " " + record.getTransferSchools());
		assertNotNull(record);
	}

	/**
	 * Test method for {@link academic.AcademicCollection#getAcademicRecord()}.
	 */
	@Test
	public void testGetAcademicRecord() {
		List<AcademicRecord> records = AcademicCollection.getAcademicRecord();
		for (AcademicRecord record : records){
			System.out.println(record.getID() + " " + record.getStudentID() + " " + record.getProgram() + " " + record.getGPA() + " " + record.getTransferSchools());
		}
		assertNotNull(records);
	}

	/**
	 * Test method for {@link academic.AcademicCollection#getTransferSchools(java.lang.String)}.
	 */
	@Test
	public void testGetTransferSchoolsString() {
		List<TransferSchool> schools = AcademicCollection.getTransferSchools("1");
		for (TransferSchool school : schools){
			//Debug
			System.out.println(school.getID() + " " + school.getAcademicID() + " " + school.getName() + " " + school.getGPA());
		}
		assertNotNull(schools);
	}

	/**
	 * Test method for {@link academic.AcademicCollection#getTransferSchools()}.
	 */
	@Test
	public void testGetTransferSchools() {
		List<TransferSchool> schools = AcademicCollection.getTransferSchools();
		for (TransferSchool school : schools){
			//Debug
			System.out.println(school.getID() + " " + school.getAcademicID() + " " + school.getName() + " " + school.getGPA());
		}
		assertNotNull(schools);
	}

	/**
	 * Test method for {@link academic.AcademicCollection#getTransferSchool(java.lang.String)}.
	 */
	@Test
	public void testGetTransferSchool() {
		TransferSchool school = AcademicCollection.getTransferSchool("1");
		//Debug
		System.out.println(school.getID() + " " + school.getAcademicID() + " " + school.getName() + " " + school.getGPA());
		assertNotNull(school);
	}

}
