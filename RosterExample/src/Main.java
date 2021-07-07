import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Roster myRoster = new Roster();
		Scanner reader = new Scanner(System.in);
	
		/*
		 * User command options:
		 * add - create a new employee and add them to the roster
		 * list - print all the employees in the roster
		 * list [id] - given an employee id, print their info
		 * quit - exit the program
		 * 
		 * TODO:
		 * delete [id] - given an employee id, remove that employee from the roster
		 * list [-a] - print all active employees
		 */
		boolean quit = false; 
		
		while(!quit) {
			System.out.println("Command options: add, list [id], quit");
			String input = reader.nextLine(); // Read user's input
			String[] inputParts = input.split(" "); // Split user input into multiple strings
			String firstCommand = inputParts[0]; // First command typed by user
			if (firstCommand.equals("add")) {
				// Prompt user for employee info
				System.out.println("Please enter employee name");
				String name = reader.nextLine();
				System.out.println("Please enter employee title");
				String title = reader.nextLine();

				Employee emp = new Employee(name, title);
				myRoster.addEmployee(emp);
			} else if (firstCommand.equals("list")) {
				if (inputParts.length == 2) { // User has input "list [i]"
					int empId = Integer.parseInt(inputParts[1]);
					printEmployee(myRoster, empId);
				} else if (inputParts.length == 1) { // User has input "list"
					printEmployeeList(myRoster);
				}
			} else if (firstCommand.equals("quit")) {
				quit = true;
			} else {
				System.out.println("Invalid input, please try again");
			}
		}
		reader.close();
	}
	
	// Print the entire employee list
	private static void printEmployeeList(Roster myRoster) {
		List<Employee> employeeList = myRoster.getEmployeeList();
		for (int i = 0; i < employeeList.size(); i++) {
			Employee currEmp = employeeList.get(i);
			System.out.println("Id: " + currEmp.getId()
							+ ", Name: " + currEmp.getName()
							+ ", Title: " + currEmp.getJobTitle());
		}
	}
	
	// Given the employee id, find them in the list and print their info
	private static void printEmployee(Roster myRoster, int id) {
		List<Employee> employeeList = myRoster.getEmployeeList();
		for (int i = 0; i < employeeList.size(); i++) {
			Employee currEmp = employeeList.get(i);
			int currId = currEmp.getId();
			if (currId == id) {
				System.out.println("Id: " + currEmp.getId()
				+ ", Name: " + currEmp.getName()
				+ ", Title: " + currEmp.getJobTitle());
			}
		}
	}

}
