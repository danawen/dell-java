import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException; 

/**
 *
 * @author sqlitetutorial.net
 */
public class SqliteExample {
	/**
	 * Connect to a sample database
	 */
	public static void connect() {
		Connection conn = null;
		Scanner scanner = new Scanner(System.in);
		try {

			// create the database connection, note the name of the file is test.db           
			String url = "jdbc:sqlite:test.db";
			conn = DriverManager.getConnection(url);
			// the connection we created above, will be used for all of the calls to the database

			// step 1 create the table in the database  if it does not already exists
			// since we want to use the connection we already created, we will pass it in to the method,
			createEmployeeTable(conn);

			// now we will insert a record in the database
			// again we pass the connection to the method
			// we want to add 2 employees so we call the method 2 times
			addNewEmployee(conn, scanner);
			addNewEmployee(conn, scanner);

			// print the employees so we can see the ones that we added
			printEmployees(conn);

			// short pause
			Thread.sleep(1000);

			// update a specific employee
			updateEmployee(conn, scanner);

			// print all the employees so we see the employee we updated
			printEmployees(conn);

			// short pause
			Thread.sleep(1000);

			// delete an employee 
			deleteEmployee(conn, scanner);

			// print all the employees so we can see the employee we deleted
			printEmployees(conn);

			//short pause
			Thread.sleep(1000);

			// we delete the table so the next time we can start with a fresh table
			deleteTable(conn);


		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			scanner.close();
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		connect();
	}

	public static void createEmployeeTable(Connection conn) throws SQLException {
		System.out.println("Creating the table if it does not already exist");

		Statement stmt = conn.createStatement();
		stmt.execute("CREATE TABLE IF NOT EXISTS employees (id integer, name varchar(255)); ");
		stmt.close();
		System.out.println("finished creating the table");
		System.out.println();
	}

	public static void deleteTable(Connection conn) throws SQLException {
		System.out.println("Deleting the table ");

		Statement stmt = conn.createStatement();
		stmt.execute("DROP TABLE employees; ");
		stmt.close();
		System.out.println("done deleting the table");
		System.out.println();
	}


	public static void updateEmployee(Connection conn, Scanner scanner) throws SQLException {
		System.out.println("Editing an employee record ");

		// ask the user for an id and  a name
		System.out.print("Enter the id to update: ");
		String idInput = scanner.nextLine();
		System.out.print("Enter the new name: ");
		String nameInput = scanner.nextLine();

		// parse the id entered into an int
		int id = Integer.parseInt(idInput);

		// use the users input to update the database using a prepared statement
		PreparedStatement stmt = conn.prepareStatement("update employees set name = ? where id = ?;");
		stmt.setString(1, nameInput); // notice this is 1 and not 0, because counting starts at 1 for SQL
		stmt.setInt(2, id);
		stmt.executeUpdate();
		stmt.close();
		System.out.println("done updating the employee record");
		System.out.println();

	}

	public static void addNewEmployee(Connection conn, Scanner scanner) throws SQLException {
		System.out.println("Adding a new employee record ");

		// ask the user for information to add to the table
		System.out.print("Enter the id: ");
		String idInput = scanner.nextLine();
		System.out.print("Enter the name: ");
		String nameInput = scanner.nextLine();

		// we parse the users string input to an int for the id
		int id = Integer.parseInt(idInput);

		// we use a preparted statment
		PreparedStatement stmt = conn.prepareStatement("insert into employees values (?,?);");
		stmt.setInt(1, id); // again notice that in Sql counting starts at 1 not 0
		stmt.setString(2, nameInput);
		stmt.executeUpdate();
		stmt.close();
		System.out.println("done adding a new employee record ");
		System.out.println();

	}

	public static void deleteEmployee(Connection conn, Scanner scanner) throws SQLException {
		System.out.println("Deleting an employee record ");

		// ask for the id to delete
		System.out.print("Enter the id to delete: ");
		String idInput = scanner.nextLine();
		// we parse the user's input to an int for th id
		int id = Integer.parseInt(idInput);

		// we use the user's input as a parameter in our where caluse.
		PreparedStatement stmt = conn.prepareStatement("delete from employees where id = ? ");
		stmt.setInt(1, id); // notice that in sql counting starts at 1 not 0
		stmt.executeUpdate();
		stmt.close();
		System.out.println("done deletng the employee record ");
		System.out.println();

	}



	public static void printEmployees(Connection conn)
			throws SQLException {
		System.out.println("Printing all the employee records");

		// since we are not using any user input, we do not need to use a prepared statement
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select id, name from employees");
		// loop through all the results that come back in the result set
		while(rs.next()){
			// get the id column for the row we are processing
			int id = rs.getInt("id"); // rs.getInt(1);
			// get the name column for the row we are processing
			String name = rs.getString("name"); // rs.getString();

			// print the id and name we got to the console
			System.out.println(id+ " "+name);
		}
		rs.close();
		stmt.close();
		System.out.println("done printing the employee records ");
		System.out.println();

	}
}
