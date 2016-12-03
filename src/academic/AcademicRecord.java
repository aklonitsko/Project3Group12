/**
Brandon Gibbons
 * 
 */
package academic;

import java.util.ArrayList;

/**
 * @author Andrew,Brandon,Brian
 *
 */
public class AcademicRecord {
	
	private String myID;
	private String myProgram;
	private String myGraduationTerm;
	private String myGraduationYear;
	private String myUWEmail;
	private String myExternalEmail;
	private double myGPA;
	private ArrayList<TransferSchool> myPreviousSchools;
	
	public AcademicRecord(String theProgram, String theGraduationTerm, String theGraduationYear,
			String theUWEmail, String theExternalEmail, double theGPA){
		this.setProgram(theProgram);
		this.setGraduationTerm(theGraduationTerm);
		this.setGraduationYear(theGraduationYear);
		this.setUWEmail(theUWEmail);
		this.setExternalEmail(theExternalEmail);
		this.setGPA(theGPA);
	}
	
	public AcademicRecord(String theProgram, String theGraduationTerm, String theGraduationYear,
			String theUWEmail, String theExternalEmail, double theGPA, ArrayList<TransferSchool> thePreviousSchools){
		//Chained Constructor
		this(theProgram, theGraduationTerm, theGraduationYear,
			theUWEmail, theExternalEmail,theGPA);
		
		this.setPreviousSchools(thePreviousSchools);
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

	public ArrayList<TransferSchool> getPreviousSchools() {
		return myPreviousSchools;
	}
	
	public void setPreviousSchools(ArrayList<TransferSchool> thePreviousSchools) {
		myPreviousSchools = thePreviousSchools;
	}

	public boolean addTransferSchool(TransferSchool theTransferSchool){
		
		boolean flag = false;
		
		try {
			
			myPreviousSchools.add(theTransferSchool);
			flag = true;
		} catch(Exception e) {
			
			flag = false;
		}
		
		return flag;
	}

}
