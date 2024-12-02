package Family.model;

public class person {

	String Name;
	Long MobileNo;
	int RollNo;
	String gender;
	int Id;
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Long getMobileNo() {
		return MobileNo;
	}
	public void setMobileNo(Long mobileNo) {
		MobileNo = mobileNo;
	}
	public int getRollNo() {
		return RollNo;
	}
	public void setRollNo(int rollNo) {
		RollNo = rollNo;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	
	public person(String name, Long mobileNo, int rollNo, String gender) {
		super();
		Name = name;
		MobileNo = mobileNo;
		RollNo = rollNo;
		this.gender = gender;
	}
	public person(String name, Long mobileNo, int rollNo, String gender, int id) {
		super();
		Name = name;
		MobileNo = mobileNo;
		RollNo = rollNo;
		this.gender = gender;
		Id = id;
	}
	public person() {
		super();
	}
	
	
}
