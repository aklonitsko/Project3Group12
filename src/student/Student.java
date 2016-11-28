/**
 * 
 */
package student;

import java.util.List;

import academic.AcademicRecord;
import employment.Employer;

/**
 * @author Andrew,Brandon,Brian
 *
 */
public class Student {

	private String myID;
	private String myFirstName;
	private String myLastName;
	private AcademicRecord myAcademicRecord;
	private List<Employer> myEmployers;
	
	public Student(String theFirstName, String theLastName){
		this.setFirstName(theFirstName);
		this.setLastName(theLastName);
	}
	
	public String getFirstName() {
		return myFirstName;
	}
	
	public void setFirstName(String theFirstName) {
		this.myFirstName = theFirstName;
	}
	
	public String getLastName() {
		return myLastName;
	}
	
	public void setLastName(String theFirstName) {
		this.myLastName = theFirstName;
	}
	
	public AcademicRecord getAcademicRecord() {
		return myAcademicRecord;
	}
	
	public void setAcademicRecord(AcademicRecord theAcademicRecord) {
		this.myAcademicRecord = theAcademicRecord;
	}
	
	public List<Employer> getEmployers() {
		return myEmployers;
	}
	
	public void setEmployers(List<Employer> theEmployers) {
		this.myEmployers = theEmployers;
	}

	public String getID() {
		return myID;
	}

	public void setID(String theID) {
		this.myID = theID;
	}

	
}
