package Family.model;

public class student {

	String SName;
	String Sgender;
	int SId;
	
	public String getSName() {
		return SName;
	}
	public void setSName(String sName) {
		SName = sName;
	}
	public String getSgender() {
		return Sgender;
	}
	public void setSgender(String sgender) {
		Sgender = sgender;
	}
	public int getSId() {
		return SId;
	}
	public void setSId(int sId) {
		SId = sId;
	}
	
	public student(String sName, String sgender, int sId) {
		super();
		SName = sName;
		Sgender = sgender;
		SId = sId;
	}
	
	public student() {
		super();
	}
	
}
