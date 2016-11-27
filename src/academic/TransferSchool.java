/**
 * 
 */
package academic;

/**
 * @author Andrew,Brandon,Brian
 *
 */
public class TransferSchool {
	
	private String myName;
	private double myGPA;
	private String myDegreeEarned;
	
	
	public TransferSchool(String theName, double theGPA, String theDegreeEarned){
		this.setName(theName);
		this.setGPA(theGPA);
		this.setDegreeEarned(theDegreeEarned);
	}
	
	public String getName() {
		return myName;
	}
	public void setName(String myName) {
		this.myName = myName;
	}
	public double getGPA() {
		return myGPA;
	}
	public void setGPA(double myGPA) {
		this.myGPA = myGPA;
	}
	public String getDegreeEarned() {
		return myDegreeEarned;
	}
	public void setDegreeEarned(String myDegreeEarned) {
		this.myDegreeEarned = myDegreeEarned;
	}
	

}
