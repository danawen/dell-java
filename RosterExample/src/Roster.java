import java.util.ArrayList;
import java.util.List;

public class Roster {
	private List<Employee> employeeList;
	
	public Roster() {
		employeeList = new ArrayList<>();
	}
	
	public List<Employee> getEmployeeList() {
		return employeeList;
	}
	
	public void setEmployeeList(List<Employee> employees) {
		employeeList = employees;
	}
	
	public void addEmployee(Employee toAdd) {
		employeeList.add(toAdd);
	}
}
