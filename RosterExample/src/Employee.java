
public class Employee {
	
	// Variables
	
	private static int NEXTID = 100; // Static variable that goes with the class
	
	private String name;
	private String jobTitle;
	private int id;
	
	/*
	 * TODO:
	 * Fields to add:
	 * boolean isActive - true if the employee is active (i.e. not retired or on leave), false otherwise
	 * LocalDateTime startDate - the date this employee began employment
	 */
	
	// Constructor
	
	public Employee(String empName, String empJobTitle) {
		name = empName;
		jobTitle = empJobTitle;
		id = NEXTID;
		NEXTID++; // increment
	}
	
	// Methods
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getJobTitle() {
		return jobTitle;
	}
	
	public void setJobTitle(String title) {
		this.jobTitle = title;
	}
}
