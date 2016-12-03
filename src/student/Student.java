/**
 * 
 */
package student;

import java.util.ArrayList;
import java.util.List;

import academic.AcademicRecord;
import employment.Employer;

/**
 * @author Andrew,Brandon,Brian
 *
 */
public class Student {

	private String myName;
	private AcademicRecord myAcademicRecord;
	private ArrayList<Employer> myEmployers;

	/**
	 * WARNING- This constructor is ONLY for use when a student is to be added, but no information is available.
	 * @param theName is the student's name.
	 */
	public Student(String theName) {
		
		this.setMyName(theName);
		
		/**
		 * This allows a record to be created, and editable for future use.
		 */
		addAcademicRecord(new AcademicRecord("undecided", "undecided", "undecided", "none", "none", 0));
	}
	
	/**
	 * This Constructor should be used in most cases.
	 * @param theName for te students name.
	 * @param theRecord for any existing academic record.
	 * @param theEmployers for any employers the student already has.
	 */
	public Student(String theName, AcademicRecord theRecord, List<Employer> theEmployers) {
		
		this.setMyName(theName);
		addAcademicRecord(theRecord);
		
		for(Employer e : theEmployers) {
			addEmployer(e);
		}
	}
	
	protected boolean addEmployer(Employer theEmployer) {
		
		boolean flag = false;
		
		try {
		myEmployers.add(new Employer(theEmployer.getMyCompanyName(), theEmployer.getMyStartDate(), theEmployer.getMySalary(),
						theEmployer.getMyPosition(), theEmployer.getSkills()));
			flag = true;
		} catch(Exception e) {
			
			flag = false;
		}
		
		return flag;
	}
	
	protected boolean addAcademicRecord(AcademicRecord theRecord) {
		
		boolean flag = false;
		
		try {
			myAcademicRecord = new AcademicRecord(theRecord.getProgram(), theRecord.getGraduationTerm(), theRecord.getGraduationYear(),
					theRecord.getUWEmail(), theRecord.getExternalEmail(), theRecord.getGPA());
				flag = true;
			} catch(Exception e) {
				
				flag = false;
			}
			
			return flag;
	}

	protected void setMyName(String myName) {
		this.myName = myName;
	}

	public String getMyName() {
		return myName;
	}
	
	public AcademicRecord getMyAcademicRecord() {
		return myAcademicRecord;
	}
	
	public List<Employer> getMyEmployers() {
		return myEmployers;
	}
	
}
