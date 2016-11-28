/**
Brandon Gibbons
 * 
 */
package academic;

import java.util.List;

/**
 * @author Andrew,Brandon,Brian
 *
 */
public class AcademicRecord {
	
	private String myID;
	private String myProgram;
	private String myDegreeLevel;
	private String myGraduationTerm;
	private String myGraduationYear;
	private String myUWEmail;
	private String myExternalEmail;
	private double myGPA;
	private List<TransferSchool> myTransferSchools;
	
	public AcademicRecord(String theProgram, String theDegreeLevel, String theGraduationTerm, String theGraduationYear,
			String theUWEmail, String theExternalEmail, double theGPA){
		this.setProgram(theProgram);
		this.setDegreeLevel(theDegreeLevel);
		this.setGraduationTerm(theGraduationTerm);
		this.setGraduationYear(theGraduationYear);
		this.setUWEmail(theUWEmail);
		this.setExternalEmail(theExternalEmail);
		this.setGPA(theGPA);
	}
	
	public AcademicRecord(String theProgram, String theDegreeLevel, String theGraduationTerm, String theGraduationYear,
			String theUWEmail, String theExternalEmail, double theGPA, List<TransferSchool> theTransferSchools){
		//Chained Constructor
		this(theProgram, theDegreeLevel, theGraduationTerm, theGraduationYear,
			theUWEmail, theExternalEmail,theGPA);
		
		this.setTransferSchools(theTransferSchools);
	}
	
	public String getID() {
		return myID;
	}

	public void setID(String myID) {
		this.myID = myID;
	}

	public String getProgram() {
		return myProgram;
	}

	public void setProgram(String myProgram) {
		this.myProgram = myProgram;
	}

	public String getGraduationTerm() {
		return myGraduationTerm;
	}

	public void setGraduationTerm(String myGraduationTerm) {
		this.myGraduationTerm = myGraduationTerm;
	}

	public String getGraduationYear() {
		return myGraduationYear;
	}

	public void setGraduationYear(String myGraduationYear) {
		this.myGraduationYear = myGraduationYear;
	}

	public String getUWEmail() {
		return myUWEmail;
	}

	public void setUWEmail(String myUWEmail) {
		this.myUWEmail = myUWEmail;
	}

	public String getExternalEmail() {
		return myExternalEmail;
	}

	public void setExternalEmail(String myExternalEmail) {
		this.myExternalEmail = myExternalEmail;
	}

	public double getGPA() {
		return myGPA;
	}

	public void setGPA(double myGPA) {
		this.myGPA = myGPA;
	}

	public List<TransferSchool> getTransferSchools() {
		return myTransferSchools;
	}
	
	public void setTransferSchools(List<TransferSchool> thePreviousSchools) {
		myTransferSchools = thePreviousSchools;
	}

	public void addTransferSchool(TransferSchool theTransferSchool){
		myTransferSchools.add(theTransferSchool);
	}

	public String getDegreeLevel() {
		return myDegreeLevel;
	}

	public void setDegreeLevel(String myDegreeLevel) {
		this.myDegreeLevel = myDegreeLevel;
	}

}
