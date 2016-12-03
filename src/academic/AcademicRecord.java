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
	
<<<<<<< HEAD
	private String myID;
	private String myProgram;
	private String myGraduationTerm;
	private String myGraduationYear;
	private String myUWEmail;
	private String myExternalEmail;
	private double myGPA;
	private ArrayList<TransferSchool> myPreviousSchools;
=======
	private String mID;
	private String mStudentID;
	private String mProgram;
	private String mDegreeLevel;
	private String mGraduationTerm;
	private String mGraduationYear;
	private String mUWEmail;
	private String mExternalEmail;
	private double mGPA;
	private List<TransferSchool> mTransferSchools;
>>>>>>> 9840fc700c75e6cb811d47af837db4ee3655df33
	
	public AcademicRecord(String studentID, String program, String degreeLevel, String graduationTerm, String graduationYear,
			String uwEmail, String externalEmail, double GPA){
		this.setStudentID(studentID);
		this.setProgram(program);
		this.setDegreeLevel(degreeLevel);
		this.setGraduationTerm(graduationTerm);
		this.setGraduationYear(graduationYear);
		this.setUWEmail(uwEmail);
		this.setExternalEmail(externalEmail);
		this.setGPA(GPA);
	}
	
<<<<<<< HEAD
	public AcademicRecord(String theProgram, String theGraduationTerm, String theGraduationYear,
			String theUWEmail, String theExternalEmail, double theGPA, ArrayList<TransferSchool> thePreviousSchools){
=======
	public AcademicRecord(String studentID, String theProgram, String theDegreeLevel, String theGraduationTerm, String theGraduationYear,
			String theUWEmail, String theExternalEmail, double theGPA, List<TransferSchool> theTransferSchools){
>>>>>>> 9840fc700c75e6cb811d47af837db4ee3655df33
		//Chained Constructor
		this(studentID, theProgram, theDegreeLevel, theGraduationTerm, theGraduationYear,
			theUWEmail, theExternalEmail,theGPA);
		
		this.setTransferSchools(theTransferSchools);
	}
	
	public String getID() {
		return mID;
	}

	public void setID(String myID) {
		this.mID = myID;
	}

	public String getProgram() {
		return mProgram;
	}

	public void setProgram(String myProgram) {
		this.mProgram = myProgram;
	}

	public String getGraduationTerm() {
		return mGraduationTerm;
	}

	public void setGraduationTerm(String myGraduationTerm) {
		this.mGraduationTerm = myGraduationTerm;
	}

	public String getGraduationYear() {
		return mGraduationYear;
	}

	public void setGraduationYear(String myGraduationYear) {
		this.mGraduationYear = myGraduationYear;
	}

	public String getUWEmail() {
		return mUWEmail;
	}

	public void setUWEmail(String myUWEmail) {
		this.mUWEmail = myUWEmail;
	}

	public String getExternalEmail() {
		return mExternalEmail;
	}

	public void setExternalEmail(String myExternalEmail) {
		this.mExternalEmail = myExternalEmail;
	}

	public double getGPA() {
		return mGPA;
	}

	public void setGPA(double myGPA) {
		this.mGPA = myGPA;
	}

<<<<<<< HEAD
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
=======
	public List<TransferSchool> getTransferSchools() {
		return mTransferSchools;
	}
	
	public void setTransferSchools(List<TransferSchool> thePreviousSchools) {
		mTransferSchools = thePreviousSchools;
	}

	public void addTransferSchool(TransferSchool theTransferSchool){
		mTransferSchools.add(theTransferSchool);
	}

	public String getDegreeLevel() {
		return mDegreeLevel;
	}

	public void setDegreeLevel(String myDegreeLevel) {
		this.mDegreeLevel = myDegreeLevel;
	}

	public String getStudentID() {
		return mStudentID;
	}

	public void setStudentID(String studentID) {
		this.mStudentID = studentID;
>>>>>>> 9840fc700c75e6cb811d47af837db4ee3655df33
	}

}
