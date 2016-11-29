/**
 * 
 */
package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import academic.AcademicCollection;
import academic.AcademicRecord;

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
	@Test
	public void testAddAcademicRecord() {
		assertTrue(AcademicCollection.add(mRecord));
	}

	/**
	 * Test method for {@link academic.AcademicCollection#add(academic.TransferSchool)}.
	 */
	@Test
	public void testAddTransferSchool() {
		fail("Not yet implemented");
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
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link academic.AcademicCollection#getAcademicRecord()}.
	 */
	@Test
	public void testGetAcademicRecord() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link academic.AcademicCollection#getTransferSchools(java.lang.String)}.
	 */
	@Test
	public void testGetTransferSchoolsString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link academic.AcademicCollection#getTransferSchools()}.
	 */
	@Test
	public void testGetTransferSchools() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link academic.AcademicCollection#getTransferSchool(java.lang.String)}.
	 */
	@Test
	public void testGetTransferSchool() {
		fail("Not yet implemented");
	}

}
