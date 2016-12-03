/**
 * 
 */
package employment;

import java.util.ArrayList;

/**
 * @author Andrew,Brandon,Brian
 *
 */
public class Employer {

	private String myCompanyName;
	private String myStartDate;
	private double mySalary;
	private String myPosition;
	private ArrayList<String> mySkillsList;
	
	/**
	 * This is the first constructor that takes a single skill to add to the list.
	 * 
	 * @param theCompanyName for the name of the company.
	 * @param theStartDate to see if this was an internship (i.e. startDate < graduationDate).
	 * @param theSalary for the yearly pay.
	 * @param thePosition for the title of their position.
	 * @param theSkill for a skill the student has.
	 */
	public Employer(String theCompanyName, String theStartDate, double theSalary,
				String thePosition, String theSkill) {
		this.setMyCompanyName(theCompanyName);
		this.setMyStartDate(theStartDate);
		this.setMySalary(theSalary);
		this.setMyPosition(thePosition);
		
		mySkillsList = new ArrayList<String>();
		this.mySkillsList.add(theSkill);
	}
	
	/**
	 * This overloaded constructor takes an entire list of skills instead of a single skill.
	 * 
	 * @param theCompanyName
	 * @param theStartDate
	 * @param theSalary
	 * @param thePosition
	 * @param theSkillsList
	 */
	public Employer(String theCompanyName, String theStartDate, double theSalary,
			String thePosition, ArrayList<String> theSkillsList) {
		
		this.setMyCompanyName(theCompanyName);
		this.setMyStartDate(theStartDate);
		this.setMySalary(theSalary);
		this.setMyPosition(thePosition);

		mySkillsList = new ArrayList<String>();
		
		for(String s: theSkillsList) {
		this.mySkillsList.add(s);
		}
		
	}

	public void setMyPosition(String myPosition) {
		
		this.myPosition = myPosition;
	}

	public void setMySalary(double mySalary) {
		
		this.mySalary = mySalary;
	}

	public void setMyStartDate(String myStartDate) {
		
		this.myStartDate = myStartDate;
	}

	public void setMyCompanyName(String myCompanyName) {
		
		this.myCompanyName = myCompanyName;
	}
	
	public String getMyPosition() {
		
		return myPosition;
	}
	
	public double getMySalary() {
		
		return mySalary;
	}
	
	public String getMyStartDate() {
		
		return myStartDate;
	}
	
	public String getMyCompanyName() {
		
		return myCompanyName;
	}

	public void addSkill(String theSkill) {
		
		mySkillsList.add(theSkill);
	}
	
	public void addSkill(ArrayList<String> theSkills) {
		
		for(String s : theSkills) {
			mySkillsList.add(s);
		}
	}
	
	public ArrayList<String> getSkills() {
		
		ArrayList<String> tempList = new ArrayList<String>();
		
		for(String s : mySkillsList) {
			tempList.add(s);
		}
		
		return tempList;
	}
}
